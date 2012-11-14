package test;


import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.mock.MockDispatcherFactory;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.jboss.resteasy.plugins.server.resourcefactory.POJOResourceFactory;
import resources.StationResource;

import javax.ws.rs.core.MediaType;

public class MarshallingTest
{

	public static void main(String[] args) throws Exception
	{
		Dispatcher dispatcher = MockDispatcherFactory.createDispatcher();

		POJOResourceFactory noDefaults = new POJOResourceFactory(StationResource.class);
		dispatcher.getRegistry().addResourceFactory(noDefaults);

		MockHttpRequest requestXml = MockHttpRequest.get("/station");
		requestXml.accept( MediaType.APPLICATION_XML );

		MockHttpRequest requestJson = MockHttpRequest.get("/station");
		requestJson.accept( MediaType.APPLICATION_JSON );

		MockHttpResponse response = new MockHttpResponse();

		dispatcher.invoke(requestXml, response);
		System.out.println( "Response XML:\t" + response.getContentAsString() );
		response.reset();
		dispatcher.invoke(requestJson, response);
		System.out.println( "Response JSON:\t" + response.getContentAsString() );

	}
}
