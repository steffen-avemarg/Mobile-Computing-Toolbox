package de.emgress.android.asynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import de.fhe.R;

public class MainActivity extends Activity implements OnClickListener 
{

    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ProgressBar bar = (ProgressBar)findViewById( R.id.bar );
        bar.setMax( 100 );
        
        Button startBtn = (Button)findViewById( R.id.startBtn );
        startBtn.setOnClickListener( this );
    }
    
    public void setProgress( Integer value )
    {
    	ProgressBar bar = (ProgressBar)findViewById( R.id.bar );
    	bar.setProgress( value.intValue() );
    }
    
    public void setMessage( String msg )
    {
    	TextView tv = (TextView)findViewById( R.id.textview );
    	tv.setText( msg );
    }
    
    @Override
	public void onClick(View v) 
    {
		new MyASyncTask().execute( "Parameter 1", "Parameter 2", "Parameter 3", 
			"Parameter 4", "Parameter 5" );
	}


	private class MyASyncTask extends AsyncTask<String, Integer, Void>
    {
		@Override
		protected void onPreExecute() 
		{
			setMessage( "MyASyncTask - running... " );
		}
    	
		@Override
		protected Void doInBackground(String... params) 
		{
			int paramIndex = 1;
			
			for( String param : params )
			{
				try { Thread.sleep( 1000 ); }
				catch (InterruptedException e) {}
				
				Log.i( "Param", param );
				
				int progress = 100/params.length * paramIndex;
				this.publishProgress( new Integer( progress ) );
								
				paramIndex++;
			}
			
			return null;
		}

		@Override
		protected void onProgressUpdate(Integer... values) 
		{
			setProgress( values[0] );
		}
    	
		@Override
		protected void onPostExecute(Void result) 
		{
			setMessage( "Finished!" );
		
		}
    }
}