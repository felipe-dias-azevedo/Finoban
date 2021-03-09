package com.bandtec.br.finoban;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private CadastroRepository cadastroRepository;

    @PostMapping("/login")
    public @ResponseBody
    Object login(@RequestParam String email, @RequestParam String senha) {

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
