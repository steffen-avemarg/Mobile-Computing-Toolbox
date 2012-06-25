package de.emgress.android.expandableadapter;

import java.util.ArrayList;
import java.util.List;

public class GroupClass
{
    private String name;
    private List<ChildClass> children;

    public GroupClass()
    {
        this( "not set" );
    }

    public GroupClass( String name )
    {
        this.name = name;
        this.children = new ArrayList<ChildClass>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ChildClass> getChildren() {
        return children;
    }

    public void setChildren( List<ChildClass> children ) {
        this.children = children;
    }

    public ChildClass getChild(int position)
    {
        return this.getChildren().get( position );
    }

    public void addChild( ChildClass child )
    {
        this.getChildren().add( child );
    }

    public void addChild( int position, ChildClass child )
    {
        this.getChildren().add( position, child );
    }

    public void removeChild( ChildClass child )
    {
        this.getChildren().remove( child );
    }

    public void removeChild( int position )
    {
        this.getChildren().remove( position );
    }

    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append( "Group: " + this.name + " / No. of Children: " + this.getChildren().size() );

        return sb.toString();
    }

}
