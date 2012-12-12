package de.fhe.ai.pmc.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Division 
{
	private String name;
	private String address;
	
	List<Employee> employees = new ArrayList<Employee>();
	
	public Division() {}

	public Division(String name, String address) {
		super();
		this.name = name;
		this.address = address;
	}

	@XmlAttribute
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@XmlElement( name = "employee" )
	@XmlElementWrapper( name = "employees" )
	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	};
	
	@XmlTransient
	@JsonIgnore
	public double getTotalSalary()
	{
		double totalSum = 0.0;
		
		for( Employee e : this.employees )
		{
			totalSum += e.getSalary();
		}
		
		return totalSum;
	}
	
	@XmlTransient
	@JsonIgnore
	public int getNumberOfEmployees()
	{
		return this.employees.size();
	}

	@Override
	public String toString() 
	{
		return this.name + " / " + this.address + "\n" +
				"No. of Employees: " + this.getNumberOfEmployees() + 
				" - Total Salary: " + this.getTotalSalary(); 
	}
	
	
}
