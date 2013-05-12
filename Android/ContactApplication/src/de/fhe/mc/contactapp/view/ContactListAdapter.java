package de.fhe.mc.contactapp.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import de.fhe.mc.contactapp.R;
import de.fhe.mc.contactapp.control.ContactAppController;
import de.fhe.mc.contactapp.model.Contact;
import de.fhe.mc.contactapp.model.ContactList;

public class ContactListAdapter extends ArrayAdapter<Contact> implements ContactList.OnChangeListener
{
    private ContactAppController controller;

    // Constructor - we just pass in our controller
    // All other parameters to the super call are fixed respectively
    // accessible from the controller
    public ContactListAdapter( ContactAppController controller )
    {
        super( ContactAppController.getAppContext(),
                R.layout.contact_list_entry,
                controller.getContacts().getContactList() );

        this.controller = controller;

        // Get our model and register as observer
        ContactList contactList = this.controller.getContacts();
        contactList.registerObserver( this );
    }

    // View Holder Pattern to efficiently retrieve references
    // to widgets in the list entry layout
    // See http://www.jmanzano.es/blog/?p=166
    public static class ViewHolder
    {
        public TextView nameLabel;
        public TextView phoneLabel;
        public TextView mailLabel;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder;

        if (v == null) {
            LayoutInflater vi =
                    (LayoutInflater)ContactAppController.getAppContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            v = vi.inflate( R.layout.contact_list_entry, null);

            holder = new ViewHolder();
            holder.nameLabel = (TextView)v.findViewById( R.id.cl_name_view );
            holder.phoneLabel = (TextView)v.findViewById( R.id.cl_phone_view );
            holder.mailLabel = (TextView)v.findViewById( R.id.cl_mail_view );

            v.setTag( holder );
        }
        else
        {
            holder=(ViewHolder)v.getTag();
        }

        final Contact c = controller.getContact(position);
        if ( c != null )
        {
            holder.nameLabel.setText( c.getFirstname() + " " + c.getLastname() );

            // Check if we need to show a phone number
            if( c.getPhone() != null && c.getPhone().trim().length() > 0)
                holder.phoneLabel.setText( c.getPhone() );
            else
                holder.phoneLabel.setText( "No Phone" );

            // Check if we need to show a mail address
            if( c.getEmail() != null && c.getEmail().trim().length() > 0)
                holder.mailLabel.setText( c.getEmail() );
            else
                holder.mailLabel.setText( "No Mail" );
        }

        return v;
    }

    /*
        Model Observer Callback
        We simply inform the List View Adapter that the
        underlying data/model has changed
     */
    @Override
    public void onContactListChange()
    {
        this.notifyDataSetChanged();
    }


}
