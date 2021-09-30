package com.bandtec.br.finoban.infraestrutura.helpers;

import com.bandtec.br.finoban.dominio.entidades.Usuario;
import com.bandtec.br.finoban.dominio.exceptions.UsuarioLogadoException;
import com.bandtec.br.finoban.dominio.exceptions.UsuarioNaoLogadoException;
import com.bandtec.br.finoban.dominio.Login;
import com.bandtec.br.finoban.dominio.resposta.ResponseGeneric;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class RecursoesHelper {
    public static Usuario verificarUsuariosLogados(List<String> usuariosLogados,
                                                          String emailLogado, Usuario verificaEmail,
                                                          int indice) {
        if (indice < 0) {
            throw new UsuarioLogadoException();
        } else {
            if (!usuariosLogados.contains(emailLogado)) {
                usuariosLogados.add(emailLogado);
                return verificaEmail;
            }
            return verificarUsuariosLogados(usuariosLogados, emailLogado, verificaEmail, indice-1);
        }
    }

    public static String efetuarLogoffUsuarioLogado(List<String> usuariosLogados, Login login,
                                                            int indice) {
        if (indice < 0) {
            throw new UsuarioNaoLogadoException();
        } else {
            if (usuariosLogados.contains(login.getEmail())) {
                usuariosLogados.remove(login.getEmail());
                return "Usuário deslogado com sucesso!";
            }
            return efetuarLogoffUsuarioLogado(usuariosLogados, login, indice -1);
        }
    }
}
