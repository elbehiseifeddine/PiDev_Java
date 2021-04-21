/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.utils;

import com.sun.istack.internal.logging.Logger;
import java.io.File;
import java.util.Properties;
import java.util.logging.Level;
import javax.activation.DataHandler;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author seifeddine
 */
public class JavaMail {
    public static void sendMail(String recepient,String type) throws Exception{
        System.out.println("preparing to");
        Properties properties=new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        
        String myAccountEmail= "rightjob.inc@gmail.com";
        String myAccountPwd= "powerdev";
        
        Session session=Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(myAccountEmail,myAccountPwd);
            }
        });
        
        if(type.equals("EmailConfirmation")){
            EmailConfirmation(session,myAccountEmail,recepient);
        }else if(type.equals("ForgetPassword")){
            System.out.println("aaaaaaa");
        }
        
        
    }

    private static void EmailConfirmation(Session session,String myAccountEmail,String recepient) throws Exception {
        
        try {
            Message message=new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("My first email from Java App");
            MimeMultipart multipart = new MimeMultipart("related");
            BodyPart messageBodyPart = new MimeBodyPart();
            String htmlText = 
                "<center>" +
                "   <img src=\"cid:image\" alt=\"RightJob\" height=\"175\" width=\"400\" />" +
                "   <br>" +
                "   <h1>Bienvenu dans notre site RightJob </h1>" +
                "   <p>Votre adhesion est effectuer avec succés , j'espère vous trouvez ce que vous chercher, nous vous souhaitons nos chèrs salutation</p>" +
                "   <p>Pour Activer votre compte clicker sur ce lien: <a href=\"http://127.0.0.1:8000/Activation/"+recepient+"\">Activer</a></p>" +
                "   <p>Pour toute autre information veuillez nous contacter sur notre email RightJob.inc@gmail.com</p>" +
                "</center>";
            messageBodyPart.setContent(htmlText, "text/html");
            multipart.addBodyPart(messageBodyPart);
            messageBodyPart = new MimeBodyPart();
            String path="../assets/Logo complet.png";
            File file = new File(path.trim());
            DataSource fds = new FileDataSource(file);
            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setHeader("Content-ID", "<image>");
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            Transport.send(message);
            System.out.println("message sent");
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(JavaMail.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}




















