package com.bandtec.br.finoban;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {

    public boolean logado;

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
            logado = true;
            return "Login efetuado";
    }

    @PostMapping("/deslogar")
    public @ResponseBody String deslogar(@RequestParam String email) {

        Cadastro verificaEmail = cadastroRepository.findByEmailContaining(email);
        logado = false;

        if(verificaEmail == null) {
            return "Email não encontrado";
        }

        else {
            return "Usuário " + verificaEmail.getNome() + " foi deslogado com sucesso!";
        }

    }

}
