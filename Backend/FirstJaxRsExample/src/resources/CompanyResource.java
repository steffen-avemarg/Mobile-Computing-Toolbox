package resources;

import model.Line;
import model.LineColor;
import model.PublicTransportCompany;
import model.Station;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/company")
public class CompanyResource
{
	@GET
	@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON } )
	public PublicTransportCompany getCompany()
	{
		PublicTransportCompany ptc = new PublicTransportCompany( "Örtlicher Nahverkehr" );

		ptc.getLines().add( new Line(LineColor.BLUE, "Tram 11") );
		ptc.getLines().add( new Line(LineColor.RED, "Bus 40") );
		ptc.getLines().add( new Line(LineColor.YELLOW, "U-Bahn 23") );

		ptc.getStations().add( new Station( 12345, "Westbahnhof" ) );
		ptc.getStations().add( new Station( 54321, "Ostbahnhof" ) );
		ptc.getStations().add( new Station( 15243, "Südbahnhof" ) );

		return ptc;
	}


}
