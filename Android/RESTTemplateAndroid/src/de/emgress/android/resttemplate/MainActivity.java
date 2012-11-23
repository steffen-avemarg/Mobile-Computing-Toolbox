package de.emgress.android.resttemplate;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends Activity
{
	private static final String LOG_TAG = "emgress | " + MainActivity.class.toString();

	@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		TreeListFragment treeFragment = new TreeListFragment();
		CourseListFragment courseFragment = new CourseListFragment();

		ActionBar ab = this.getActionBar();
		ab.setTitle( "Friedolin" );
		ab.setNavigationMode( ActionBar.NAVIGATION_MODE_TABS );

		Tab tab1 = ab.newTab();
		tab1.setText( "Verzeichnis" );
		tab1.setTabListener( new MyTabListener( treeFragment ) );
		ab.addTab( tab1 );

		Tab tab2 = ab.newTab();
		tab2.setText( "Veranstaltungen" );
		tab2.setTabListener( new MyTabListener( courseFragment ) );
		ab.addTab( tab2 );
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = new MenuInflater( this );
		inflater.inflate( R.menu.main_menu, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		if( item.getItemId() == R.id.menu_refresh_item )
		{
			NetworkOperationFragment fragment =
			    	(NetworkOperationFragment)getFragmentManager().findFragmentById( R.id.content_container );

			fragment.refresh();

			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	/**
	 * ActionBar Tab Listener
	 */
	private class MyTabListener implements ActionBar.TabListener
	{
		NetworkOperationFragment fragment;

		public MyTabListener( NetworkOperationFragment fragment )
		{
			this.fragment = fragment;
		}

		public void onTabReselected(Tab tab, FragmentTransaction ft) {}

		public void onTabSelected(Tab tab, FragmentTransaction ft)
		{
			ft.replace( R.id.content_container, (Fragment)this.fragment );
		}

		public void onTabUnselected(Tab tab, FragmentTransaction ft)
		{
			ft.remove( (Fragment)this.fragment );
		}
	}


}
