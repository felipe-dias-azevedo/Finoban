package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.demo.Cadastro;
import com.bandtec.br.finoban.demo.CadastroRepository;
import com.bandtec.br.finoban.demo.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api-finoban")
public class LoginController {

    @Autowired
    private CadastroRepository cadastroRepository;

    @PostMapping("/login")
    public ResponseEntity novoLogin(@RequestBody Login novoLogin) {

        Cadastro verificaEmail = cadastroRepository.findByEmailContaining(novoLogin.getEmail());

        if(verificaEmail == null) {
            return ResponseEntity.status(204).body("Email não encontrado");
        }

        if(!verificaEmail.getSenha().equals(novoLogin.senha)) {
            return ResponseEntity.status(204).body("Senha incorreta");
        }

        return ResponseEntity.status(200).body("Login efetuado com sucesso");

    }

}
