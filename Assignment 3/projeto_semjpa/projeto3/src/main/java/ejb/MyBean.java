package ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import data.*;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 * Session Bean implementation class MyBean
 */
@Stateless
@LocalBean
public class MyBean {
    private ListCourses lc;

    
    private ListCountries lct;
    private ListItems lit;
    /**
     * Default constructor.
     */
    public MyBean() {
        /*Course courses[] = {new Course(1, "IS"), new Course(2, "ES"), new Course(3, "PPP")};
        Material materials[] = {new Material(1, "book", "/usr"), new Material(2, "slides", "/usr/slides"), new Material(3, "exercises", "/usr/exercises")};

        courses[0].addMaterial(materials[0]);
        courses[0].addMaterial(materials[1]);
        courses[1].addMaterial(materials[0]);
        courses[1].addMaterial(materials[2]);
        courses[2].addMaterial(materials[1]);
        courses[2].addMaterial(materials[2]);

        lc = new ListCourses();
        for (Course c : courses)
            lc.addCourse(c);*/

        String url = "jdbc:mysql://localhost:3306/KafkaDB?useTimezone=true&serverTimezone=UTC";
        String user = "joaot";
        String password = "teste";
        
        lct = new ListCountries();

        String query = "SELECT * from countries";

        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {
                System.out.println(rs.getString(1));
                Country c = new Country(Integer.parseInt(rs.getString(1)), rs.getString(2));
                lct.addCountry(c);
            }

        } catch (SQLException ex) {
            System.out.println("O menino espumou-se todo");
        }
        
        
        
        
    }


    public ListCourses getListCourses() {
        return this.lc;
    }

    public Course getCourse(int id) {
        return this.lc.get(id);
    }

    public ListCountries getListCountries() {
        return this.lct;
    }

}

