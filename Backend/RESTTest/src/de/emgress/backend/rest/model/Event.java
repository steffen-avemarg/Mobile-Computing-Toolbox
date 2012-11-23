package de.emgress.backend.rest.model;


import de.emgress.backend.rest.model.constants.DayOfWeek;
import de.emgress.backend.rest.model.constants.EventRecurrence;

import java.util.Date;

public class Event
{
	private DayOfWeek dayOfWeek;
	private Date startTime;
	private Date endTime;
	private Date startDate;
	private Date endDate;
	private EventRecurrence recurrence;
	private Room room;
	private Person lecturer;

	public Event() {}

	public Event(Person lecturer, DayOfWeek dayOfWeek, EventRecurrence recurrence, Room room,
				 Date startDate, Date endDate, Date startTime, Date endTime )
	{
		this();
		this.dayOfWeek = dayOfWeek;
		this.endDate = endDate;
		this.endTime = endTime;
		this.lecturer = lecturer;
		this.recurrence = recurrence;
		this.room = room;
		this.startDate = startDate;
		this.startTime = startTime;
	}

	public DayOfWeek getDayOfWeek()
	{
		return dayOfWeek;
	}

	public void setDayOfWeek(DayOfWeek dayOfWeek)
	{
		this.dayOfWeek = dayOfWeek;
	}

	public Date getEndDate()
	{
		return endDate;
	}

	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}

	public Date getEndTime()
	{
		return endTime;
	}

	public void setEndTime(Date endTime)
	{
		this.endTime = endTime;
	}

	public Person getLecturer()
	{
		return lecturer;
	}

	public void setLecturer(Person lecturer)
	{
		this.lecturer = lecturer;
	}

	public EventRecurrence getRecurrence()
	{
		return recurrence;
	}

	public void setRecurrence(EventRecurrence recurrence)
	{
		this.recurrence = recurrence;
	}

	public Room getRoom()
	{
		return room;
	}

	public void setRoom(Room room)
	{
		this.room = room;
	}

	public Date getStartDate()
	{
		return startDate;
	}

	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}

	public Date getStartTime()
	{
		return startTime;
	}

	public void setStartTime(Date startTime)
	{
		this.startTime = startTime;
	}

	@Override
	public String toString()
	{
		return this.getDayOfWeek().toString();
	}
}
