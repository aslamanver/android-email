import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {

    public static void send() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                final String username = "yourname@yourcompany.com";
                final String password = "yourpassword";

                Properties props = new Properties();
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.host", "smtp.example.com");
                props.put("mail.smtp.port", "587");

                Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

                try {

                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress("to@yourdomain.com"));
                    message.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse("to@yourdomain.com"));
                    message.setSubject("Testing Subject");
                    message.setText("Dear Dude,"
                            + "\n\n I came from Android SMTP!");

                    Transport.send(message);

                    System.out.println("Done");

                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
