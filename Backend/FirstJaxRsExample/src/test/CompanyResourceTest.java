package test;

import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.mock.MockDispatcherFactory;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.jboss.resteasy.plugins.server.resourcefactory.POJOResourceFactory;
import resources.CompanyResource;

import javax.ws.rs.core.MediaType;
import java.net.URISyntaxException;

public class CompanyResourceTest
{

	public static void main(String[] args) throws URISyntaxException
	{
		Dispatcher dispatcher = MockDispatcherFactory.createDispatcher();

		POJOResourceFactory noDefaults = new POJOResourceFactory(CompanyResource.class);
		dispatcher.getRegistry().addResourceFactory(noDefaults);

		MockHttpRequest requestXml = MockHttpRequest.get("/company");
		requestXml.accept( MediaType.APPLICATION_XML );

		MockHttpRequest requestJson = MockHttpRequest.get("/company");
		requestJson.accept( MediaType.APPLICATION_JSON );

		MockHttpResponse response = new MockHttpResponse();

		dispatcher.invoke(requestXml, response);
		System.out.println( "Response XML:\t" + response.getContentAsString() );
		response.reset();
		dispatcher.invoke(requestJson, response);
		System.out.println( "Response JSON:\t" + response.getContentAsString() );


	}


	/*
		Runtime Result

		Response XML:
			<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
			<publicTransportCompany name="Örtlicher Nahverkehr">
				<lines>
					<line color="BLUE" name="Tram 11"/>
					<line color="RED" name="Bus 40"/>
					<line color="YELLOW" name="U-Bahn 23"/>
				</lines>
				<stations>
					<station id="12345">
						<name>Westbahnhof</name>
					</station>
					<station id="54321">
						<name>Ostbahnhof</name>
					</station>
					<station id="15243">
						<name>Südbahnhof</name>
					</station>
				</stations>
			</publicTransportCompany>

		Response JSON:
			{
				"name":"Örtlicher Nahverkehr",
				"stations":
					[
						{"id":12345,"name":"Westbahnhof"},
						{"id":54321,"name":"Ostbahnhof"},
						{"id":15243,"name":"Südbahnhof"}
					],
				"lines":
					[
						{"name":"Tram 11","color":"BLUE"},
						{"name":"Bus 40","color":"RED"},
						{"name":"U-Bahn 23","color":"YELLOW"}
					]
			}

	 */


	/*
		Response ohne @XMLElement & @XMLElementWrapper in der PublicTransportCompany Klasse

		Response XML:
			<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
			<publicTransportCompany name="Örtlicher Nahverkehr">
				<lines color="BLUE" name="Tram 11"/>
				<lines color="RED" name="Bus 40"/>
				<lines color="YELLOW" name="U-Bahn 23"/>
				<stations id="12345">
					<name>Westbahnhof</name>
				</stations>
				<stations id="54321">
					<name>Ostbahnhof</name>
				</stations>
				<stations id="15243">
					<name>Südbahnhof</name>
				</stations>
			</publicTransportCompany>

		Response JSON:
			{
				"name":"Örtlicher Nahverkehr",
				"stations":
					[
						{"id":12345,"name":"Westbahnhof"},
						{"id":54321,"name":"Ostbahnhof"},
						{"id":15243,"name":"Südbahnhof"}
					],
				"lines":
					[
						{"name":"Tram 11","color":"BLUE"},
						{"name":"Bus 40","color":"RED"},
						{"name":"U-Bahn 23","color":"YELLOW"}
					]
			}




	 */

}
