package com.bandtec.br.finoban.repository;

import com.bandtec.br.finoban.dominio.entidades.Usuario;
import org.springframework.http.ResponseEntity;

import javax.mail.MessagingException;
import java.io.IOException;

public interface SendEmailRepository {
    void sendEmail(Usuario usuario, String url) throws MessagingException, IOException;
}
