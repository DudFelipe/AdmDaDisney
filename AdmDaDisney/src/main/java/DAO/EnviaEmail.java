/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author fernando.lcoutinho
 */
public class EnviaEmail {

    /**
     * @throws java.io.IOException
     */
    public void enviaEmail() throws IOException {
        System.out.println("Iniciar envio do e-mail...");
        Properties properties=new Properties();
        
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        
        String myAccountEmail="adm.efmm@gmail.com";
        String password="admtads5s";
        String toEmail="falta email";
        
        Session session=Session.getInstance(properties,new Authenticator() {
            
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });
        
        Message message =new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(myAccountEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject("Assunto do email [Relatório + departamento + id do relatório]");
             Multipart emailContetnt=new MimeMultipart();
             
             //text body
             MimeBodyPart textBodyPart=new MimeBodyPart();
             textBodyPart.setText("Assunto do e-mail");
             
             
             //attachment body part
             MimeBodyPart pdfAttachment=new MimeBodyPart();
             /**
              * Exemplo:
              * pdfAttachment.attachFile("C:\\Users\\Rajiv Pathak\\Downloads\\MIT TYBCA Sem 4 Advanced Java.pdf");
              */
             pdfAttachment.attachFile("Insira o caminho do anexo aquis");
             
             // attach body part
             emailContetnt.addBodyPart(textBodyPart);
             emailContetnt.addBodyPart(pdfAttachment);
             
             
             //attach multipart to message
             message.setContent(emailContetnt);
             
             Transport.send(message);
             System.out.println("Email enviado.)");
             
             
            
        } catch (MessagingException ex) {
            Logger.getLogger(EnviaEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
