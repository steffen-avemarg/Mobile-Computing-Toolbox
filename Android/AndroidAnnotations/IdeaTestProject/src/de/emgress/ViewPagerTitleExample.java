package de.emgress;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;


/**
 * This Activity demonstrates the usage of a ViewPager combined
 * with Tabs in the ActionBar to create a Google Play Store like
 * effect of sliding pages.
 */
@EActivity( R.layout.view_pager_example )
public class ViewPagerTitleExample extends FragmentActivity
{
	/**
	 * A simple array holding the titles of the different Tabs
	 * respectively Pages
	 */
	private static final String[] tabNames = { "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten" };

	/**
	 * A reference to the Adapter used for the ViewPager. The class is
	 * defined as inner class.
	 */
	private ViewPagerFragmentAdapter mAdapter;

	/**
	 * A reference to the Activity's ActionBar.
	 */
	public ActionBar mActionBar;

	/**
	 * A reference to the TabListener used by the ActionBar.
	 */
	private TabListener myTabListener;

	/**
	 * Using androidannotations, we inject a reference to the ViewPager
	 * that is defined in the layout xml for this Activity.
	 */
	@ViewById( R.id.viewpager )
	ViewPager mPager;

	/**
	 * We use the androidannotations library to mark this method as the
	 * final configuration method after the layout has been inflated and
	 * all View references have been injected into this Activity.
	 * <br/><br/>
	 * First, we create a TabListener, which will switch the pages
	 * of the ViewPager if another Tab inside the ActionBar has been
	 * selected.
	 * <br/><br/>
	 * Second, the ActionBar is configured for Tab Navigation Mode as well
	 * as several Tabs are added.
	 * <br/><br/>
	 * Third, we create an Adapter for the ViewPager which is responsible
	 * to create the different Pages as requested by the ViewPager - in
	 * this case, all pages are Fragments (instances of ListFragment, to be
	 * exact). Afterwards, several connections between the pager, the ActionBar
	 * and the adapter are created.
	 */
	@AfterViews
	public void configureViews()
	{
		myTabListener = new TabListener( mPager );

		mActionBar = getActionBar();

		mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		mActionBar.setTitle( "View Pager Tabs" );

		for( int i = 0; i<tabNames.length; i++ )
		{
			Tab tab = mActionBar.newTab()
					.setText( tabNames[i] )
					.setTabListener( myTabListener );
			mActionBar.addTab( tab );
		}

		mAdapter = new ViewPagerFragmentAdapter( getSupportFragmentManager() );
		mAdapter.setActionBar( mActionBar );

		mPager.setAdapter( mAdapter );
		mPager.setOnPageChangeListener( new MyPageChangeListener() );
	}

	/**
	 * This class is used to update the ActionBar Tabs if a new page
	 * has been selected/slided in by the View Pager.
	 * <br/><br/>
	 * We only implement the <code>onPageSelected</code> method,
	 * all other methods defined in the interface are empty.
	 */
	class MyPageChangeListener implements OnPageChangeListener
	{
		/**
		 * This method changes the selected tab of the Activity's ActionBar
		 * so that it corresponds to the selected page inside the ViewPager.
		 * <br/><br/>
		 * Remark: There is one caveat: If there are to many tabs to be
		 * displayed inside the ActionBar, the ActionBar will display a
		 * drop down list with all the tabs. This is in itself not a problem,
		 * but setting the selected tab will not result in an update to that
		 * drop down list.
		 *
		 * Solution needs to be found.
		 * <br/><br/>
		 * @param i the index of the currently displayed/selected page
		 */
		@Override
		public void onPageSelected(int i)
		{
			mActionBar.getTabAt( i ).select();
			mActionBar.setSelectedNavigationItem( i );
			mActionBar.selectTab( mActionBar.getTabAt( i ) );
		}

		/**
		 * Not used
		 * <br/><br/>
		 * @param i
		 */
		@Override
		public void onPageScrollStateChanged(int i) {}

		/**
		 * Not used
		 * <br/><br/>
		 * @param i
		 * @param v
		 * @param i1
		 */
		@Override
		public void onPageScrolled(int i, float v, int i1) {}
	}

	/**
	 * This adapter is used to supply the ViewPager with the contents
	 * of the different pages. In this case, these are simple Fragments
	 * that display the Fragments title as well as a ListView.
	 * <br/><br/>
	 * Those Fragments extend from the ListFragment class.
	 * <br/><br/>
	 * The adapter will create as much pages as there are tabs in the
	 * ActionBar. That said, the number of Tabs is defined by the number
	 * of elements in the <code>tabNames</code> Array. {@see configureViews,
	 * tabNames}
	 * <br/><br/>
	 * <b>One note of caution:</b> Having such a tight coupling between the adapter
	 * and the ActionBar is fine for this little example. However, in a full-
	 * fledged application, one would make sure that the Adapter does not
	 * need a reference to the ActionBar. It's only purpose is to create and
	 * supply the different pages for the ViewPager!
	 */
	public static class ViewPagerFragmentAdapter extends FragmentPagerAdapter {
		ActionBar mActionBar;

		public ViewPagerFragmentAdapter(FragmentManager fm)
		{
			super(fm);
		}

		/**
		 * The number of pages corresponds to the number of Tabs in the ActionBar.
		 * <br/><br/>
		 * @return the number of pages displayed by the ViewPager
		 */
		@Override
		public int getCount()
		{
			return mActionBar.getTabCount();
		}

		/**
		 * Creates a new instance of <code>ArrayListFragment</code>
		 *
		 * @param position the position of the page inside the ViewPager
		 * @return the new Fragment
		 */
		@Override
		public android.support.v4.app.Fragment getItem(int position)
		{
			return ArrayListFragment.newInstance(position);
		}

		/**
		 * Setter to supply a reference to the ActionBar. See class
		 * description for a remark on this kind of tight coupling.
		 * @param bar the ActionBar
		 */
		public void setActionBar( ActionBar bar )
		{
			mActionBar = bar;
		}
	}

	/**
	 * Subclass of ListFragment, used to display the different pages
	 * inside the ViewPager.
	 * <br/><br/>
	 * Simply shows a TextView with the name of the Page/Tab {@see tabNames}
	 * and a ListView with some static entries {@see listStrings}.
	 */

	public static class ArrayListFragment extends ListFragment {
		int mNum;

		/**
		 * Factory method.
		 *
		 * @param num Index of the Fragment inside the ViewPager
		 * @return a new instance of ArrayListFragment
		 */
		static ArrayListFragment newInstance(int num) {
			ArrayListFragment f = new ArrayListFragment();

			// Supply num input as an argument.
			Bundle args = new Bundle();
			args.putInt("num", num);
			f.setArguments(args);

			return f;
		}

		/**
		 * When creating, we retrieve this instance's page index from
		 * its arguments.
		 * <br/><br/>
		 * The Fragment's UI is just a simple text view showing its
		 * instance name as well as a ListView.
		 */
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
								 Bundle savedInstanceState) {

			mNum = (getArguments() != null ? getArguments().getInt("num") : 1);

			View v = inflater.inflate( R.layout.fragment_pager_list, container, false );
			TextView tv = (TextView)v.findViewById( R.id.text );
			tv.setText( "Fragment " + tabNames[mNum] );
			return v;
		}

		/**
		 * The last method that gets called during the creation of a fragment
		 * simply adds an adapter to the ListView using the <code>listStrings</code>
		 * array as content.
		 *
		 * @param savedInstanceState
		 */
		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);
			setListAdapter( new ArrayAdapter<String>(getActivity(),
					android.R.layout.simple_list_item_1, listStrings) );
		}
	}

	/**
	 * A utility array used by ArrayListFragment to fill the ListView.
	 */
	public static String[] listStrings = new String[100];
	static {
		for( int i = 0; i<100; i++ )
		{
			listStrings[i] = "Eintrag Nummer " + (i+1);
		}
	}

}