package model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Line
{
	private String name;
	private LineColor color;

	public Line() {}

	public Line( LineColor color, String name )
	{
		this.color = color;
		this.name = name;
	}

	@XmlAttribute
	public LineColor getColor()
	{
		return color;
	}

	public void setColor(LineColor color)
	{
		this.color = color;
	}

	@XmlAttribute
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}
