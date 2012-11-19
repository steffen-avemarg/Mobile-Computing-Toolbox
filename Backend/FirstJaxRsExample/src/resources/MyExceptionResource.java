package resources;

import exception.MyException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path( "/error" )
public class MyExceptionResource
{
	@GET
	public String getData() throws MyException
	{
		throw new MyException( "Error Message" );
	}
}
