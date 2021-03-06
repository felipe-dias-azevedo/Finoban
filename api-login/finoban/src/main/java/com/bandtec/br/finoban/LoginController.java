package com.bandtec.br.finoban;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private CadastroRepository cadastroRepository;

    @GetMapping(path = "/login/{email}/{senha}")
    public Object login(@PathVariable String email, @PathVariable String senha) {

        Cadastro verificaEmail = cadastroRepository.findByEmailContaining(email);

        if(verificaEmail == null) {
            return "Email não encontrado";
        }

        if(!verificaEmail.getSenha().equals(senha)) {
            return "Senha incorreta";
        }

        return "Login efetuado";

    }

}
