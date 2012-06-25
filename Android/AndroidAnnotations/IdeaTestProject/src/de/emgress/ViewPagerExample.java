package de.emgress;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;


@EActivity( R.layout.view_pager_example )
public class ViewPagerExample extends FragmentActivity
{
    @ViewById( R.id.viewpager )
	ViewPager pagerView;

    @AfterViews
    public void configurePager()
    {
        PagerAdapter adapter = new FragmentPagerAdapter( getSupportFragmentManager() ) {

            @Override
            public android.support.v4.app.Fragment getItem(int i) {
                return new PagerFragment();
            }

            @Override
            public int getCount() {
                return 10;
            }
        };

        pagerView.setAdapter( adapter );
    }
}