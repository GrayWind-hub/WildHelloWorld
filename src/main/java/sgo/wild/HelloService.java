package sgo.wild;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;


@Path("/hello")
public class HelloService {
    private long id = 0;
    public HelloService()
    {
        id = new Date().getTime();
    }

    @GET
    @Path("/text")
    public String getHello ()
    {
        return "hello world! ("+id+")";
    }
    @GET
    @Path("/json")
    @Produces(MediaType.APPLICATION_JSON)
    public SimpleProperty getPropertyJSON ()
    {
        SimpleProperty p = new SimpleProperty("id",""+id);
        return p;
    }
    @GET
    @Path("/xml")
    @Produces(MediaType.APPLICATION_XML)
    public SimpleProperty getPropertyXML ()
    {
        SimpleProperty p = new SimpleProperty("key","value");
        return p;
    }
}