package de.emgress;

import android.app.Activity;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.googlecode.androidannotations.annotations.*;

@EActivity( R.layout.background_example )
public class BackgroundExampleActivity extends Activity
{
    private static final int PROGRESS_MAX_VALE = 100;
    private static final int PROGRESS_INCREMENT = 1;
    private int currentProgress = 0;

    private boolean taskRunning = false;
    private boolean taskShouldStop = false;

    @ViewById
    ProgressBar progressBar;

    @ViewById
    TextView statusTextView;

    @AfterViews
    public void configureViews()
    {
        progressBar.setMax( PROGRESS_MAX_VALE );
    }

    /*
    Button Handling
     */
    @Click
    public void startProcess()
    {
        if( !taskRunning )
            runTask();
        else
            notifyUser( "Task already running!" );
    }

    @Click
    public void stopProcess()
    {
        taskShouldStop = true;
    }

    @Click
    public void resetProcess()
    {
        currentProgress = 0;

        if( taskRunning )
        {
            stopProcess();

            try { Thread.sleep( 100 ); }
            catch (InterruptedException e) {}

            startProcess();
        }
        else
        {
            updateProgressBar( currentProgress );
        }
    }


    /*
    Background Task Handling
     */

    @Background
    public void runTask()
    {
        taskRunning = true;

        setText( "Progress started" );

        for( int i = currentProgress; i< PROGRESS_MAX_VALE; i+=PROGRESS_INCREMENT )
        {
            if( taskShouldStop ) break;

            updateProgressBar( i );

            try
            {
                Thread.sleep( 30 );
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

        }

        if( taskShouldStop )
        {
            taskShouldStop = false;
            setText( "Progress stopped" );
        }
        else
        {
            setText( "Progress finished" );
        }

        taskRunning = false;
    }

    @UiThread
    public void updateProgressBar( int progress )
    {
        progressBar.setProgress( progress );
    }

    /*
    Util Methods
     */

    @UiThread
    public void setText( String text )
    {
        statusTextView.setText( text );
    }

    @UiThread
    public void notifyUser( String msg )
    {
        Toast.makeText( this, msg, Toast.LENGTH_SHORT ).show();
    }
}