package model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Station {
	private int id;
	private String name;

	public Station() {}

	public Station( int id, String name ) {
		this.id = id;
		this.name = name;
	}

	@XmlAttribute
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	@XmlElement
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	@Override
	public String toString()
	{
		return this.id + ": " + this.name;
	}

}
