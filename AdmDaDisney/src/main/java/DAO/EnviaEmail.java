/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Pedido;
import View.Principal;
import java.io.IOException;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author fernando.lcoutinho
 */
public class EnviaEmail {

    /**
     * @param p
     * @throws java.io.IOException
     */
    public static void enviaEmail(Pedido p) throws IOException {
//        System.out.println("Iniciar envio do e-mail...");
//        Properties properties=new Properties();
//        
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.starttls.enable", "true");
//        properties.put("mail.smtp.host", "smtp.gmail.com");
//        properties.put("mail.smtp.port", "587");
//        
//        String myAccountEmail="adm.efmm@gmail.com";
//        String password="admtads5s";
//        String toEmail="email do destinatário";
//        
//        Session session = Session.getInstance(properties,new Authenticator() {
//            
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication(){
//                return new PasswordAuthentication(myAccountEmail, password);
//            }
//        });
//        
//        Message message =new MimeMessage(session);
//        try {
//            message.setFrom(new InternetAddress(myAccountEmail));
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
//            message.setSubject("Assunto do email [Relatório + departamento + id do relatório]");
//             Multipart emailContetnt=new MimeMultipart();
//             
//             //text body
//             MimeBodyPart textBodyPart=new MimeBodyPart();
//             textBodyPart.setText("Corpo do e-mail");
//             
//             
//             //attachment body part
//             MimeBodyPart pdfAttachment=new MimeBodyPart();
//             /**
//              * Exemplo:
//              * pdfAttachment.attachFile("C:\\Users\\Rajiv Pathak\\Downloads\\MIT TYBCA Sem 4 Advanced Java.pdf");
//              */
//             pdfAttachment.attachFile("Insira o caminho do anexo aquis");
//             
//             // attach body part
//             emailContetnt.addBodyPart(textBodyPart);
//             emailContetnt.addBodyPart(pdfAttachment);
//             
//             
//             //attach multipart to message
//             message.setContent(emailContetnt);
//             
//             Transport.send(message);
//             System.out.println("Email enviado.)");
//             
//             
//            
//        } catch (MessagingException ex) {
//            Logger.getLogger(EnviaEmail.class.getName()).log(Level.SEVERE, null, ex);
//        }
        Properties props = new Properties();
        /**
         * Parâmetros de conexão com servidor Gmail
         */
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "587");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("adm.efmm@gmail.com",
                        "admtads5s");
            }
        });

        /**
         * Ativa Debug para sessão
         */
        session.setDebug(true);

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("adm.efmm@gmail.com"));
            //Remetente

            /**
             * 0-Comercial
             * 1-Financeiro
             * 2-Logística
             * 3-Materiais
             * 4-Operações
             * 5-Produção
             * 6-RH
             * 7-TI
             * 8-Vendas
             */
            Integer var = Principal.cbbdep.getSelectedIndex();
            String departamento = "";
            switch (var) {
                case 0:
                    //falta email
                    break;
                case 1:
                    departamento = "erp.tfinanceiro@gmail.com";
                    break;
                case 2:
                    departamento = "logistica.erpp@gmail.com";
                    break;
                case 3:
                    departamento = "erp.materiais.tads@gmail.com";
                    break;
                case 4:
                    //to do
                    break;
                case 5:
                    departamento = "er.mproducao@gmail.com";
                    break;
                case 6:
                    //to do
                    break;
                case 7:
                    //to do
                    break;
                case 8:
                    departamento = "mu.amurim@gmail.com";
                    break;
            }
            Address[] toUser = InternetAddress.parse(departamento);
            String aprovacao = "";
            if(p.getAprovacao() == 1){
                aprovacao = "Aprovada";
            } else {
                aprovacao = "Negada";
            }
            
            String assunto = p.getTitulo();
            String titulo = p.getTitulo();
            String descri = p.getDescricao();
            String justi = p.getJustificativa();
            String dept = p.getDepartamento();
            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject(assunto);//Assunto
            message.setText("A solicitação intitulada '" + titulo + "' feita pelo departamento de '" +dept +
                            "' da qual a descrição é: '" + descri + "'\n" +
                            "Foi " + aprovacao + " diante a justificativa de '" + justi + "'\n\n" +
                            "Assinado: Departamento administrativo");
            /**
             * Método para enviar a mensagem criada
             */
            Transport.send(message);

            System.out.println("Feito!!!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

}
