package de.emgress.backend.rest.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class TreeElement
{
    private int id;
    private String name;

    @JsonIgnore
    private List<TreeElement> children;

    public TreeElement()
    {
        this.children = new ArrayList<TreeElement>();
    }

    public TreeElement(int id)
    {
        this();
        this.id = id;
    }

    public TreeElement(int id, String name)
    {
        this( id );
        this.name = name;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<TreeElement> getChildren() {
        return children;
    }

    public void setChildren(List<TreeElement> children) {
        this.children = children;
    }
}
