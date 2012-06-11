package de.emgress;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.LongClick;
import com.googlecode.androidannotations.annotations.ViewById;

@EActivity(R.layout.main)
public class MainActivity extends Activity
{
	public static final String LOG_TAG = "AA MainActivity";

	@ViewById( R.id.mytextview )
	TextView textview;

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

}
