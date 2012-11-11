package resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

public class RegionResource
{
	private int regionID;

	public RegionResource( int regionID )
	{
		this.regionID = regionID;
	}

	@GET
	public String getRegionDetails()
	{
		return "Regionsdetails for Region " + this.regionID;
	}

	@GET
	@Path( "geo" )
	public String getLocation()
	{
		return "51.000 / 12.000";
	}
}
