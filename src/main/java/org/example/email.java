package org.example;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.*;

public class email
{
    public static void main(String [] args) throws MessagingException {
        String subject = "Test";
        String message = "Sending email through java";
        String to = "himani.prasad016@gmail.com";//change accordingly
        String from = "himanip@spryhealth.care";
//        sendEmail(message, subject, to, from);
        sendAttach(message, subject, to, from);
    }

    private static void sendAttach(String message, String subject, String to, String from) {
        String host = "smtp.gmail.com";
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("himanip@spryhealth.care", "ysgnvktpoxxabman");
            }
        });
        session.setDebug(true);

        //compose the message
        try{
            MimeMessage m = new MimeMessage(session);
            m.setFrom(new InternetAddress(from));
            m.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            m.setSubject(subject);
            m.setText(message);

            String path = "/home/spry/Documents/sample.pdf";
            MimeBodyPart textMime = new MimeBodyPart();
            MimeBodyPart fileMime = new MimeBodyPart();
            MimeMultipart mimeMultipart = new MimeMultipart();
            try {
                textMime.setText(message);
                fileMime.attachFile(path);
                mimeMultipart.addBodyPart(textMime);
                mimeMultipart.addBodyPart(fileMime);
            }catch (Exception e){
                e.printStackTrace();
            }

            m.setContent(mimeMultipart);
            // Send message
            Transport.send(m);
            System.out.println("message sent successfully....");

        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    private static void sendEmail(String message, String subject, String to, String from){
        //Get the session object
        String host = "smtp.gmail.com";
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("himanip@spryhealth.care", "ysgnvktpoxxabman");
            }
        });
        session.setDebug(true);

        //compose the message
        try{
            MimeMessage m = new MimeMessage(session);
            m.setFrom(new InternetAddress(from));
            m.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            m.setSubject(subject);
            m.setText(message);

            // Send message
            Transport.send(m);
            System.out.println("message sent successfully....");

        }catch (MessagingException mex) {mex.printStackTrace();}
    }
}