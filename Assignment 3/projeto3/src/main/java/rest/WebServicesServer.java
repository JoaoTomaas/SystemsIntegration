package rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
//import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import data.Course;
import data.ListCountries;
import data.ListCourses;
import ejb.MyBean;

@Path("/project3webservices")
@RequestScoped
public class WebServicesServer {

    @Inject
    MyBean db;

    public WebServicesServer() throws NamingException {
        System.out.println("WebServicesServer created. db = " + this.db);
    }

    // http://localhost:8100/play-REST-server/webapi/project3webservices/gettext
    @GET
    @Path("gettext")
    @Produces({MediaType.TEXT_PLAIN})
    public String getText() {
        return "Hello World!";
    }

    // http://localhost:8100/play-REST-server/webapi/project3webservices/getmaterials
    @GET
    @Path("getmaterials")
    @Produces({MediaType.APPLICATION_XML})
    public ListCourses getAllMaterials() {
        return db.getListCourses();
    }


    // http://localhost:8100/play-REST-server/webapi/project3webservices/getstudents?id=1
    @GET
    @Path("getstudents")
    @Produces({MediaType.APPLICATION_JSON})
    public Course getAllStudents(@QueryParam("courseid") int id) {
        return db.getCourse(id);
    }

    @GET
    @Path("getcountries")
    @Produces({MediaType.APPLICATION_JSON})
    public ListCountries getCountries() {
        return db.getListCountries();
    }

}