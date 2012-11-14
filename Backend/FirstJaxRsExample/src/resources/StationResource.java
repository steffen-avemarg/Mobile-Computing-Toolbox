package resources;

import model.Station;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
}
