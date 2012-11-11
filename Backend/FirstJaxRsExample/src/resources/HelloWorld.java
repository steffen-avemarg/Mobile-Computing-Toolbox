package resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path( "/helloworld" )
public class HelloWorld
{
    @GET
    @Produces( MediaType.TEXT_PLAIN )
    public String getMessage()
	{
        return "Hello World";
    }

	@GET
	@Produces( MediaType.APPLICATION_XML )
	public String getXMLMessage()
	{
		return "<message>Hello World</message>";
	}
}