package resources;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;

@Path( "/info" )
public class HeaderParamClass
{
	@GET
	public String getUserAgent(
			@HeaderParam( "User-Agent" ) String userAgent
	)
	{
		return userAgent;
	}
}
