package de.fhe.mc.contactapp.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import de.fhe.mc.contactapp.R;
import de.fhe.mc.contactapp.control.ContactAppController;
import de.fhe.mc.contactapp.model.Contact;
import de.fhe.mc.contactapp.util.StorageWrapper;

public class EditContactActivity extends Activity
{
    private static final String LOG_TAG = "EditContactActivity";

    public static final String KEY_CONTACT_ID = "ContactID";
    public static final int NEW_CONTACT = -1;

    private ContactAppController controller;

    private int contactID;

    private EditText lastnameField;
    private EditText firstnameField;
    private EditText phoneField;
    private EditText mailField;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.edit_contact );

        this.controller = (ContactAppController)getApplication();

        // Get Reference to all Textfields
        this.lastnameField = (EditText)findViewById( R.id.edit_lastname_input );
        this.firstnameField = (EditText)findViewById( R.id.edit_firstname_input );
        this.phoneField = (EditText)findViewById( R.id.edit_phone_input );
        this.mailField = (EditText)findViewById( R.id.edit_mail_input );
    }


    /*
        Handle Press of the Buttons
        In case of the Save Button we
     */
    public void handleButtons( View v )
    {
        // Save Button
        if (v.getId() == R.id.edit_button_save)
        {
            String lastname = lastnameField.getText().toString();
            String firstname = firstnameField.getText().toString();

            // Check if lastname and
            if( lastname.trim().length() == 0 || firstname.trim().length() == 0 )
            {
                Toast.makeText( this, "Lastname and Firstname must not be empty!", 500).show();
            }
            else
            {
                String phone = phoneField.getText().toString();
                String mail = mailField.getText().toString();

                if (contactID != NEW_CONTACT)
                    controller.updateContact(contactID, lastname,firstname, phone, mail );
                else
                    controller.saveNewContact( lastname,firstname, phone, mail );

                finish();
            }
        }
        // Cancel Button
        else
        {
            finish();
        }
    }




    @Override
    protected void onResume()
    {
        super.onResume();

        // Check Intent - did we get a Contact ID? E.g. are we in
        // creation or editing mode?
        Intent i = getIntent();

        if( i.getExtras() != null )
        {
            this.contactID = i.getExtras().getInt( KEY_CONTACT_ID, NEW_CONTACT );
        }

        // We probably got a valid contact id,
        // so we prefill the input fields
        if( contactID != NEW_CONTACT )
        {
            Contact c = this.controller.getContact( contactID );

            if( c != null )
            {
                this.lastnameField.setText( c.getLastname() );
                this.firstnameField.setText( c.getFirstname() );
                this.phoneField.setText( c.getPhone() );
                this.mailField.setText( c.getEmail() );
            }
        }
        // New Contact - empty all fields
        else
        {
            this.lastnameField.setText( null );
            this.firstnameField.setText( null );
            this.phoneField.setText( null );
            this.mailField.setText( null );
        }
    }
}
