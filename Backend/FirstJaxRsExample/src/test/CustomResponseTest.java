package test;

import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.mock.MockDispatcherFactory;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.jboss.resteasy.plugins.server.resourcefactory.POJOResourceFactory;
import resources.CustomResponseResource;

import javax.ws.rs.core.MediaType;
import java.net.URISyntaxException;

public class CustomResponseTest
{

	public static void main(String[] args) throws URISyntaxException
	{
		Dispatcher dispatcher = MockDispatcherFactory.createDispatcher();

		POJOResourceFactory noDefaults = new POJOResourceFactory(CustomResponseResource.class);
		dispatcher.getRegistry().addResourceFactory(noDefaults);

		MockHttpRequest requestOk = MockHttpRequest.get("/ok");
		requestOk.accept( MediaType.APPLICATION_XML );

		MockHttpRequest requestNotFound = MockHttpRequest.get("/notfound");
		requestNotFound.accept( MediaType.APPLICATION_JSON );

		MockHttpResponse response = new MockHttpResponse();

		dispatcher.invoke(requestOk, response);
		System.out.println( "Response Ok:        " + response.getStatus() );
		response.reset();
		dispatcher.invoke(requestNotFound, response);
		System.out.println( "Response Not Found: " + response.getStatus() +
							" / Header: " + response.getOutputHeaders().getFirst("custom-header") );
	}


	/*
		Runtime Result

		Response Ok:        200
		Response Not Found: 404 / Header: custom header value
	 */
}
