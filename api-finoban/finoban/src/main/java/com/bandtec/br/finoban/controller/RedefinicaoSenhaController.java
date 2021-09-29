package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.repository.GestaoRedefinicaoSenhaRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-finoban/redefinicao-senhas")
@AllArgsConstructor
public class RedefinicaoSenhaController {

    private GestaoRedefinicaoSenhaRepository gestaoRedefinicaoSenhaRepository;

    @GetMapping
    public ResponseEntity listarRedefinicoes() {
        return gestaoRedefinicaoSenhaRepository.listarRedefinicoes();
    }
}
