package com.bandtec.br.finoban.recursoes;

import com.bandtec.br.finoban.entidades.Usuario;
import com.bandtec.br.finoban.exceptions.UsuarioLogadoException;
import com.bandtec.br.finoban.exceptions.UsuarioNaoLogadoException;
import com.bandtec.br.finoban.models.Login;
import com.bandtec.br.finoban.resposta.ResponseGeneric;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class RecursoesHelper {
    public static ResponseEntity verificarUsuariosLogados(List<String> usuariosLogados,
                                                          String emailLogado, Usuario verificaEmail,
                                                          int indice) {
        if (indice < 0) {
            return new ResponseEntity(new UsuarioLogadoException(), HttpStatus.NOT_FOUND);
        } else {
            if (!usuariosLogados.contains(emailLogado)) {
                usuariosLogados.add(emailLogado);
                return ResponseEntity.status(200).body(new ResponseGeneric(verificaEmail));
            }
            return verificarUsuariosLogados(usuariosLogados, emailLogado, verificaEmail, indice-1);
        }
    }

    public static ResponseEntity efetuarLogoffUsuarioLogado(List<String> usuariosLogados, Login login,
                                                            int indice) {
        if (indice < 0) {
            return new ResponseEntity(new UsuarioNaoLogadoException(), HttpStatus.NOT_FOUND);
        } else {
            if (usuariosLogados.contains(login.getEmail())) {
                usuariosLogados.remove(login.getEmail());
                return ResponseEntity.status(200).body(new ResponseGeneric("Usuário deslogado com sucesso!"));
            }
            return efetuarLogoffUsuarioLogado(usuariosLogados, login, indice -1);
        }
    }
}
