package de.fhe.mc.contactapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ContactList implements Serializable
{
    public interface OnChangeListener
    {
        public void onContactListChange();
    }

    List<Contact> contactList = new ArrayList<Contact>();
    transient List<OnChangeListener> observer = new ArrayList<OnChangeListener>();

    public ContactList() {}

    public void addContact( Contact contact )
    {
        this.contactList.add( contact );
        notifyAllObserver();
    }

    public void removeContact( Contact contact )
    {
        if( this.contactList.remove( contact ) )
            notifyAllObserver();
    }

    public void updateContact( int contactID, String lastname, String firstname, String phone, String mail )
    {
        Contact c = this.contactList.get( contactID );

        boolean notify = false;

        if( c != null )
        {
            if( lastname != null )
            {
                c.setLastname( lastname );
                notify = true;
            }
            if( firstname != null )
            {
                c.setFirstname( firstname );
                notify = true;
            }
            if( phone != null )
            {
                c.setPhone( phone );
                notify = true;
            }
            if( mail != null )
            {
                c.setEmail( mail );
                notify = true;
            }

            if( notify ) notifyAllObserver();
        }
    }

    public void removeAllContacts()
    {
        this.contactList.clear();
        notifyAllObserver();
    }

    public void registerObserver( OnChangeListener observer )
    {
        this.observer.add( observer );
    }

    public void removeObserver( OnChangeListener observer )
    {
        this.observer.remove( observer );
    }

    public List<Contact> getContactList()
    {
        return contactList;
    }

    private void notifyAllObserver()
    {
        for( OnChangeListener ocl : this.observer )
            ocl.onContactListChange();
    }

    public void initObserverList()
    {
        this.observer = new ArrayList<OnChangeListener>();
    }
}
