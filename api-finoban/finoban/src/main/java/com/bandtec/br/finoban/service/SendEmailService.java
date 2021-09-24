package com.bandtec.br.finoban.service;


import com.bandtec.br.finoban.entidades.Usuario;
import com.bandtec.br.finoban.exceptions.EnviarEmailException;
import com.bandtec.br.finoban.repository.SendEmailRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Service
@AllArgsConstructor
public class SendEmailService implements SendEmailRepository {

    private JavaMailSender javaMailSender;


    public ResponseEntity sendEmail(Usuario usuario, String url) throws MessagingException, IOException {
        try {
            MimeMessage mensagem = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mensagem, true);


            mimeMessageHelper.setTo(usuario.getEmail());
            mimeMessageHelper.setSubject("Finoban - Redefinição de senha");
            mimeMessageHelper.setText(String.format("<h1> Redefinição de senha. </h1>"), true);
            mimeMessageHelper.setText(String.format("<p>Clique no Link para redefinir a senha: %s", url), true);
            System.out.println("Enviando email...");
            javaMailSender.send(mensagem);
            return ResponseEntity.status(201).build();
        } catch (Exception ex) {
            return new ResponseEntity(new EnviarEmailException(), HttpStatus.NOT_FOUND);
        }
    }
}
