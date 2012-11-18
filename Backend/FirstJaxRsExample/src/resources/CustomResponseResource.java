package resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/")
public class CustomResponseResource
{

	@GET
	@Path( "ok" )
	public Response getOkResponse()
	{
		return Response.ok().build();
	}

	@GET
	@Path( "notfound")
	public Response getNotFoundRespone()
	{
		return Response.status( Response.Status.NOT_FOUND )
					   .header( "custom-header", "custom header value")
					   .build();
	}

}
