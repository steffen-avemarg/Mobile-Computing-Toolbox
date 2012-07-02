package de.emgress.android.sqlite;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import de.emgress.android.sqlite.fhe.R;

public class MainActivity extends Activity implements OnClickListener
{
	private EntriesDao entries;
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button addButton = (Button)findViewById( R.id.add_button );
        Button deleteButton = (Button)findViewById( R.id.delete_button );
        addButton.setOnClickListener( this );
        deleteButton.setOnClickListener( this );
    
        entries = new EntriesDao( new MySQLHelper( this ) );
        entries.open();
        
        Cursor cursor = entries.getAllEntries();
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, 
        		android.R.layout.simple_list_item_1, cursor, 
        		new String[] { EntriesDao.COLUMN_VALUE }, 
        		new int[] { android.R.id.text1 } );
        
        ListView lv = (ListView)findViewById( R.id.entries );
        lv.setAdapter( adapter );
    }

	@Override
	public void onClick( View v )
	{
		
		ListView lv = (ListView)findViewById( R.id.entries );
		
		// Add
		if( v.getId() == R.id.add_button )
		{
			entries.addEntry( "Current Time: " + System.currentTimeMillis() );
		}
		// Remove
		else if( v.getId() == R.id.delete_button )
		{
			if( lv.getAdapter().getCount() > 0 )
			{
				Cursor item = (Cursor)lv.getAdapter().getItem( 0 );
				long itemId = item.getLong( 0 );
				this.entries.removeEntry( itemId );
			}
		}
		
		Cursor newEntries = entries.getAllEntries();
		((SimpleCursorAdapter)lv.getAdapter()).swapCursor( newEntries );
	}
    
    
}




