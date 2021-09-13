package com.bandtec.br.finoban.service;

import com.bandtec.br.finoban.builder.AcessoBuilder;
import com.bandtec.br.finoban.entidades.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.MalformedJwtException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TokenServiceTest {

    @Test
    @DisplayName("Decode token - Sucesso")
    void decodeToken() {
        TokenService service = new TokenService();
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
        TokenService service = new TokenService();
        AcessoBuilder acessoBuilder = new AcessoBuilder();
        Usuario usuario = acessoBuilder.setUsuario();

        String jwt = String.valueOf(UUID.randomUUID());

        assertThrows(MalformedJwtException.class, () -> service.decodeToken(jwt));
    }
}