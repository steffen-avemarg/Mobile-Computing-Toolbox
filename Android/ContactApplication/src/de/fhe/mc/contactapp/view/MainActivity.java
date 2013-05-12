package de.fhe.mc.contactapp.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import de.fhe.mc.contactapp.R;
import de.fhe.mc.contactapp.control.ContactAppController;

public class MainActivity extends Activity
{
    private ContactAppController controller;
    private ListView contactListView;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.main );

        this.controller = (ContactAppController)getApplication();

        // Get Reference to Contact List View
        this.contactListView = (ListView)findViewById( R.id.main_contactlist );

        this.contactListView.setAdapter( new ContactListAdapter(this.controller) );

        // Add Listener for List View Click Events
        this.contactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                controller.editContact(position);
            }
        });
    }

    /*
        Option Menu Callbacks
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch ( item.getItemId() )
        {
            case R.id.add_contact_menu_item:
                this.controller.createNewContact();
                break;

            case R.id.clear_all_contacts_menu_item:
                this.controller.removeAllContacts();
                break;

            case R.id.sort_by_lastname_menu_item:

                break;
        }

        return super.onOptionsItemSelected(item);
    }

}


