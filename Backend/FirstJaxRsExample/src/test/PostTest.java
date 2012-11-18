package test;

import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.mock.MockDispatcherFactory;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.jboss.resteasy.plugins.server.resourcefactory.POJOResourceFactory;
import resources.StationResource;

import javax.ws.rs.core.MediaType;
import java.net.URISyntaxException;

public class PostTest
{

	public static void main(String[] args) throws URISyntaxException
	{
		Dispatcher dispatcher = MockDispatcherFactory.createDispatcher();

		POJOResourceFactory noDefaults = new POJOResourceFactory(StationResource.class);
		dispatcher.getRegistry().addResourceFactory(noDefaults);

		MockHttpRequest requestJson = MockHttpRequest.post("/station");
		requestJson.contentType( MediaType.APPLICATION_JSON);
		requestJson.content( "{\"id\":54321,\"name\":\"Südbahnhof\"}".getBytes() );

		MockHttpResponse response = new MockHttpResponse();

		dispatcher.invoke(requestJson, response);
		System.out.println( "Response:\t" + response.getStatus() + " / "
				+ response.getOutputHeaders().getFirst( "location" ) );


	}

}
