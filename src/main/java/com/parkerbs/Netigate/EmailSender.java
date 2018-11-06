/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parkerbs.Netigate;

import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author Ryan
 */
public class EmailSender {

    private static final String SMTP_SERVER = "smtp.office365.com";
    private static final int SMTP_PORT = 587;
    private static String SMTP_EMAIL;
    private static String SMTP_PASSWORD;

    private static String to;
    private static String from;

    private static final String subject = "Weekly NPS report";
    private static final String messageContent = " ";

    Properties propertiesObject = new Properties();

    public static void sendEmail(String file) {
        SMTP_EMAIL = ConfigFile.getSender();
        SMTP_PASSWORD = ConfigFile.getPassword();
        to = ConfigFile.getSendTo();
        from = ConfigFile.getSender();

        final Session session = Session.getInstance(EmailSender.getEmailProperties(), new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SMTP_EMAIL, SMTP_PASSWORD);
            }

        });

        try {
            InternetAddress sendTo = new InternetAddress(to);
            InternetAddress sendFrom = new InternetAddress(from);
            sendFrom.validate();
            sendTo.validate();
            final Message message = new MimeMessage(session);
            message.setRecipient(Message.RecipientType.TO, sendTo);
            message.setFrom(sendFrom);
            message.setSubject(subject);
            message.setText(messageContent);
            message.setSentDate(new Date());
            Multipart multipart = new MimeMultipart();

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            String fileName = "Customers sent to.csv";

            DataSource source = new FileDataSource(file);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(fileName);
            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);
            Transport.send(message);
        } catch (MessagingException ex) {
            if (ConfigFile.getError()) {
                ErrorLogger.writeError(ex, ErrorLogger.ERROR);
            }
        }
    }

    private static Properties getEmailProperties() {
        final Properties config = new Properties();
        config.put("mail.smtp.auth", "true");
        config.put("mail.smtp.starttls.enable", "true");
        config.put("mail.smtp.host", SMTP_SERVER);
        config.put("mail.smtp.port", SMTP_PORT);
        return config;
    }
}
