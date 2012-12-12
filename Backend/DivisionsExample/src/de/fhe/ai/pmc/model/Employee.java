package de.fhe.ai.pmc.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Employee 
{
	private String firstname;
	private String lastname;
	
	private int roomNumber;
	private double salary;

	public Employee() { super(); }

	public Employee(String firstname, String lastname, int roomNumber, double salary) {
		this();
		this.firstname = firstname;
		this.lastname = lastname;
		this.roomNumber = roomNumber;
		this.salary = salary;
	}

	@XmlAttribute
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@XmlAttribute
	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@XmlAttribute
	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	@XmlAttribute
	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() 
	{
		return this.firstname + " " + this.lastname + 
				" - Room: " + this.roomNumber + 
				" - Salary: " + this.salary;
	}
	
	
}
