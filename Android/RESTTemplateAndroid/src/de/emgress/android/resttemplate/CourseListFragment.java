package de.emgress.android.resttemplate;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import de.emgress.backend.rest.model.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseListFragment extends ListFragment implements NetworkOperationFragment
{
	private static final int REQUEST = 1;

	private Context cxt;

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);

		// Save the Context to be usable by inner classes
		this.cxt = getActivity();

		// Initialize ListAdapter with empty data set
		// Data will be loaded as soon as the Fragment
		// is displayed in an Activity
		setListAdapter( new CourseListAdapter( null ) );

		// Configure ListView Click Behaviour
		getListView().setChoiceMode( ListView.CHOICE_MODE_SINGLE );
		getListView().setClickable( true );
	}


	@Override
	public void onResume()
	{
		super.onResume();

		this.refresh();
	}

	public void refresh()
	{
		NetworkHandler.getCourses( REQUEST, this );
	}

	public void onResult( int requestID, List<?> results )
	{
		/**
		 * We can suppress the warning as we know that the method
		 * we called at the NetworkHandler will return a List of
		 * <code>TreeElement</code>s
		 */
		@SuppressWarnings( "unchecked" )
		List<Course> newListItems = (List<Course>)results;

		// Get List Adapter
		CourseListAdapter adapter = (CourseListAdapter)this.getListAdapter();

		// Set new Items and propagate the change
		adapter.setItems( newListItems );
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
	private class CourseListAdapter extends BaseAdapter
	{
		private final LayoutInflater inflater;
		private List<Course> items;

		public CourseListAdapter( List<Course> items )
		{
			this.inflater =
					(LayoutInflater)cxt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			if( items != null )
				this.items = items;
			else
				this.items = new ArrayList<Course>();
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

			if( convertView != null && convertView.getId() == android.R.layout.simple_list_item_2)
			{
				// We can reuse the View
				view = (ViewGroup)convertView;
			}
			else
			{
				// We create a new View
				view = (ViewGroup)inflater.inflate( android.R.layout.simple_list_item_2, parent, false );
			}

			Course item = (Course)getItem( i );

			TextView titleView = (TextView)view.findViewById( android.R.id.text1 );
			titleView.setText( item.getTitle() );

			TextView sizeView = (TextView)view.findViewById( android.R.id.text2 );
			sizeView.setText( item.getDescription() );

			return view;
		}

		public void setItems( List<Course> newItems )
		{
			this.items = newItems;
			this.notifyDataSetChanged();
		}

	}

}
