package de.fhe.mc.contactapp.util;

import android.content.Context;
import android.util.Log;
import de.fhe.mc.contactapp.control.ContactAppController;
import de.fhe.mc.contactapp.model.ContactList;

import java.io.*;

public class StorageWrapper
{
    private static final String LOG_TAG = "Storage";
    private static final String FILE_NAME = "contacts.obj";

    public static ContactList loadContacts()
    {
        Context context = ContactAppController.getAppContext();

        ContactList contacts;
        FileInputStream fis = null;

        try
        {
            fis = context.openFileInput( FILE_NAME );
            ObjectInputStream ois = new ObjectInputStream( fis );

            contacts = (ContactList)ois.readObject();
            // Due to the fact that the Observer List is transient,
            // we need to init a new one after the object has been
            // deserialized
            contacts.initObserverList();
        }
        catch (Exception ioe)
        {
            Log.e(LOG_TAG, "Error while reading InputStream - creating new, empty model");

            // In Case of an Error (probably first app start), we
            // create a new empty contact list
            contacts = new ContactList();
        }
        finally
        {
            if( fis != null )
            {
                try
                {
                    fis.close();
                }
                catch (IOException e)
                {
                    Log.e(LOG_TAG, "Error while closing FileInputStream");
                }
            }
        }

        return contacts;
    }

    public static void saveContacts( ContactList contacts )
    {
        Context context = ContactAppController.getAppContext();

        FileOutputStream fos = null;
        try
        {
            fos = context.openFileOutput( FILE_NAME, Context.MODE_PRIVATE );
            ObjectOutputStream oos = new ObjectOutputStream( fos );

            oos.writeObject( contacts );
            oos.flush();
        }
        catch (IOException ioe )
        {
            Log.e(LOG_TAG, "Error while writing OutputStream");
        }
        finally
        {
            if( fos != null )
            {
                try
                {
                    fos.close();
                }
                catch (IOException e)
                {
                    Log.e(LOG_TAG, "Error while Closing FileOutputStream");
                }
            }
        }
    }

}
