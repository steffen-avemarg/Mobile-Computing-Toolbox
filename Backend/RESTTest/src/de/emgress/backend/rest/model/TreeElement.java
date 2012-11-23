package de.emgress.backend.rest.model;

import de.emgress.backend.rest.model.constants.TreeElementType;

import java.util.ArrayList;
import java.util.List;

public class TreeElement
{
    private int id;
	private TreeElementType type;
    private String name;

    private List<TreeElement> children;
	private List<TreeElementCourseWrapper> courses;

    public TreeElement()
    {
		this.type = TreeElementType.NODE;
        this.children = new ArrayList<TreeElement>();
		this.courses = new ArrayList<TreeElementCourseWrapper>();
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

	public TreeElement(int id, TreeElementType type, String name)
	{
		this( id, name );
		this.type = type;
	}

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

	public TreeElementType getType()
	{
		return type;
	}

	public void setType(TreeElementType type)
	{
		this.type = type;
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

	public List<TreeElementCourseWrapper> getCourses()
	{
		return courses;
	}

	public void setCourses(List<TreeElementCourseWrapper> courses)
	{
		this.courses = courses;
	}

	@Override
	public String toString()
	{
		return this.getName();
	}
}
