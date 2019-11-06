package ejb;


import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


@Stateless
public class MailBean {
    @Resource(name = "java:jboss/mail/myapp")
    private Session session;
    public MailBean() {}
    // @Schedule(second = "0",minute = "*",hour = "*")
    public void send() {
        try {
            final Message message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("duarte1998esl@gmail.com"));
            message.setSubject("cenas");
            message.setText("olaaaa");

            Transport.send(message);
        } catch (MessagingException e) {
            System.out.println("Cannot send mail"+ e);
        }
    }
}


