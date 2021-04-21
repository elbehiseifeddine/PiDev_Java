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
        if(type.equals("EmailReclamation")){
            EmailReclamation(session,myAccountEmail,recepient);
        }
        if(type.equals("EmailOffreEmploi")){
            EmailOffreEmploi(session,myAccountEmail,recepient);
        }
        if(type.equals("EmailOffreStage")){
            EmailOffreStage(session,myAccountEmail,recepient);
        }
        if(type.equals("EmailEvenement")){
            EmailEvenement(session,myAccountEmail,recepient);
        }
        if(type.equals("EmailFormation")){
            EmailFormation(session,myAccountEmail,recepient);
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
            String path="C:/xampp/htdocs/PiDev_Java/src/pidev_java/assets/Logo complet (1).png";
//            String path="https://firebasestorage.googleapis.com/v0/b/atast-9e29a.appspot.com/o/Logo%20complet.png?alt=media&token=d2068295-a4b1-4ebc-a2e6-f1db57344c8f&fbclid=IwAR2gV_UhBG8KriaZyMAXg3SgKGkj1xG6w3-PsW25O3wUgDUZYyu-wstZLTk";
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
        
    private static void EmailReclamation(Session session,String myAccountEmail,String recepient) throws Exception {
        
        try {
            Message message=new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Nouvelle Reclamation détecté");
            MimeMultipart multipart = new MimeMultipart("related");
            BodyPart messageBodyPart = new MimeBodyPart();
            String htmlText = 
                "<center>" +
                "   <img src=\"cid:image\" alt=\"RightJob\" height=\"175\" width=\"400\" />" +
                "   <br>" +
                "   <h1>Nouvelle Reclamation détécté </h1>" +
                "   <p>Une nouvelle reclamation est intercépter, veuillez la consulter à partir de votre profil d'admin de reclamation ! Merci !</p>" +
                "</center>";
            messageBodyPart.setContent(htmlText, "text/html");
            multipart.addBodyPart(messageBodyPart);
            messageBodyPart = new MimeBodyPart();
            String path="C:/xampp/htdocs/PiDev_Java/src/pidev_java/assets/Logo complet (1).png";
//            String path="https://firebasestorage.googleapis.com/v0/b/atast-9e29a.appspot.com/o/Logo%20complet.png?alt=media&token=d2068295-a4b1-4ebc-a2e6-f1db57344c8f&fbclid=IwAR2gV_UhBG8KriaZyMAXg3SgKGkj1xG6w3-PsW25O3wUgDUZYyu-wstZLTk";
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

     private static void EmailOffreEmploi(Session session,String myAccountEmail,String recepient) throws Exception {
        
        try {
            Message message=new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Nouvelle Offre d'Emploi détecté");
            MimeMultipart multipart = new MimeMultipart("related");
            BodyPart messageBodyPart = new MimeBodyPart();
            String htmlText = 
                "<center>" +
                "   <img src=\"cid:image\" alt=\"RightJob\" height=\"175\" width=\"400\" />" +
                "   <br>" +
                "   <h1>Nouvelle Offre d'Emploi détécté </h1>" +
                "   <p>Une nouvelle Offre d'Emploi est intercépter, veuillez la consulter à partir de votre profil d'admin des emploi ! Merci !</p>" +
                "</center>";
            messageBodyPart.setContent(htmlText, "text/html");
            multipart.addBodyPart(messageBodyPart);
            messageBodyPart = new MimeBodyPart();
            String path="C:/xampp/htdocs/PiDev_Java/src/pidev_java/assets/Logo complet (1).png";
//            String path="https://firebasestorage.googleapis.com/v0/b/atast-9e29a.appspot.com/o/Logo%20complet.png?alt=media&token=d2068295-a4b1-4ebc-a2e6-f1db57344c8f&fbclid=IwAR0OU2CDadtpG0HKz67ipJkayVO6TB3Mw4yIuYA_edUCmseK05wUu0yhO5o";
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

      private static void EmailOffreStage(Session session,String myAccountEmail,String recepient) throws Exception {
        
        try {
            Message message=new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Nouvelle Offre de Stage détecté");
            MimeMultipart multipart = new MimeMultipart("related");
            BodyPart messageBodyPart = new MimeBodyPart();
            String htmlText = 
                "<center>" +
                "   <img src=\"cid:image\" alt=\"RightJob\" height=\"175\" width=\"400\" />" +
                "   <br>" +
                "   <h1>Nouvelle Offre de Stage détécté </h1>" +
                "   <p>Une nouvelle Offre d'Emploi est intercépter, veuillez la consulter à partir de votre profil d'admin des emploi ! Merci !</p>" +
                "</center>";
            messageBodyPart.setContent(htmlText, "text/html");
            multipart.addBodyPart(messageBodyPart);
            messageBodyPart = new MimeBodyPart();
            String path="C:/xampp/htdocs/PiDev_Java/src/pidev_java/assets/Logo complet (1).png";
//            String path="https://firebasestorage.googleapis.com/v0/b/atast-9e29a.appspot.com/o/Logo%20complet.png?alt=media&token=d2068295-a4b1-4ebc-a2e6-f1db57344c8f&fbclid=IwAR2gV_UhBG8KriaZyMAXg3SgKGkj1xG6w3-PsW25O3wUgDUZYyu-wstZLTk";
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
 private static void EmailEvenement(Session session,String myAccountEmail,String recepient) throws Exception {
        
        try {
            Message message=new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Nouvelle Evénement détecté");
            MimeMultipart multipart = new MimeMultipart("related");
            BodyPart messageBodyPart = new MimeBodyPart();
            String htmlText = 
                "<center>" +
                "   <img src=\"cid:image\" alt=\"RightJob\" height=\"175\" width=\"400\" />" +
                "   <br>" +
                "   <h1>Nouvelle Evénement détécté </h1>" +
                "   <p>Une nouvelle Evénement est détécté, veuillez la consulter à partir de votre profil d'admin des événements ! Merci !</p>" +
                "</center>";
            messageBodyPart.setContent(htmlText, "text/html");
            multipart.addBodyPart(messageBodyPart);
            messageBodyPart = new MimeBodyPart();
            String path="C:/xampp/htdocs/PiDev_Java/src/pidev_java/assets/Logo complet (1).png";
//            String path="https://firebasestorage.googleapis.com/v0/b/atast-9e29a.appspot.com/o/Logo%20complet.png?alt=media&token=d2068295-a4b1-4ebc-a2e6-f1db57344c8f&fbclid=IwAR2gV_UhBG8KriaZyMAXg3SgKGkj1xG6w3-PsW25O3wUgDUZYyu-wstZLTk";
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

  private static void EmailFormation(Session session,String myAccountEmail,String recepient) throws Exception {
        
        try {
            Message message=new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Nouvelle Formation détecté");
            MimeMultipart multipart = new MimeMultipart("related");
            BodyPart messageBodyPart = new MimeBodyPart();
            String htmlText = 
                "<center>" +
                "   <img src=\"cid:image\" alt=\"RightJob\" height=\"175\" width=\"400\" />" +
                "   <br>" +
                "   <h1>Nouvelle Formation détécté </h1>" +
                "   <p>Une nouvelle Offre d'Emploi est intercépter, veuillez la consulter à partir de votre profil d'admin des événements ! Merci !</p>" +
                "</center>";
            messageBodyPart.setContent(htmlText, "text/html");
            multipart.addBodyPart(messageBodyPart);
            messageBodyPart = new MimeBodyPart();
            String path="C:/xampp/htdocs/PiDev_Java/src/pidev_java/assets/Logo complet (1).png";
//            String path="https://firebasestorage.googleapis.com/v0/b/atast-9e29a.appspot.com/o/Logo%20complet.png?alt=media&token=d2068295-a4b1-4ebc-a2e6-f1db57344c8f&fbclid=IwAR2gV_UhBG8KriaZyMAXg3SgKGkj1xG6w3-PsW25O3wUgDUZYyu-wstZLTk";
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




















