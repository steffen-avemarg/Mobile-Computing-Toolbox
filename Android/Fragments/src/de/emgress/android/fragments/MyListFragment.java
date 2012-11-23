package de.emgress.android.fragments;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MyListFragment extends ListFragment 
{
	private int mCurrentPosition;
	
	@Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Populate list with our static array of titles.
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_activated_1, 
                Shakespeare.TITLES ));
        
        if (savedInstanceState != null) 
        {
            // Restore last state for checked position.
        	mCurrentPosition = savedInstanceState.getInt( "curChoice", 0 );
        }

        // In dual-pane mode, the list view highlights the selected item.
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        // Make sure our UI is in the correct state.
        showDetails( mCurrentPosition );
        
	}

	@Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState( outState );
        outState.putInt( "curChoice", mCurrentPosition );
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        showDetails(position);
    }

	void showDetails(int index) {
		mCurrentPosition = index;

		getListView().setItemChecked(index, true);

		// Check what fragment is currently shown, replace if needed.
		DetailsFragment details = (DetailsFragment) getFragmentManager()
				.findFragmentById(R.id.details);
		if (details == null || details.getShownIndex() != index) {
			// Make new fragment to show this selection.
			details = DetailsFragment.newInstance(index);

			// Execute a transaction, replacing any existing fragment
			// with this one inside the frame.
			FragmentTransaction ft = getFragmentManager().beginTransaction();
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			ft.replace(R.id.details, details);
			ft.commit();
		}
	}

}
