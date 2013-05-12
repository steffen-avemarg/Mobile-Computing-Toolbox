package de.fhe.mc.contactapp.model;

import java.io.Serializable;
import java.util.List;

public class Contact implements Serializable
{
    private static final String LOG_TAG = "Contact_Class";

    private String lastname;
    private String firstname;
    private String email;
    private String phone;

    public Contact(String lastname, String firstname, String phone, String email )
    {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;

    }

    /*
        Getter & Setter
     */

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    public String getLastname()
    {
        return lastname;
    }

    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    /*
        Overriding this is necessary if you want to use this
         class with a simple ArrayAdaper and a simple_list_item_1
     */
    @Override
    public String toString()
    {
        return this.firstname + " " + this.lastname;
    }
}
