package de.emgress.android.appwidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;

public class MyAppWidgetProvider extends AppWidgetProvider 
{
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) 
	{
		for( int i : appWidgetIds )
		{
			int appWidgetId = appWidgetIds[i];
			updateAppWidget( context, appWidgetManager, appWidgetId );
		}
	}

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) 
	{
		for( int i : appWidgetIds )
		{
			// Remove Widget Data
		}
	}
	
	@Override
	public void onEnabled(Context context) 
	{
		// Clear old Data
		
		// Register for Events
		PackageManager pm = context.getPackageManager();
		pm.setComponentEnabledSetting(
				new ComponentName("de.fhe", "MyAppWidgetProvider"),
				PackageManager.COMPONENT_ENABLED_STATE_ENABLED, 
				PackageManager.DONT_KILL_APP );
	}


	@Override
	public void onDisabled(Context context) 
	{
		// Clear old Data
		
		// De-Register for Events
		PackageManager pm = context.getPackageManager();
		pm.setComponentEnabledSetting(
				new ComponentName("de.fhe", "MyAppWidgetProvider"),
				PackageManager.COMPONENT_ENABLED_STATE_DISABLED, 
				PackageManager.DONT_KILL_APP );
	}

	
	
	private void updateAppWidget( Context context, AppWidgetManager manager, int wId )
	{
		// Ask Config Activity to update Widget
		MyConfigurationActivity.updateAppWidget( context, manager, wId );
	}
}
