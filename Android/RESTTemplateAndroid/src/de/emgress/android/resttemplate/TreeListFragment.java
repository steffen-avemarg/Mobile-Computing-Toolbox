package de.emgress.android.resttemplate;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import de.emgress.backend.rest.model.TreeElement;

import java.util.ArrayList;
import java.util.List;

public class TreeListFragment extends ListFragment implements NetworkOperationFragment
{
	private static final int TREE_ROOT_ID = -1;
	private static final String ARGUMENT_KEY_ELEMENT_ID = "rootElementId";

	private static final int REQUEST = 1;
	private int rootElementId = TREE_ROOT_ID;

	private Context cxt;

	public static TreeListFragment newInstance( int rootElementId )
	{
		TreeListFragment f = new TreeListFragment();

		// Supply elementID input as an argument.
		Bundle args = new Bundle();
		args.putInt( ARGUMENT_KEY_ELEMENT_ID, rootElementId );
		f.setArguments(args);

		return f;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);

		// Save the Context to be usable by inner classes
		this.cxt = getActivity();

		// Initialize TreeListAdapter with empty data set
		// Data will be loaded as soon as the Fragment
		// is displayed in an Activity
		setListAdapter( new TreeListAdapter( null ) );

		// Configure ListView Click Behaviour
		getListView().setChoiceMode( ListView.CHOICE_MODE_SINGLE );
		getListView().setClickable( true );
	}

	@Override
	public void onResume()
	{
		super.onResume();

		Bundle args = getArguments();
		if( args != null )
		{
			this.rootElementId = args.getInt( ARGUMENT_KEY_ELEMENT_ID, TREE_ROOT_ID );
		}

		this.refresh();
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id)
	{
		TreeListFragment subFragment = TreeListFragment.newInstance( (int)id );

		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.setTransition( FragmentTransaction.TRANSIT_FRAGMENT_FADE );
		ft.replace(R.id.content_container, subFragment);
		ft.addToBackStack( null );
		ft.commit();
	}

	/**
	 * Network Operations - we simply access the
	 * backend again to retrieve the actual list
	 *
	 * Depending on whether a rootElementId different
	 * from the Top Root ID is given, a different
	 * request is performed
	 */
	public void refresh()
	{
		if( this.rootElementId == TREE_ROOT_ID )
		{
			NetworkHandler.getTreeRootElements( REQUEST, this );
		}
		else
		{
			NetworkHandler.getTreeElements( REQUEST, this.rootElementId, this );
		}
	}

	/**
	 * Callback for NetworkHandler
	 * We extract the results and update the ListView
	 * @param requestID
	 * @param results
	 */
	public void onResult(int requestID, List<?> results)
	{
		/**
		 * We can suppress the warning as we know that the method
		 * we called at the NetworkHandler will return a List of
		 * <code>TreeElement</code>s
		 */
		@SuppressWarnings( "unchecked" )
		List<TreeElement> newListItem = (List<TreeElement>)results;

		// Get List Adapter
		TreeListAdapter adapter = (TreeListAdapter)this.getListAdapter();

		// Set new Items and propagate the change
		adapter.setItems( newListItem );
		adapter.notifyDataSetChanged();

		// Scroll to the top of the list
		this.getListView().smoothScrollToPosition(0);
	}

	/**
	 * Custom List Adapter
	 *
	 * Supplies the data for our ListView - tailored to
	 * the concrete List of TreeElements and a custom
	 * layout that displays the name of the TreeElement
	 * as well as the number of its children
	 */
	private class TreeListAdapter extends BaseAdapter
	{
		private final LayoutInflater inflater;
		private List<TreeElement> items;

		public TreeListAdapter(List<TreeElement> items)
		{
			this.inflater =
					(LayoutInflater)cxt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			if( items != null )
				this.items = items;
			else
				this.items = new ArrayList<TreeElement>();
		}

		public int getCount()
		{
			return items.size();
		}

		public Object getItem(int i)
		{
			return items.get( i );
		}

		public long getItemId(int i)
		{
			return items.get( i ).getId();
		}

		public View getView( int i, View convertView, ViewGroup parent )
		{
			ViewGroup view;

			if( convertView != null && convertView.getId() == android.R.layout.simple_list_item_2) //R.layout.tree_list_element
			{
				// We can reuse the View
				view = (ViewGroup)convertView;
			}
			else
			{
				// We create a new View
				view = (ViewGroup)inflater.inflate( android.R.layout.simple_list_item_2, parent, false );
			}

			TreeElement item = (TreeElement)getItem( i );

			TextView titleView = (TextView)view.findViewById( android.R.id.text1 );
			titleView.setText( item.getName() );

			TextView sizeView = (TextView)view.findViewById( android.R.id.text2 );
			sizeView.setText( getResources().getText( R.string.Label_Sub_Category_Count).toString() +
					": " + item.getChildren().size() );

			return view;
		}

		public void setItems( List<TreeElement> newItems )
		{
			this.items = newItems;
			this.notifyDataSetChanged();
		}

	}

}
