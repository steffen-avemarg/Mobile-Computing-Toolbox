package de.emgress.backend.rest.model;

import de.emgress.backend.rest.model.constants.CourseType;
import org.codehaus.jackson.annotate.JsonIgnore;

public class TreeElementCourseWrapper
{
	private Course wrappedCourse;

	public TreeElementCourseWrapper()
	{
		this.wrappedCourse = new Course();
	}

	public TreeElementCourseWrapper( Course course )
	{
		this.wrappedCourse = course;
	}

	public int getId()
	{
		return this.wrappedCourse.getId();
	}

	public String getTitle()
	{
		boolean firstPerson = true;
		String personList = "";
		for( Person person : this.wrappedCourse.getPersons() )
		{
			if( firstPerson )
			{
				firstPerson = false;
			}
			else
			{
				personList += ", ";
			}

			personList += person.getTitle() + " " + person.getLastName();
		}

		return this.wrappedCourse.getTitle() + " - " + personList;
	}

	public CourseType getType()
	{
		return this.wrappedCourse.getType();
	}

	public String getUri()
	{
		return this.wrappedCourse.getUri();
	}

	@JsonIgnore
	public Course getWrappedCourse()
	{
		return wrappedCourse;
	}

	public void setWrappedCourse(Course wrappedCourse)
	{
		this.wrappedCourse = wrappedCourse;
	}
}
