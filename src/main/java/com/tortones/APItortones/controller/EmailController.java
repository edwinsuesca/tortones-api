package com.tortones.APItortones.controller;

import com.tortones.APItortones.model.Porcion;
import org.springframework.web.bind.annotation.*;

import javax.mail.Session;
import java.util.Map;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@RestController
@RequestMapping("/api")
public class EmailController {

    @PostMapping("/email")
    public Map<String, String> sendQuoteEmail() {
        try {
            // Create a JavaMail session
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.vivaldi.net");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("tortones@vivaldi.net", "pwd_tortones");
                }
            });

            // Create a MimeMessage
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("tortones@vivaldi.net"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("destinatario1@gmail.com, destinatario2@gmail.com"));
            message.setSubject("Cotización de producto");
            message.setText("Hola Administrador,\n\nSe ha solicitado una cotización del siguiente producto:\n\n");

            // Send the message
            Transport.send(message);

            return Map.of("Message","El correo de cotización ha sido enviado al administrador.");
        } catch (Exception ex) {
            return Map.of("Message", ex.getMessage());
        }
    }
}
