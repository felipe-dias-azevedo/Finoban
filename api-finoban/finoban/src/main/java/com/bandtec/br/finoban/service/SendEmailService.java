package com.bandtec.br.finoban.service;


import com.bandtec.br.finoban.dominio.entidades.Usuario;
import com.bandtec.br.finoban.dominio.exceptions.FalhaAoEnviarEmailException;
import com.bandtec.br.finoban.repository.SendEmailRepository;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class SendEmailService implements SendEmailRepository {

    private JavaMailSender javaMailSender;


    public void sendEmail(Usuario usuario, String url) {
        try {

            MimeMessage mensagem = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mensagem, true);
            mimeMessageHelper.setTo(usuario.getEmail());
            mimeMessageHelper.setSubject("Finoban - Redefinição de senha");
            mimeMessageHelper.setText(String.format(
            "<h1> Redefinição de senha. </h1>" +
            "<br>" +
            "<img src='https://i.imgur.com/8EkHsI2.png'>"+
            "<br>"+
            "<h5>Clique no Link para redefinir a senha: %s<h5>",
            url), true);
            javaMailSender.send(mensagem);
        } catch (Exception ex) {
            throw new FalhaAoEnviarEmailException();
        }
    }
}
