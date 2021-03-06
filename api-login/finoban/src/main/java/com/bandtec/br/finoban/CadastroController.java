package com.bandtec.br.finoban;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CadastroController {

    @Autowired
    private CadastroRepository cadastroRepository;

    @PostMapping("/cadastro")
    public @ResponseBody
    Cadastro novoCadastro(@RequestParam String nome, @RequestParam String cnpj,
                          @RequestParam String email, @RequestParam String senha,
                          @RequestParam String cep, String numeroCasa) {
        Cadastro cadastro = new Cadastro(nome, cnpj, email, senha, cep, numeroCasa);
        cadastroRepository.save(cadastro);
        return cadastro;
    }

    @GetMapping("/listar")
    public Iterable<Cadastro> obterUsuarios() {
        return cadastroRepository.findAll();
    }

}
