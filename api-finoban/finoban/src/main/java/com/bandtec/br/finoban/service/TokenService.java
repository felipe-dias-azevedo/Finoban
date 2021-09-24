package com.bandtec.br.finoban.service;

import com.bandtec.br.finoban.criptografia.Criptografia;
import com.bandtec.br.finoban.entidades.Usuario;
import com.bandtec.br.finoban.helpers.DateHelper;
import com.bandtec.br.finoban.models.TokenDecodificadoModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    private String key = "_FINOBAN_BEST";

    public String generateToken(Usuario usuario) {

        return Jwts.builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setSubject(String.valueOf(usuario.getNome()))
                .setExpiration(new Date(System.currentTimeMillis() + tempoExpiracao))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    public String createJWT(Usuario usuario) {
        Map<String, Object> mapJwt = new HashMap<>();
        mapJwt.put("email", usuario.getEmail());
        mapJwt.put("token", Criptografia.criptografarParaHashingSHA256(usuario.getEmail()));
        return Jwts.builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setClaims(mapJwt)
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

    public boolean jwtExpirado(String token) {
        Claims claims = decodeToken(token);
        long value = Integer.parseInt(claims.get("exp").toString());
        return DateHelper.verificarJwtExpirou(value);
    }

    public TokenDecodificadoModel converterToModel(String jwt) {
        Claims claims = decodeToken(jwt);
        long value = Integer.parseInt(claims.get("exp").toString());
        Instant instant = Instant.ofEpochSecond(value);
        Date date = Date.from(instant);
        return new TokenDecodificadoModel(
                claims.get("email").toString(),
                claims.get("token").toString(),
                date
                );
    }

}
