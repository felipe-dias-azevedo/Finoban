package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.dominio.entidades.RedefinicaoSenha;
import com.bandtec.br.finoban.dominio.resposta.SingleResponse;
import com.bandtec.br.finoban.repository.GestaoRedefinicaoSenhaRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api-finoban/redefinicao-senhas")
@AllArgsConstructor
public class RedefinicaoSenhaController {

    private GestaoRedefinicaoSenhaRepository gestaoRedefinicaoSenhaRepository;

    @GetMapping
    public ResponseEntity listarRedefinicoes() {
        List<RedefinicaoSenha> redefinicaoSenhaList = gestaoRedefinicaoSenhaRepository.listarRedefinicoes();
        if (redefinicaoSenhaList.isEmpty())
            return ResponseEntity.status(204).build();

        return ResponseEntity.status(200).body(new SingleResponse(redefinicaoSenhaList));
    }
}
