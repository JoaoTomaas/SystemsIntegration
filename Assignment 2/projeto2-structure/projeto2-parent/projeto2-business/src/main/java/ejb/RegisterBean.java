package ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@LocalBean
@Stateless(name = "RegisterEJB")
public class RegisterBean {
    public RegisterBean() {
    }
}
