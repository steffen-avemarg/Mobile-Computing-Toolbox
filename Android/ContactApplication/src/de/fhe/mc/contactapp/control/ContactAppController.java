package de.fhe.mc.contactapp.control;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import de.fhe.mc.contactapp.model.Contact;
import de.fhe.mc.contactapp.model.ContactList;
import de.fhe.mc.contactapp.util.StorageWrapper;
import de.fhe.mc.contactapp.view.EditContactActivity;

public class ContactAppController extends Application
{
    private static final String LOG_TAG = "AppController";

    private static Context context;
    private ContactList contacts;

    public void onCreate()
    {
        super.onCreate();
        context = getApplicationContext();
        contacts = StorageWrapper.loadContacts();
    }

    /*
        Model related Methods
        Mainly just delegate methods that map to the corresponding
        ContactList methods
     */

    public Contact getContact( int contactID )
    {
        return getContacts().getContactList().get( contactID );
    }

    public void saveNewContact( String lastname, String firstname, String phone, String mail )
    {
        Contact c = new Contact( lastname, firstname, phone, mail );
        getContacts().addContact( c );

        this.saveContacts();
    }

    public void updateContact( int contactID, String lastname, String firstname, String phone, String mail )
    {
        getContacts().updateContact( contactID, lastname, firstname, phone, mail );

        this.saveContacts();
    }

    public void removeAllContacts()
    {
        getContacts().removeAllContacts();

        this.saveContacts();
    }


    /*
        Navigation/App related Methods
     */
    public void createNewContact()
    {
        editContact( EditContactActivity.NEW_CONTACT );
    }

    public void editContact( int contactID )
    {
        Intent i = new Intent( getAppContext(), EditContactActivity.class );

        i.putExtra( EditContactActivity.KEY_CONTACT_ID, contactID );
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        getAppContext().startActivity(i);
    }


    /*
        Global Helper
     */

    // Access to Context
    public static Context getAppContext()
    {
        return context;
    }

    // Access to Contact List
    public ContactList getContacts()
    {
        return this.contacts;
    }

    private void saveContacts()
    {
        StorageWrapper.saveContacts( getContacts() );
    }


}
