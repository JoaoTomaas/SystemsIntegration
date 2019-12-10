package rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
//import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import data.Country;
import data.Item;
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
    /*@GET
    @Path("getmaterials")
    @Produces({MediaType.APPLICATION_XML})
    public ListCourses getAllMaterials() {
        return db.getListCourses();
    }*/


    // http://localhost:8100/play-REST-server/webapi/project3webservices/getstudents?id=1
    /*@GET
    @Path("getstudents")
    @Produces({MediaType.APPLICATION_JSON})
    public Course getAllStudents(@QueryParam("courseid") int id) {
        return db.getCourse(id);
    }*/

    /*@POST
    @Path("insertcountries")*/


    @GET
    @Path("insertcountry")
    @Produces({MediaType.APPLICATION_JSON})
        public void insertCountry(@QueryParam("countryname") String name){
        db.insertCountry(name);
    }

    @GET
    @Path("insertitem")
    @Produces({MediaType.APPLICATION_JSON})
    public void insertItem(@QueryParam("itemname") String name){
        db.insertItem(name);
    }


    @GET
    @Path("getitems")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Item> getItems() {
        return db.getListItems();
    }


    @GET
    @Path("getcountries")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Country> getCountries() {
        return db.getListCountries();
    }

}