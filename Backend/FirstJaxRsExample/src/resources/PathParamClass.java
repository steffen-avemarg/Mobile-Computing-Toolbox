package resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path( "/" )
public class PathParamClass
{
	@GET
	@Path( "/regions/{regionID}/stations/{stationID}" )
	public String getStationDetails(
			@PathParam( "regionID" ) int regionID,
			@PathParam( "stationID" ) int stationID )
	{
		System.out.println( "RegionID:  " + regionID );
		System.out.println( "StationID: " + stationID );

		return "Stationsdetails";
	}
}
