package de.emgress.android.expandableadapter;


public class ChildClass
{
    private String name;
    private String text;

    public ChildClass()
    {
        this.name = "not set";
        this.text = "not set";
    }

    public ChildClass( String name, String text )
    {
        this.name = name;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString()
    {
        return "Child: " + this.name + " / " + this.text;
    }
}
