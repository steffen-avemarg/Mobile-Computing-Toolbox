package resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path( "/example" )
public class TwoResourcesInOneClass
{
	@GET
	@Path( "message1" )
	@Produces( MediaType.TEXT_PLAIN )
	public String getMessage01()
	{
		return "First Message";
	}

	@GET
	@Path( "message2" )
	@Produces( MediaType.TEXT_PLAIN )
	public String getMessage02()
	{
		return "Second Message";
	}
}