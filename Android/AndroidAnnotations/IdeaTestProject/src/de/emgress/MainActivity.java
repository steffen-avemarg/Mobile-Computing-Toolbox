package de.emgress;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.googlecode.androidannotations.annotations.*;

@EActivity(R.layout.main)
@OptionsMenu( R.menu.optionmenu )
public class MainActivity extends Activity
{
	public static final String LOG_TAG = "AA MainActivity";

	@ViewById( R.id.mytextview )
	TextView textview;

    @AfterViews
    public void configureViews()
    {

    }

	@Click( R.id.button )
	public void buttonPressed( View pressedButton )
	{
		Log.i( LOG_TAG, "Button Pressed - Object: " + pressedButton.toString() );

		textview.setText( "Awesome!" );
	}

    @LongClick( R.id.mytextview )
    public void resetTextView()
    {
        textview.setText( "Reset :)" );
    }

    private boolean switchTo( Class requestedClass )
    {
        Intent i = new Intent( this, requestedClass );
        startActivity( i );
        return true;
    }


    @OptionsItem
    public boolean switchToBackgroundExample()
    {
        return switchTo( BackgroundExampleActivity_.class );
    }

    @OptionsItem
    public boolean switchToViewPagerExample()
    {
        return switchTo( ViewPagerTitleExample_.class );
    }

    @OptionsItem
    public boolean switchToAccelerometerExample()
    {
        return switchTo( AccelerometerActivity.class );
    }

    @OptionsItem
    public boolean switchToCompassExample()
    {
        return switchTo( CompassActivity.class );
    }


    @OptionsItem
    public boolean switchToCameraIntentExample()
    {
        return switchTo( CameraCallActivity_.class );
    }

	@OptionsItem
	public boolean switchToTweenExample()
	{
		Intent i = new Intent( this, TweenExample_.class);
		startActivity( i );

		return true;
	}


}
