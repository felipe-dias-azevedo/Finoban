package com.bandtec.br.finoban.service;

import com.bandtec.br.finoban.builder.AcessoBuilder;
import com.bandtec.br.finoban.builder.CadastroBuilder;
import com.bandtec.br.finoban.dominio.entidades.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.MalformedJwtException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TokenServiceTest {

    @Test
    @DisplayName("Decode token - Sucesso")
    void decodeToken() {
        TokenServiceImpl service = new TokenServiceImpl();
        AcessoBuilder acessoBuilder = new AcessoBuilder();
        Usuario usuario = acessoBuilder.setUsuario();

        String jwt = service.generateToken(usuario);
        Claims claims = service.decodeToken(jwt);

        assertEquals(usuario.getNome(), claims.getSubject());
        assertTrue(claims.getExpiration().before(new Date(System.currentTimeMillis() + 1_800_001)));
    }

    @Test
    @DisplayName("Decode token - Erro")
    void decodeTokenNotOK() {
        TokenServiceImpl service = new TokenServiceImpl();
        AcessoBuilder acessoBuilder = new AcessoBuilder();
        Usuario usuario = acessoBuilder.setUsuario();

        String jwt = String.valueOf(UUID.randomUUID());

        assertThrows(MalformedJwtException.class, () -> service.decodeToken(jwt));
    }
}