package resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path( "/query" )
public class QueryParamClass
{
	@GET
	@Produces(MediaType.TEXT_PLAIN )
	public String handleQuery(
			@QueryParam( "phrase" ) @DefaultValue( "empty" ) String pharse
	)
	{
		System.out.println( "Search Phrase: " + pharse );

		return "Handled";
	}
}
