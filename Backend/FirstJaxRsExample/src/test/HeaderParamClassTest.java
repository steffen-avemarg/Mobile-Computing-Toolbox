package test;

import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.mock.MockDispatcherFactory;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.jboss.resteasy.plugins.server.resourcefactory.POJOResourceFactory;
import resources.HeaderParamClass;

public class HeaderParamClassTest
{
	public static void main(String[] args) throws Exception
	{
		Dispatcher dispatcher = MockDispatcherFactory.createDispatcher();

		POJOResourceFactory noDefaults =
				new POJOResourceFactory( HeaderParamClass.class );
		dispatcher.getRegistry().addResourceFactory( noDefaults );

		MockHttpRequest request = MockHttpRequest.get("/info");
		request.header( "User-Agent", "My Example Client" );
		MockHttpResponse response = new MockHttpResponse();

		dispatcher.invoke(request, response);
		System.out.println("Response: " + response.getContentAsString());
	}
}
