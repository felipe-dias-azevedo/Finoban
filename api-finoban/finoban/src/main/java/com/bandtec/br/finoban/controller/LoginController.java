package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.entidades.Usuario;
import com.bandtec.br.finoban.repository.CadastroRepository;
import com.bandtec.br.finoban.models.Login;
import com.bandtec.br.finoban.resposta.ResponseGeneric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api-finoban")
public class LoginController {

    @Autowired
    private CadastroRepository cadastroRepository;

    List<String> usuariosLogados = new ArrayList<>();

    @PostMapping("/login")
    public ResponseEntity novoLogin(@RequestBody Login novoLogin) {
        Usuario verificaEmail = cadastroRepository.findByEmailContaining(novoLogin.getEmail());
        if (verificaEmail == null) {
            return ResponseEntity.status(204).body(new ResponseGeneric("Email não encontrado"));
        }
        if (!verificaEmail.getSenha().equals(novoLogin.senha)) {
            return ResponseEntity.status(204).body(new ResponseGeneric("Senha incorreta"));
        }
        String emailLogado = novoLogin.getEmail();
        return verificarUsuariosLogados(usuariosLogados, emailLogado, verificaEmail, usuariosLogados.size());
    }

    @PostMapping("/logoff")
    public ResponseEntity efetuarLogoff(@RequestBody Login novoLogin) {
        return efetuarLogoffUsuarioLogado(usuariosLogados, novoLogin, new String(), usuariosLogados.size());
    }

    public static ResponseEntity efetuarLogoffUsuarioLogado(List<String> usuariosLogados, Login login,
                                                            String resultado, int indice) {
        if (indice < 0) {
            return ResponseEntity.status(404).body(new ResponseGeneric("Usuário não logado"));
        } else {
            if (usuariosLogados.contains(login.getEmail())) {
                usuariosLogados.remove(login.getEmail());
                resultado = String.format("Usuário deslogado com sucesso!");
                return ResponseEntity.status(200).body(new ResponseGeneric(resultado));
            }
            return efetuarLogoffUsuarioLogado(usuariosLogados, login, resultado, indice -1);
        }
    }

    public static ResponseEntity verificarUsuariosLogados(List<String> usuariosLogados,
                                                          String emailLogado, Usuario verificaEmail,
                                                          int indice) {
        if (indice < 0) {
            return ResponseEntity.status(404).body(new ResponseGeneric("Usuário já logado."));
        } else {
            if (!usuariosLogados.contains(emailLogado)) {
                usuariosLogados.add(emailLogado);
                return ResponseEntity.status(200).body(new ResponseGeneric(verificaEmail, null));
            }
            return verificarUsuariosLogados(usuariosLogados, emailLogado, verificaEmail, indice-1);
        }
    }
}
