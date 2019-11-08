package ejb;


import data.Item;
import dto.ItemDTO;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.net.ConnectException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

@LocalBean
@Stateless
public class MailBean {
    @PersistenceContext(name = "Users")
    EntityManager em;
    //@Resource(name = "java:jboss/mail/myapp")
    @Resource(name = "java:jboss/mail/gmail")
    private Session session;

    //ItemBean ib;

    public MailBean() {}

    //@Schedule(second = "0",minute = "*",hour = "*")
    public void send() {

        try {
            Query q = em.createQuery("from Item i order by published_date desc");

            @SuppressWarnings("unchecked")
            List<Item> all_items = q.getResultList();


            StringBuilder items_email = new StringBuilder("Top 3 Items of the Week\n\n");

            //List<Item> all_items = ib.Newest_Items();
            for (int i = 0; i < 3; i++){
                items_email.append("Product Name: ").append(all_items.get(i).getName()).append("\n");
                items_email.append("Category: ").append(all_items.get(i).getCategory()).append("\n");
                items_email.append("Country of Origin: ").append(all_items.get(i).getCountry_of_origin()).append("\n");
                items_email.append("Preco: ").append(all_items.get(i).getPrice()).append("\n\n");
                //Será que também é para mandar as imagens por email?
                System.out.println("\n");
            }

            items_email.append("\nBest regards,\n" + "\tMyBay CEO's Joao Tomas and Duarte Guerreiro");
            //System.out.println("ddddd -> " + items_email);


            /*final*/ MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("revealconcepts@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("joao.miguel.tomas@hotmail.com"));
            message.setSubject("MyBay Items Catalog");
            //message.setContent("Mail sent from JBoss 10","text/plain");
            message.setContent(items_email.toString(), "text/plain");
            //message.setText(items_email);
            Transport.send(message);

        } catch (MessagingException e) {
            //System.out.println("Cannot send mail"+ e);
            Logger.getLogger(MailBean.class.getName()).log(Level.WARNING, "Cannot send email", e);
        }
    }
}


