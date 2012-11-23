package de.emgress.backend.rest.resources;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;
import de.emgress.backend.friedolin.FriedolinConnector;
import de.emgress.backend.rest.model.*;
import de.emgress.backend.rest.model.constants.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Path("/")
public class BaseResource
{

	@Context
	private UriInfo uriInfo;


    @GET
    @Path("tree")
    @Produces( MediaType.APPLICATION_JSON )
    public List<TreeElement> getRootTreeElements()
    {
	   return getMockRootTreeElements();
    }

    @GET
    @Path("tree/{treeElementID : \\d+}")
    @Produces( MediaType.APPLICATION_JSON )
    public List<TreeElement> getTreeElement(
            @PathParam( "treeElementID" ) int treeElementID )
    {
		List<TreeElement> returnValue = null;


		if( treeElementID < 10 )
		{
			List<TreeElement> completeList = getMockRootTreeElements();

			for( TreeElement te : completeList )
			{
				if( te.getId() == treeElementID )
					returnValue = te.getChildren();
			}

			if( returnValue == null )
				returnValue = this.getMockSecondLevelElements();


			return returnValue;
		}

		// Second Level
		else if( treeElementID < 100 )
		{
			returnValue = this.getMockSecondLevelElements();
		}
		// Third Level
		else
		{
			returnValue = this.getMockThirdLevelElements();
		}

        return returnValue;
    }


	@GET
	@Path("courses")
	@Produces( MediaType.APPLICATION_JSON )
	public List<Course> getCourses()
	{
		return this.getMockCourseList();
	}

	@GET
	@Path("courses/{course_id : \\d+}")
	@Produces( MediaType.APPLICATION_JSON )
	public Course getCourse( @PathParam( "course_id" ) int courseID )
	{
		return this.getMockCourseList().get( 0 );
	}



	@GET
	@Path("semester")
	@Produces( "text/plain" )
	public String getSemesterMetaData()
	{
		return FriedolinConnector.getSemesterMetaData();
	}

	@GET
	@Path("topnodes")
	@Produces( "text/plain" )
	public String getTopNodes()
	{
		return FriedolinConnector.getTopNodes( "20121" );
	}



    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServerFactory.create("http://localhost:9998/");
        server.start();

        System.out.println("Server running");
        System.out.println("Visit: http://localhost:9998/tree");
        System.out.println("Hit return to stop...");
        System.in.read();
        System.out.println("Stopping server");
        server.stop(0);
        System.out.println("Server stopped");
    }


	private List<TreeElement> getMockRootTreeElements()
	{
		ArrayList<TreeElement> elements = new ArrayList<TreeElement>();

		TreeElement root = new TreeElement( 1, "Theologische Fakultät" );
		root.getChildren().add( new TreeElement( 11, "Sprachen" ) );
		root.getChildren().add( new TreeElement( 12, "Religionswissenschaft" ) );
		root.getChildren().add( new TreeElement( 13, "Altes Testament" ) );
		root.getChildren().add( new TreeElement( 14, "Neues Testament" ) );
		elements.add( root );
		elements.add( new TreeElement( 2, "Rechtswissenschaftliche Fakultät" ) );
		elements.add( new TreeElement( 3, "Wirtschaftwissenschaftliche Fakultät  " ) );
		elements.add( new TreeElement( 4, "Philosophische Fakultät" ) );
		elements.add( new TreeElement( 5, "Fakultät für Sozial- und Verhaltenswissenschaften" ) );
		elements.add( new TreeElement( 6, "Fakultät für Mathematik und Informatik" ) );
		elements.add( new TreeElement( 7, "Physikalisch-Astronomische Fakultät" ) );
		elements.add( new TreeElement( 8, "Chemisch-Geowissenschaftliche Fakultät" ) );

		return elements;
	}

	private List<TreeElement> getMockSecondLevelElements()
	{
		ArrayList<TreeElement> elements = new ArrayList<TreeElement>();

		elements.add( new TreeElement( 11, "Theologische Fakultät - Sublevel 2" ) );
		elements.add( new TreeElement( 21, "Rechtswissenschaftliche Fakultät - Sublevel 2" ) );
		elements.add( new TreeElement( 31, "Physikalisch-Astronomische Fakultät - Sublevel 2" ) );
		elements.add( new TreeElement( 41, "Fakultät für Mathematik und Informatik - Sublevel 2" ) );
		elements.add( new TreeElement( 51, "Theologische Fakultät - Sublevel 2" ) );
		elements.add( new TreeElement( 61, "Rechtswissenschaftliche Fakultät - Sublevel 2" ) );
		elements.add( new TreeElement( 71, "Physikalisch-Astronomische Fakultät - Sublevel 2" ) );
		elements.add( new TreeElement( 81, "Fakultät für Mathematik und Informatik - Sublevel 2" ) );

		return elements;
	}

	private List<TreeElement> getMockThirdLevelElements()
	{
		ArrayList<TreeElement> elements = new ArrayList<TreeElement>();

		// Create some Tree Elements
		elements.add( new TreeElement( 111, TreeElementType.LEAF_WITH_COURSES, "Theologische Fakultät - Leaf" ) );
		elements.add( new TreeElement( 211, TreeElementType.LEAF_WITH_COURSES, "Rechtswissenschaftliche Fakultät - Leaf" ) );
		elements.add( new TreeElement( 311, TreeElementType.LEAF_WITH_COURSES, "Physikalisch-Astronomische Fakultät - Leaf" ) );
		elements.add( new TreeElement( 411, TreeElementType.LEAF_WITH_COURSES, "Fakultät für Mathematik und Informatik - Leaf" ) );
		elements.add( new TreeElement( 511, TreeElementType.LEAF_WITH_COURSES, "Theologische Fakultät - Leaf" ) );
		elements.add( new TreeElement( 611, TreeElementType.LEAF_WITH_COURSES, "Rechtswissenschaftliche Fakultät - Leaf" ) );
		elements.add( new TreeElement( 711, TreeElementType.LEAF_WITH_COURSES, "Physikalisch-Astronomische Fakultät - Leaf" ) );
		elements.add( new TreeElement( 811, TreeElementType.LEAF_WITH_COURSES, "Fakultät für Mathematik und Informatik - Leaf" ) );

		// Add some Courses to these Elements
		List<Course> mockCourses = this.getMockCourseList();

		for( Course c : mockCourses )
		{
			TreeElementCourseWrapper wrapper = new TreeElementCourseWrapper( c );

			for( TreeElement te : elements )
			{
				te.getCourses().add( wrapper );
			}
		}

		return elements;
	}

	private List<Course> getMockCourseList()
	{
		ArrayList<Course> elements = new ArrayList<Course>();

		Course course = new Course( 1, "Kurs 1", Language.GERMAN );
		Person assistant = new Person( "Max", "Mustermann", "Dozent", Title.DR );
		Person lecturer = new Person( "Uwe", "Müller", "Dozent", Title.PROF );

		course.setSemester( "WS 2012/13" );
		course.setDescription("Ziemlich kurze Kursbeschreibung");
		course.getPersons().add(lecturer);
		course.getPersons().add( assistant );
		course.setPrerequisites("Keine");
		course.setCertificates("Keine Zertifikate");
		course.setLiterature("Buch 1, ISBN 1234567X");
		course.setType( CourseType.LECTURE );
		course.setSws( 4 );

		course.setUri( uriInfo.getBaseUri() + "courses/" + course.getId() );

		course.getEvents().add(new Event(lecturer, DayOfWeek.THURSDAY, EventRecurrence.WEEKLY, new Room(),
				new Date(), new Date(), new Date(), new Date()));
		course.getEvents().add( new Event( assistant, DayOfWeek.MONDAY, EventRecurrence.WEEKLY, new Room(),
				new Date(), new Date(), new Date(), new Date() ) );

		elements.add( course );
		elements.add( course );
		elements.add( course );
		elements.add( course );

		return elements;
	}
}