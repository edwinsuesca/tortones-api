package com.tortones.APItortones.controller;

import com.tortones.APItortones.model.Cotizacion;
import com.tortones.APItortones.model.Usuario;
import com.tortones.APItortones.repository.UsuarioRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import jakarta.mail.internet.MimeMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmailController {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/cotizacion")
    public Map<String, String> enviarCotizacion(@RequestBody Cotizacion cotizacion) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        try {
            helper.setFrom("tortones@vivaldi.net");
            helper.setTo(cotizacion.getRemitente());
            helper.setSubject(cotizacion.getAsunto());
            helper.setText(cotizacion.getMensaje(), true);
            javaMailSender.send(message);
            return Map.of("message", "La cotización se envió correctamente.");
        } catch (MessagingException e) {
            return Map.of("message", e.getMessage());
        }
    }

    @PostMapping("/notificacion-admin")
    public Map<String, String> notificarAdmin(@RequestBody Cotizacion cotizacion) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        List<Usuario> usuarios = usuarioRepository.findByRol("Administrador");
        List<InternetAddress> destinatarios = new ArrayList<>();
        try {
            for (Usuario usuario : usuarios) {
                destinatarios.add(new InternetAddress(usuario.getCorreo()));
            }
            helper.setFrom("tortones@vivaldi.net");
            helper.setTo(destinatarios.toArray(new InternetAddress[destinatarios.size()]));
            helper.setSubject(cotizacion.getAsunto());
            helper.setText(cotizacion.getMensaje(), true);
            javaMailSender.send(message);
            return Map.of("message", "La cotización se envió correctamente.");
        } catch (MessagingException e) {
            return Map.of("message", e.getMessage());
        }
    }
}
