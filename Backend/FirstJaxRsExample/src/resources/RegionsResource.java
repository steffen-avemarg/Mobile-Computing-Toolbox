package resources;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path( "/regions" )
public class RegionsResource
{
	@Path( "{regionID}" )
	public RegionResource getRegion(
			@PathParam( "regionID" ) int regionID
	)
	{
		RegionResource rr = new RegionResource( regionID );
		return rr;
	}
}
