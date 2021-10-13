package com.bandtec.br.finoban.service;

import com.bandtec.br.finoban.dominio.entidades.Admin;
import com.bandtec.br.finoban.dominio.exceptions.TokenExpiradoException;
import com.bandtec.br.finoban.dominio.exceptions.TokenInvalidoException;
import com.bandtec.br.finoban.dominio.exceptions.configuracao.ExceptionGeneric;
import com.bandtec.br.finoban.infraestrutura.criptografia.Criptografia;
import com.bandtec.br.finoban.dominio.entidades.Usuario;
import com.bandtec.br.finoban.infraestrutura.helpers.DateHelper;
import com.bandtec.br.finoban.dominio.TokenDecodificadoModel;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;

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

@Service
public class TokenServiceImpl implements AuthService {

    // 60000ms = 1min
    // 1800000 = 30min
    private static final int TEMPO_EXPIRACAO = 1_800_000;
    private static final String KEY = "_FINOBAN_BEST";

    public String generatetokenUsuario(Usuario usuario) {

        return Jwts.builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setSubject(String.valueOf(usuario.getNome()))
                .setExpiration(new Date(System.currentTimeMillis() + TEMPO_EXPIRACAO))
                .signWith(SignatureAlgorithm.HS256, KEY)
                .compact();
    }

    @Override
    public String generateTokenAdmin(Admin admin) {
        return Jwts.builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setSubject(String.valueOf(admin.getNome()))
                .setExpiration(new Date(System.currentTimeMillis() + TEMPO_EXPIRACAO))
                .signWith(SignatureAlgorithm.HS256, KEY)
                .compact();
    }

    public String createJWT(Usuario usuario) {
        Map<String, Object> mapJwt = new HashMap<>();
        mapJwt.put("email", usuario.getEmail());
        mapJwt.put("token", Criptografia.criptografarParaHashingSHA256(usuario.getEmail()));
        return Jwts.builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setClaims(mapJwt)
                .setExpiration(new Date(System.currentTimeMillis() + TEMPO_EXPIRACAO))
                .signWith(SignatureAlgorithm.HS256, KEY)
                .compact();
    }

    public Claims decodeToken(String token) {
        return Jwts.parser()
                .setSigningKey(KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateJwt(String token) {
        String tokenTratado = token.replace("Bearer ", "");
        try {
            decodeToken(tokenTratado);
            return true;
        } catch (ExpiredJwtException e) {
            throw new TokenExpiradoException();
        } catch (SignatureException e) {
            throw new TokenInvalidoException();
        } catch (Exception e) {
            throw new ExceptionGeneric("0", "Erro Desconhecido");
        }
    }

    public boolean jwtExpirado(String token) {
        try {
            Claims claims = decodeToken(token);
            long value = Integer.parseInt(claims.get("exp").toString());
            return DateHelper.verificarJwtExpirou(value);
        } catch (Exception ex) {
            return true;
        }
    }

    public TokenDecodificadoModel converterToModel(String jwt) {
        try {
            Claims claims = decodeToken(jwt);
            long value = Integer.parseInt(claims.get("exp").toString());
            Instant instant = Instant.ofEpochSecond(value);
            Date date = Date.from(instant);
            return new TokenDecodificadoModel(
                    claims.get("email").toString(),
                    claims.get("token").toString(),
                    date
            );
        } catch (ExpiredJwtException ex) {
            return null;
        }
    }

}
