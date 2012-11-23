package de.emgress.backend.friedolin;

import de.emgress.backend.friedolin.model.Semester;
import de.emgress.backend.friedolin.model.SemesterContainer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

public class FriedolinConnector
{
	public static final String QUERY_SEMESTER_LIST =
			"<SOAPDataService>" +
				"<general>" +
					"<object>semesterlist</object>" +
				"</general>" +
			"</SOAPDataService>";

	private static final String QUERY_TOP_NODES =
			"<SOAPDataService>" +
				"<general>" +
					"<object>eVV</object>" +
				"</general>" +
				"<condition>" +
					"<titel>Vorlesungsverzeichnis</titel>" +
					"<semester>@€SEM-ID€@</semester>" +
				"</condition>" +
			"</SOAPDataService>";

	private static final String QUERY_CHILD_NODES =
			"<SOAPDataService>" +
				"<general>" +
					"<object>eVV</object>" +
				"</general>" +
				"<condition>" +
					"<id>@€NODE-ID€@</id>" +
					"<semester>@€SEM-ID€@</semester>" +
				"</condition>" +
				"<language>de-DE</language>" +
			"</SOAPDataService>";

	private static final String QUERY_CANCELED_LECTURES =
			"<SOAPDataService>" +
				"<general>" +
					"<object>canceledLectures</object>" +
				"</general>" +
				"<condition>" +
					"<date>@€DATE€@</date>" +
				"</condition>" +
				"<limit>20</limit>" +
				"<offset>0</offset>" +
				"<language>de-DE</language>" +
			"</SOAPDataService>";




	public static String getSemesterMetaData()
	{
		try
		{
			DBInterface binding = new DBInterfaceServiceLocator().getdbinterface();
			String reply = binding.getDataXML( QUERY_SEMESTER_LIST);
			System.out.println( reply );

			JAXBContext context = JAXBContext.newInstance( SemesterContainer.class, Semester.class );
			Unmarshaller unmarshaller = context.createUnmarshaller();

			SemesterContainer sc = (SemesterContainer)unmarshaller.unmarshal( new StringReader( reply ) );

			return "Number of Semesters: " + sc.getSemesterList().size();

		}
		catch (Exception e)
		{
			e.printStackTrace();

			return e.getMessage();
		}
	}

	public static String getTopNodes(String semesterID )
	{
		try
		{
			DBInterface binding = new DBInterfaceServiceLocator().getdbinterface();
			String reply = binding.getDataXML( QUERY_TOP_NODES.replaceFirst( "@€SEM-ID€@", semesterID) );
			System.out.println( reply );
			return reply;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}



}
