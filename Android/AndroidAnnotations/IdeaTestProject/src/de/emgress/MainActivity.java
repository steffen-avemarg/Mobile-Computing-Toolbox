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


    @OptionsItem
    public boolean switchToBackgroundExample()
    {
        Intent i = new Intent( this, BackgroundExampleActivity_.class);
        startActivity( i );

        return true;
    }

    @OptionsItem
    public boolean switchToViewPagerExample()
    {
        Intent i = new Intent( this, ViewPagerTitleExample_.class);
        startActivity( i );

        return true;
    }

}
