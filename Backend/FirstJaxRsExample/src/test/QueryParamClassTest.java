package test;

import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.mock.MockDispatcherFactory;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.jboss.resteasy.plugins.server.resourcefactory.POJOResourceFactory;
import resources.QueryParamClass;

public class QueryParamClassTest
{
	public static void main(String[] args) throws Exception
	{
		Dispatcher dispatcher = MockDispatcherFactory.createDispatcher();

		POJOResourceFactory noDefaults =
				new POJOResourceFactory( QueryParamClass.class );
		dispatcher.getRegistry().addResourceFactory( noDefaults );

		MockHttpRequest request1 = MockHttpRequest.get("/query");
		MockHttpRequest request2 = MockHttpRequest.get("/query?phrase=Mobile%20Computing");

		MockHttpResponse response = new MockHttpResponse();

		dispatcher.invoke(request1, response);
		System.out.println("Response: " + response.getContentAsString());
		response.reset();
		dispatcher.invoke(request2, response);
		System.out.println("Response: " + response.getContentAsString());
	}
}
