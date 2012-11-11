package test;

import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.mock.MockDispatcherFactory;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.jboss.resteasy.plugins.server.resourcefactory.POJOResourceFactory;
import resources.HelloWorld;

import javax.ws.rs.core.MediaType;

public class HelloWorldTest
{
	public static void main(String[] args) throws Exception
	{
		Dispatcher dispatcher = MockDispatcherFactory.createDispatcher();

		POJOResourceFactory noDefaults = new POJOResourceFactory(HelloWorld.class);
		dispatcher.getRegistry().addResourceFactory(noDefaults);

		MockHttpRequest request = MockHttpRequest.get("/helloworld");
		request.accept( MediaType.TEXT_PLAIN );


		MockHttpRequest requestXML = MockHttpRequest.get("/helloworld");
		requestXML.accept( MediaType.APPLICATION_XML );

		MockHttpResponse response = new MockHttpResponse();

		dispatcher.invoke(request, response);
		System.out.println( "Response:\t\t" + response.getContentAsString() );
		response.reset();
		dispatcher.invoke(requestXML, response);
		System.out.println( "Response XML:\t" + response.getContentAsString() );
	}
}
