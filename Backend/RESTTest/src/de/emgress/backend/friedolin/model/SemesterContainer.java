package de.emgress.backend.friedolin.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement( name = "semester" )
public class SemesterContainer
{
	private List<Semester> semesterList;

	public SemesterContainer() {}

	@XmlElement( name = "Semester" )
	public List<Semester> getSemesterList()
	{
		return semesterList;
	}

	public void setSemesterList(List<Semester> semesterList)
	{
		this.semesterList = semesterList;
	}
}
