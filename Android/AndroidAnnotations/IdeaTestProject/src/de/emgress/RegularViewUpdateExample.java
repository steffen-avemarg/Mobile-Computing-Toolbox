package de.emgress;

import android.app.Activity;
import android.util.Log;
import com.googlecode.androidannotations.annotations.*;

@EActivity( R.layout.regular_view_update )
public class RegularViewUpdateExample extends Activity
{
	@ViewById( R.id.drawView )
	public DrawView myView;

	@Background
	public void doSomething()
	{
		Log.i("Draw", "Starting...");

		for( int i=0; i < 5; i++ )
		{
			Log.i("Draw", "Loop " + i );

			myView.updateMyView();

			try
			{
				Thread.sleep( 300 );
			}
			catch (InterruptedException e)
			{
				Log.i( "Draw", "Exception!" );
			}


		}
	}

	@UiThread
	public void updateUI()
	{
		Log.i( "Draw", "RVUA - updateUI" );
		myView.updateMyView();
	}

	@Click( R.id.startMovingCircle )
	protected void startCircle()
	{
		Log.i("Draw", "Start Background Task");
		this.doSomething();
		Log.i("Draw", "Start Background Task started");
	}
}