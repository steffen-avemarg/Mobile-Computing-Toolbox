package de.emgress.android.resttemplate;

import android.os.AsyncTask;
import de.emgress.backend.rest.model.Course;
import de.emgress.backend.rest.model.TreeElement;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NetworkHandler
{
	private static final String SERVER_IP = "176.221.43.139";
	private static final String SERVER_PORT = "8080";
	private static final String BASE_URI = "http://" + SERVER_IP + ":" + SERVER_PORT + "/mfb/resources";

	private static final String COURSES_URI = BASE_URI + "/courses";
	private static final String TREE_ROOT_URI = BASE_URI + "/tree";


	public static void getCourses( int requestId, NetworkHandlerCallback callback )
	{
		executeRequest( requestId, COURSES_URI, callback);
	}

	public static void getTreeRootElements( int requestId, NetworkHandlerCallback callback )
	{
		executeRequest( requestId, TREE_ROOT_URI, callback );
	}

	public static void getTreeElements( int requestId, int elementId, NetworkHandlerCallback callback )
	{
		executeRequest( requestId, TREE_ROOT_URI + "/" + elementId, callback );
	}

	/**
	 * AsyncTask for all Network activity
	 */
	private static class NetworkAsyncTask extends AsyncTask<Void, Void, List<?>>
	{
		private final int requestId;
		private final String requestUri;
		private final NetworkHandlerCallback callback;

		public NetworkAsyncTask( int requestId, String requestUri, NetworkHandlerCallback callback )
		{
			this.requestId = requestId;
			this.requestUri = requestUri;
			this.callback = callback;
		}

		@Override
		protected List<?> doInBackground( Void... voids )
		{
			if( this.requestUri.startsWith( COURSES_URI ) )
			{
				ResponseEntity<Course[]>  responseEntity =
						template.exchange( this.requestUri, HttpMethod.GET, requestEntity, Course[].class );
				Course[] result = responseEntity.getBody();

				return Arrays.asList(result);
			}
			else if( this.requestUri.startsWith( TREE_ROOT_URI ) )
			{
				ResponseEntity<TreeElement[]>  responseEntity =
						template.exchange( this.requestUri, HttpMethod.GET, requestEntity, TreeElement[].class );
				TreeElement[] result = responseEntity.getBody();

				return Arrays.asList( result );
			}
			return null;
		}

		@Override
		protected void onPostExecute( List<?> result )
		{
			this.callback.onResult( this.requestId, result );
		}
	}

	/*
	 Various Stuff to make this class complete
	 */

	// Private Constructor - we don't want to have
	// any instance of this class created.
	private NetworkHandler() {}

	// Private Methode to create a configured AsyncTask instance
	// and execute it
	private static void executeRequest( int requestId, String uri, NetworkHandlerCallback callback )
	{
		NetworkAsyncTask task = new NetworkAsyncTask( requestId, uri, callback );
		task.execute();
	}

	// Static references and configuration
	private static final HttpEntity<?> requestEntity;
	private static final RestTemplate template;
	static
	{
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept( Collections.singletonList( new MediaType( "application", "json" ) ) );
		requestEntity = new HttpEntity<Object>( headers );

		MappingJacksonHttpMessageConverter messageConverter = new MappingJacksonHttpMessageConverter();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(messageConverter);

		template = new RestTemplate();
		template.setMessageConverters( messageConverters );
	}


}
