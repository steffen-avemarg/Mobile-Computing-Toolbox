package de.emgress.android.layoutanimation;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		this.setupListView();
    }

	private void setupListView()
	{
		String[] items = new String[] {
				"Item 1", "Item 2", "Item 3",
				"Item 4", "Item 5", "Item 6",
				"Item 7", "Item 8"
		};

		ArrayAdapter<String> adatper = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, items );

		ListView lv = (ListView)findViewById( R.id.list_view );
		lv.setAdapter( adatper );
	}

}
