package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.models.Cadastro;
import com.bandtec.br.finoban.models.DocumentoLayout;
import com.bandtec.br.finoban.repository.CadastroRepository;
import com.bandtec.br.finoban.models.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api-finoban")
public class LoginController {

    @Autowired
    private CadastroRepository cadastroRepository;

    List<String> usuariosLogados = new ArrayList<>();

    @PostMapping("/login")
    public ResponseEntity novoLogin(@RequestBody Login novoLogin) {

        Cadastro verificaEmail = cadastroRepository.findByEmailContaining(novoLogin.getEmail());

        if (verificaEmail == null) {
            return ResponseEntity.status(204).body("Email não encontrado");
        }

        if (!verificaEmail.getSenha().equals(novoLogin.senha)) {
            return ResponseEntity.status(204).body("Senha incorreta");
        }

        String emailLogado = novoLogin.getEmail();

        for (int i=0; i<usuariosLogados.size(); i++) {
            if (!usuariosLogados.contains(emailLogado)) {
                usuariosLogados.add(emailLogado);
            }
        }

        return ResponseEntity.status(200).body("Login efetuado com sucesso");

    }

    @PostMapping("/logoff")
    public ResponseEntity efetuarLogoff(@RequestBody Login novoLogin) {

        for (int i=0; i<usuariosLogados.size(); i++) {
            if (usuariosLogados.contains(novoLogin.getEmail())) {
                usuariosLogados.remove(novoLogin.getEmail());
            }
        }

        Cadastro verificaEmail = cadastroRepository.findByEmailContaining(novoLogin.getEmail());

        String resultado = String.format("%s, você foi deslogado com sucesso!", verificaEmail.getNome());

        return ResponseEntity.status(200).body(resultado);

    }

}
