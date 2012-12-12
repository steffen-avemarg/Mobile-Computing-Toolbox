package de.fhe.ai.pmc.resources;

import de.fhe.ai.pmc.model.DatabaseWrapper;
import de.fhe.ai.pmc.model.Division;
import de.fhe.ai.pmc.model.Employee;
import de.fhe.ai.pmc.model.InfoMessage;
import org.jboss.resteasy.spi.BadRequestException;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path( "/" )
public class ProjectRootResource 
{
	/*
		Just some stuff
	 */

	@GET
	@Produces( MediaType.TEXT_PLAIN )
	public String handleRootRequest()
	{
		return "PMC Demo Application"; 
	}
	
	@GET
	@Path( "info" )
	@Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
	public InfoMessage handleInfoRequest()
	{
		return new InfoMessage( "PMC Demo Application" );
	}

	/*
		Division related Methods
	 */

	@GET
	@Path("divisions")
	@Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
	public List<Division> getDivisions()
	{
		return DatabaseWrapper.getDivisions(); 
	}

	@POST
	@Path("divisions")
	@Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
	public Response createDivision(Division newDivision,
								   @Context UriInfo uriInfo )
	{
		DatabaseWrapper.getDivisions().add( newDivision );

		String newResourceLocation = uriInfo.getRequestUri().toString() + "/" + DatabaseWrapper.getDivisions().size();

		return Response.created( URI.create( newResourceLocation ) ).build();
	}
	
	@GET
	@Path("divisions/{did}")
	@Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
	public Division getDivision( @PathParam( "did" ) int divisionId )
	{
		if( divisionId < 1 || divisionId > DatabaseWrapper.getDivisions().size() )
		{
			throw new BadRequestException( "Division ID must be between 1 and 10" );
		}
		else
		{
			return DatabaseWrapper.getDivisions().get( divisionId - 1 ); 
		}
	}

	@PUT
	@Path("divisions/{did}")
	@Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
	public Response updateDivision( @PathParam( "did" ) int divisionId,
								 Division newDivision)
	{
		Division oldDivision = this.getDivision( divisionId );

		if( newDivision.getName() != null )
			oldDivision.setName( newDivision.getName() );
		if( newDivision.getAddress() != null )
			oldDivision.setAddress( newDivision.getAddress() );

		return Response.ok().build();
	}
	
	@DELETE
	@Path("divisions/{did}")
	public Response deleteDivision( @PathParam( "did" ) int divisionId )
	{
		Division d = this.getDivision( divisionId );
		this.getDivisions().remove( d );
		
		return Response.ok().build();
	}
	
	@GET
	@Path("divisions/{did}/employees")
	@Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
	public List<Employee> getEmployees( @PathParam( "did" ) int divisionId  )
	{
		Division d = this.getDivision( divisionId );
		return d.getEmployees();
	}

	/*
		Employee related Methods
	 */

	@POST
	@Path("divisions/{did}/employees")
	@Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } ) 
	public Response createEmployee( 
			@PathParam( "did" ) int divisionId,
			Employee newEmployee,
			@Context UriInfo uriInfo )
	{
		Division d = this.getDivision( divisionId );

		d.getEmployees().add( newEmployee );

		String newResourceLocation = uriInfo.getRequestUri().toString() + "/" + d.getNumberOfEmployees();

		return Response.created( URI.create(newResourceLocation) ).build();
	}
	
	@GET
	@Path("divisions/{did}/employees/{eid}")
	@Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
	public Employee getEmployee( @PathParam( "did" ) int divisionId,
			@PathParam( "eid" ) int employeeId )
	{
		List<Employee> employees = this.getDivision( divisionId ).getEmployees();
	
		if( employeeId < 1 || employeeId > employees.size() )
			throw new BadRequestException( "Invalid Employee ID. Division " + divisionId + " has " + 
					employees.size() + " employees." );
		else
			return employees.get( employeeId - 1 );
	}

	@PUT
	@Path("divisions/{did}/employees/{eid}")
	@Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
	public Response updateEmployee(
			@PathParam( "did" ) int divisionId,
			@PathParam( "eid" ) int employeeId,
			Employee updatedEmployee )
	{
		Employee oldEmployee = this.getEmployee( divisionId, employeeId );

		if( updatedEmployee.getFirstname() != null )
			oldEmployee.setFirstname( updatedEmployee.getFirstname() );
		if( updatedEmployee.getLastname() != null )
			oldEmployee.setLastname( updatedEmployee.getLastname() );

		oldEmployee.setRoomNumber( updatedEmployee.getRoomNumber() );
		oldEmployee.setSalary( updatedEmployee.getSalary() );

		return Response.ok().build();
	}
	
	@DELETE
	@Path("divisions/{did}/employees/{eid}")
	public Response deleteEmployee( @PathParam( "did" ) int divisionId,
			@PathParam( "eid" ) int employeeId )
	{
		Employee e = this.getEmployee( divisionId, employeeId );
		this.getDivision( divisionId).getEmployees().remove( e );
		
		return Response.ok().build();
	}
	
}
