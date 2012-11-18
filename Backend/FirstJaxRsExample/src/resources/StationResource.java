package resources;

import model.Station;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

@Path( "/station" )
public class StationResource
{
	@GET
	@Produces( MediaType.APPLICATION_XML )
	public Station getStationAsXml() {
		return new Station( 12345, "Hauptbahnhof" );
	}

	@GET
	@Produces( MediaType.APPLICATION_JSON )
	public Station getStationAsJson() {
		return new Station( 54321, "Südbahnhof" );
	}

	@POST
	@Consumes( MediaType.APPLICATION_JSON )
	public Response createStation( Station s ) throws URISyntaxException
	{
		System.out.println( s );

		return Response.created( new URI( "http://server/station/134" ) ).build();
	}
}
