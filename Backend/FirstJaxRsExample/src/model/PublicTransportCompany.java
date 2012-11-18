package model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class PublicTransportCompany
{
	private String name;
	private List<Station> stations = new ArrayList<Station>();
	private List<Line> lines = new ArrayList<Line>();

	public PublicTransportCompany() {}

	public PublicTransportCompany(String name)
	{
		this.name = name;
	}

	@XmlElement( name = "line" )
	@XmlElementWrapper( name = "lines")
	public List<Line> getLines()
	{
		return lines;
	}

	public void setLines(List<Line> lines)
	{
		this.lines = lines;
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


	@XmlElement( name = "station" )
	@XmlElementWrapper( name = "stations")
	public List<Station> getStations()
	{
		return stations;
	}

	public void setStations(List<Station> stations)
	{
		this.stations = stations;
	}
}
