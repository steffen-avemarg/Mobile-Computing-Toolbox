package test;

import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.mock.MockDispatcherFactory;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.jboss.resteasy.plugins.server.resourcefactory.POJOResourceFactory;
import resources.PathParamClass;

public class PathParamClassTest
{
	public static void main(String[] args) throws Exception
	{
		Dispatcher dispatcher = MockDispatcherFactory.createDispatcher();

		POJOResourceFactory noDefaults =
				new POJOResourceFactory( PathParamClass.class );
		dispatcher.getRegistry().addResourceFactory( noDefaults );

		MockHttpRequest request1 = MockHttpRequest.get("/regions/12/stations/43");

		MockHttpResponse response = new MockHttpResponse();

		dispatcher.invoke(request1, response);
		System.out.println( "Response: " + response.getContentAsString() );
		response.reset();
	}
}
