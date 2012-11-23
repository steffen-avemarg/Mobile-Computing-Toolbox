package de.emgress.backend.friedolin.model;


/*
	Friedolin XML

	 <Semester>
        <SemId>20051</SemId>
        <Status>Archiv</Status>
        <Kurztext>SS 2005</Kurztext>
        <Drucktext>Sommersemester 2005</Drucktext>
        <Langtext>Sommersemester 2005</Langtext>
        <k_semester.von>01.04.2005</k_semester.von>
        <k_semester.bis>30.09.2005</k_semester.bis>
        <k_semester.vorlesungvon>11.04.2005</k_semester.vorlesungvon>
        <k_semester.vorlesungbis>16.07.2005</k_semester.vorlesungbis>
        <AktSem />
    </Semester>

 */

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement( name = "Semester" )
public class Semester
{
	private int semId;
	private SemesterStatus status;
	private String shortText;
	private String longText;
	private Date from;
	private Date to;
	private Date lecturesFrom;
	private Date lecturesTo;

	public Semester() {}

	@XmlElement( name = "k_semester.von" )
	public Date getFrom()
	{
		return from;
	}

	public void setFrom(Date from)
	{
		this.from = from;
	}

	@XmlElement( name = "k_semester.vorlesungvon" )
	public Date getLecturesFrom()
	{
		return lecturesFrom;
	}

	public void setLecturesFrom(Date lecturesFrom)
	{
		this.lecturesFrom = lecturesFrom;
	}

	@XmlElement( name = "k_semester.vorlesungbis" )
	public Date getLecturesTo()
	{
		return lecturesTo;
	}

	public void setLecturesTo(Date lecturesTo)
	{
		this.lecturesTo = lecturesTo;
	}

	@XmlElement( name = "Langtext" )
	public String getLongText()
	{
		return longText;
	}

	public void setLongText(String longText)
	{
		this.longText = longText;
	}

	@XmlElement( name = "SemId" )
	public int getSemId()
	{
		return semId;
	}

	public void setSemId(int semId)
	{
		this.semId = semId;
	}

	@XmlElement( name = "Kurztext" )
	public String getShortText()
	{
		return shortText;
	}

	public void setShortText(String shortText)
	{
		this.shortText = shortText;
	}

	@XmlElement( name = "Status" )
	public SemesterStatus getStatus()
	{
		return status;
	}

	public void setStatus(SemesterStatus status)
	{
		this.status = status;
	}

	@XmlElement( name = "k_semester.bis" )
	public Date getTo()
	{
		return to;
	}

	public void setTo(Date to)
	{
		this.to = to;
	}
}
