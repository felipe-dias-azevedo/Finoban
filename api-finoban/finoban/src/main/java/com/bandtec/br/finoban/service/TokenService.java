package com.bandtec.br.finoban.service;

import com.bandtec.br.finoban.entidades.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.UUID;

/*
 * Estratégia: Bearer Authentication
 *   Token recebido no login
 *   Enviado no Authorization(header)
 *      Sintax: Bearer + Token
 *   Fonte Authentication schemes: "https://developer.mozilla.org/en-US/docs/Web/HTTP/Authentication#authentication_schemes"
 * */

public class TokenService {

    // 60000ms = 1min
    // 1800000 = 30min
    private int tempoExpiracao = 1_800_000;
    private String key = String.valueOf(UUID.randomUUID());

    public String generateToken(Usuario usuario) {

        return Jwts.builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setSubject(String.valueOf(usuario.getId()))
                .setExpiration(new Date(System.currentTimeMillis() + tempoExpiracao))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    public Claims decodeToken(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }
}
