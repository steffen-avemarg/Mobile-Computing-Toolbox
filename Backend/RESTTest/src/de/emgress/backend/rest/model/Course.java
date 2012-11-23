package de.emgress.backend.rest.model;

import de.emgress.backend.rest.model.constants.CourseType;
import de.emgress.backend.rest.model.constants.Language;

import java.util.ArrayList;

public class Course
{
	private int id;
	private String title;
	private CourseType type;
	private String semester;
	private Language language;
	private int sws;
	private ArrayList<Person> persons;
	private ArrayList<Event> events;
	private String remarks;
	private String literature;
	private String description;
	private String prerequisites;
	private String certificates;
	private String uri;

	public Course()
	{
		this.persons = new ArrayList<Person>();
		this.events = new ArrayList<Event>();
	}

	public Course(int id, String title, Language language)
	{
		this();
		this.id = id;
		this.title = title;
		this.language = language;
	}

	public String getCertificates()
	{
		return certificates;
	}

	public void setCertificates(String certificates)
	{
		this.certificates = certificates;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public ArrayList<Event> getEvents()
	{
		return events;
	}

	public void setEvents(ArrayList<Event> events)
	{
		this.events = events;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public Language getLanguage()
	{
		return language;
	}

	public void setLanguage(Language language)
	{
		this.language = language;
	}

	public String getLiterature()
	{
		return literature;
	}

	public void setLiterature(String literature)
	{
		this.literature = literature;
	}

	public ArrayList<Person> getPersons()
	{
		return persons;
	}

	public void setPersons(ArrayList<Person> persons)
	{
		this.persons = persons;
	}

	public String getPrerequisites()
	{
		return prerequisites;
	}

	public void setPrerequisites(String prerequisites)
	{
		this.prerequisites = prerequisites;
	}

	public String getRemarks()
	{
		return remarks;
	}

	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}

	public String getSemester()
	{
		return semester;
	}

	public void setSemester(String semester)
	{
		this.semester = semester;
	}

	public int getSws()
	{
		return sws;
	}

	public void setSws(int sws)
	{
		this.sws = sws;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public CourseType getType()
	{
		return type;
	}

	public void setType(CourseType type)
	{
		this.type = type;
	}

	public String getUri()
	{
		return uri;
	}

	public void setUri(String uri)
	{
		this.uri = uri;
	}

	@Override
	public String toString()
	{
		return this.getTitle();
	}
}
