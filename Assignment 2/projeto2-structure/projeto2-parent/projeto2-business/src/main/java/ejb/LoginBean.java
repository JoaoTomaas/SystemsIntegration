package ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@LocalBean
@Stateless(name = "LoginEJB")
public class LoginBean {

    public LoginBean() {
    }

    public void UserLogin(String username, String password){

    }

}
