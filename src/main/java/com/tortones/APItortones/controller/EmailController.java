package com.tortones.APItortones.controller;

import com.tortones.APItortones.model.Cotizacion;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import jakarta.mail.internet.MimeMessage;

@RestController
@RequestMapping("/api")
public class EmailController {

    @Autowired
    private JavaMailSender javaMailSender;

    @PostMapping("/cotizacion")
    public ResponseEntity<String> enviarCorreo(@RequestBody Cotizacion cotizacion) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        try {
            helper.setFrom("tortones@vivaldi.net");
            helper.setTo(cotizacion.getRemitente());
            helper.setSubject(cotizacion.getAsunto());
            helper.setText(cotizacion.getMensaje(), true);
            javaMailSender.send(message);
            return ResponseEntity.ok("La cotización se envió correctamente.");
        } catch (MessagingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al enviar la cotizacion: " + e.getMessage());
        }
    }
}
