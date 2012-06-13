package de.emgress;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

public class TabListener implements ActionBar.TabListener {
	private ViewPager mPager;

	public TabListener(ViewPager pager)
	{
		mPager = pager;
	}

	public void onTabSelected(Tab tab, FragmentTransaction ft)
	{
		mPager.setCurrentItem(tab.getPosition());
	}

	public void onTabUnselected(Tab tab, FragmentTransaction ft) {}

	public void onTabReselected(Tab tab, FragmentTransaction ft) {}
}
