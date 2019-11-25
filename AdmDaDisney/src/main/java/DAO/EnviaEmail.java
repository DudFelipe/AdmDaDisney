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

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;

/**
 *
 * @author fernando.lcoutinho
 */
public class EnviaEmail {

    /**
     * @param p
     * @throws java.io.IOException
     */
    public static void enviaEmail(Document doc, Pedido p) throws IOException {
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
                    //falta email
                    break;
                case 5:
                    departamento = "er.mproducao@gmail.com";
                    break;
                case 6:
                    //falta email
                    break;
                case 7:
                    //falta email
                    break;
                case 8:
                    departamento = "wcslxcolossus@gmail.com";
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
            
            FileDataSource fds = new FileDataSource("Solicitacao" + p.getId() + ".pdf");
            message.setDataHandler(new DataHandler(fds));
            message.setFileName(fds.getName());
            
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
