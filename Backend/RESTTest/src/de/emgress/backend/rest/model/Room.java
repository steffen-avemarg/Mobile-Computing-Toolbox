package de.emgress.backend.rest.model;

public class Room
{
	protected String name;
	protected String short_name;
	protected String building;
	protected String street;
	protected String postalCode;
	protected String city;
	protected String floor;
	protected String type;
	protected String[] equipment = {};
	protected Integer capacity;
	protected String remarks;

	public Room()
	{
	}

	public String getBuilding()
	{
		return building;
	}

	public void setBuilding(String building)
	{
		this.building = building;
	}

	public Integer getCapacity()
	{
		return capacity;
	}

	public void setCapacity(Integer capacity)
	{
		this.capacity = capacity;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String[] getEquipment()
	{
		return equipment;
	}

	public void setEquipment(String[] equipment)
	{
		this.equipment = equipment;
	}

	public String getFloor()
	{
		return floor;
	}

	public void setFloor(String floor)
	{
		this.floor = floor;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getPostalCode()
	{
		return postalCode;
	}

	public void setPostalCode(String postalCode)
	{
		this.postalCode = postalCode;
	}

	public String getRemarks()
	{
		return remarks;
	}

	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}

	public String getShort_name()
	{
		return short_name;
	}

	public void setShort_name(String short_name)
	{
		this.short_name = short_name;
	}

	public String getStreet()
	{
		return street;
	}

	public void setStreet(String street)
	{
		this.street = street;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}
}
