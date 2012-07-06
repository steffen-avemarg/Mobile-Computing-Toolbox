package de.emgress.android.appwidget;

import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RemoteViews;
import de.fhe.R;

public class MyConfigurationActivity extends Activity implements OnClickListener
{
	private int myAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
	
		setContentView( R.layout.config_layout );
		
		Button b = (Button)findViewById( R.id.config_finished_button );
		b.setOnClickListener( this );

		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		if( extras != null )
		{
			myAppWidgetId = extras.getInt( AppWidgetManager.EXTRA_APPWIDGET_ID, 
					AppWidgetManager.INVALID_APPWIDGET_ID );
		}
	}
	
	
	public static void updateAppWidget( Context context, AppWidgetManager appWidgetManager, int widgetId )
	{
		RemoteViews views = new RemoteViews( context.getPackageName(), R.layout.widget_layout );
		views.setTextViewText( R.id.widget_id, "WidgetID: " + widgetId );
		
		Intent clickAction = new Intent( Intent.ACTION_VIEW, Uri.parse( "http://www.google.de" ) );
		PendingIntent pI = PendingIntent.getActivity( context, 0, clickAction, 0 );
		views.setOnClickPendingIntent( R.id.button, pI );
		
		appWidgetManager.updateAppWidget( widgetId, views );
	}


	/**
	 * Called when the configuration is finished and the Config
	 * Activity should be dismissed.
	 *
	 * @param v the Button that was clicked
	 */
	@Override
	public void onClick(View v) 
	{
		// Check Config Data
		// Save valid Configuration
		
		// Update Widget
		updateAppWidget( this, AppWidgetManager.getInstance( this ), myAppWidgetId );
		
		// Send Result that Configuration was successful
		Intent resultValue = new Intent();
		resultValue.putExtra( AppWidgetManager.EXTRA_APPWIDGET_ID, myAppWidgetId );
		setResult( RESULT_OK, resultValue );
		finish();
	}
	
	
	

}
