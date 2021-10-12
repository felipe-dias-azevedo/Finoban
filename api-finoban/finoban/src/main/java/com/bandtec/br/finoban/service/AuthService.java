package com.bandtec.br.finoban.service;

import com.bandtec.br.finoban.dominio.TokenDecodificadoModel;
import com.bandtec.br.finoban.dominio.entidades.Admin;
import com.bandtec.br.finoban.dominio.entidades.Usuario;
import io.jsonwebtoken.Claims;


public interface AuthService {
    public String generatetokenUsuario(Usuario usuario);

    public String generateTokenAdmin(Admin admin);

    public String createJWT(Usuario usuario);

    public Claims decodeToken(String token);

    public boolean jwtExpirado(String token);

    public boolean validateJwt(String token);

    public TokenDecodificadoModel converterToModel(String jwt);

}
