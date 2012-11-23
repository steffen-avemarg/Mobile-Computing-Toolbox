package de.emgress.backend.rest.model;

import de.emgress.backend.rest.model.constants.Title;

public class Person
{
	private String lastName;
	private String firstName;
	private Title title;
	private String role;

	public Person() {}

	public Person(String firstName, String lastName, String role, Title title)
	{
		this();
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.title = title;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getRole()
	{
		return role;
	}

	public void setRole(String role)
	{
		this.role = role;
	}

	public Title getTitle()
	{
		return title;
	}

	public void setTitle(Title title)
	{
		this.title = title;
	}
}
