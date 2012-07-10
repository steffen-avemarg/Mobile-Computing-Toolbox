package de.emgress.backend.rest.resources;
import com.sun.net.httpserver.HttpServer;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import de.emgress.backend.rest.model.TreeElement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("/")
public class BaseResource
{



    @GET
    @Path("tree")
    @Produces( MediaType.APPLICATION_JSON )
    public List<TreeElement> getRootTreeElements()
    {
        return treeElements;
    }

    @GET
    @Path("tree/{treeElementID}")
    @Produces( MediaType.APPLICATION_JSON )
    public List<TreeElement> getTreeElement(
            @PathParam( "treeElementID" ) int treeElementID )
    {
        return new ArrayList<TreeElement>();
    }



    @Produces("text/plain")
    public String getClichedMessage()
    {
        return "Hello World";
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


    /**
     * Create some static test data
     */
    private static List<TreeElement> treeElements = new ArrayList<TreeElement>();
    static
    {
        TreeElement elemLevel01ID01 = new TreeElement( 1, "Theologische Fakultät" );
        treeElements.add( elemLevel01ID01 );
        TreeElement elemLevel02ID01 = new TreeElement( 11, "Religionswissenschaft" );
        elemLevel01ID01.getChildren().add( elemLevel02ID01 );



        treeElements.add( new TreeElement( 2, "Rechtswissenschaftliche Fakultät" ) );
        treeElements.add( new TreeElement( 3, "Physikalisch-Astronomische Fakultät" ) );
        treeElements.add( new TreeElement( 4, "Fakultät für Mathematik und Informatik" ) );
    }

}