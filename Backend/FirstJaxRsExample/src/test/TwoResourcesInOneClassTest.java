package test;

import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.mock.MockDispatcherFactory;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.jboss.resteasy.plugins.server.resourcefactory.POJOResourceFactory;
import resources.TwoResourcesInOneClass;

public class TwoResourcesInOneClassTest
{
	public static void main(String[] args) throws Exception
	{
		Dispatcher dispatcher = MockDispatcherFactory.createDispatcher();

		POJOResourceFactory noDefaults =
				new POJOResourceFactory( TwoResourcesInOneClass.class );
		dispatcher.getRegistry().addResourceFactory( noDefaults );

		MockHttpRequest request1 = MockHttpRequest.get("/example/message1");
		MockHttpRequest request2 = MockHttpRequest.get("/example/message2");

		MockHttpResponse response = new MockHttpResponse();

		dispatcher.invoke(request1, response);
		System.out.println( "Response 1: " + response.getContentAsString() );
		response.reset();
		dispatcher.invoke(request2, response);
		System.out.println( "Response 2: " + response.getContentAsString() );
	}
}