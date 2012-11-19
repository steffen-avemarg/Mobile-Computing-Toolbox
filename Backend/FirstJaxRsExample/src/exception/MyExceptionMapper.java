package exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class MyExceptionMapper implements ExceptionMapper<MyException>
{
	@Override
	public Response toResponse(MyException e)
	{
		System.out.println( "Mapping Exception: " + e.getMessage() );

		return Response.status( Response.Status.INTERNAL_SERVER_ERROR )
				       .entity(e.getMessage())
				       .build() ;
	}
}
