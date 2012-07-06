package de.emgress.android.contentprovider;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import de.fhe.R;

public class MainActivity extends Activity 
	implements OnClickListener, LoaderManager.LoaderCallbacks<Cursor> {
	
	private SimpleCursorAdapter adapter = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button addButton = (Button)findViewById( R.id.add_button );
        Button deleteButton = (Button)findViewById( R.id.delete_button );
        addButton.setOnClickListener( this );
        deleteButton.setOnClickListener( this );
    
        this.fillData();
    }

	@Override
	public void onClick(View v) 
	{
		
		ListView lv = (ListView)findViewById( R.id.entries );
		
		// Add
		if( v.getId() == R.id.add_button )
		{
			ContentValues values = new ContentValues();
			values.put( EntriesTable.COLUMN_VALUE, "Entry: " + System.currentTimeMillis() );
			getContentResolver().insert( MyContentProvider.CONTENT_URI, values );
		
		}
		// Remove
		else if( v.getId() == R.id.delete_button )
		{
			if( lv.getAdapter().getCount() > 0 )
			{
				Cursor item = (Cursor)lv.getAdapter().getItem( 0 );
				long itemId = item.getLong( 0 );
				
				Uri uri = Uri.parse( MyContentProvider.CONTENT_URI + "/" + itemId );
				getContentResolver().delete( uri, null, null );
			}
		}
	}

	private void fillData()
	{
		getLoaderManager().initLoader( 0, null, this );
		
		if( adapter == null )
		{
			adapter = new SimpleCursorAdapter( this, android.R.layout.simple_list_item_1,
				null, new String[] { EntriesTable.COLUMN_VALUE}, 
				new int[] { android.R.id.text1 }, 0 );
		
			ListView lv = (ListView)findViewById( R.id.entries );
			lv.setAdapter( adapter );
		}
	}
	
	
	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		CursorLoader cursorLoader = new CursorLoader( this, 
				MyContentProvider.CONTENT_URI, null, null, null, null );
		return cursorLoader;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor ) {
		adapter.swapCursor( cursor );
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		adapter.swapCursor( null );
	}
	
	
}


