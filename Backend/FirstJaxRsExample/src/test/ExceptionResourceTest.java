package test;

import exception.MyExceptionMapper;
import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.mock.MockDispatcherFactory;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.jboss.resteasy.plugins.server.resourcefactory.POJOResourceFactory;
import resources.MyExceptionResource;

import java.net.URISyntaxException;

public class ExceptionResourceTest
{

	public static void main(String[] args) throws URISyntaxException
	{
		Dispatcher dispatcher = MockDispatcherFactory.createDispatcher();

		POJOResourceFactory noDefaults = new POJOResourceFactory( MyExceptionResource.class );
		dispatcher.getRegistry().addResourceFactory(noDefaults);
		dispatcher.getProviderFactory().addExceptionMapper( MyExceptionMapper.class );

		MockHttpRequest request = MockHttpRequest.get("/error");

		MockHttpResponse response = new MockHttpResponse();

		dispatcher.invoke(request, response);
		System.out.println( "Response:\t" + response.getStatus() + " / "
				+ response.getContentAsString() );
	}

}
