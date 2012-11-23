package de.emgress.backend.couchdb;

import org.ektorp.UpdateConflictException;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;

import java.net.MalformedURLException;

public class CouchDBTest
{

	public static void main(String[] args)
	{
		try
		{
			HttpClient authenticatedHttpClient = new StdHttpClient.Builder()
				.url("http://176.221.43.139:5984")
				.username("admin")
				.password("G!n63r=%cA7")
				.build();

			StdCouchDbInstance dbInstance = new StdCouchDbInstance( authenticatedHttpClient );
			StdCouchDbConnector db = new StdCouchDbConnector( "mfb", dbInstance );

			Sofa sofa = new Sofa();
			sofa.setId( "2" );
			sofa.setColor( "My beautiful color" );

			db.create( sofa );

			System.out.println( sofa.getId() + " - " + sofa.getRevision() );



		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
		catch (UpdateConflictException uce )
		{
			System.out.println( uce.getMessage() );
			uce.printStackTrace();
		}

	}
}
