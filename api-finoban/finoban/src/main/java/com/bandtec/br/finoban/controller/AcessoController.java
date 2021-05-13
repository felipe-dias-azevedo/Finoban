package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.entidades.Acesso;
import com.bandtec.br.finoban.repository.AcessoRepository;
import com.bandtec.br.finoban.repository.CadastroRepository;
import com.bandtec.br.finoban.repository.RegiaoRepository;
import com.bandtec.br.finoban.resposta.ResponseGeneric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api-finoban")
public class AcessoController {

    @Autowired
    private AcessoRepository acessoRepository;

    @Autowired
    private CadastroRepository cadastroRepository;

    @Autowired
    private RegiaoRepository regiaoRepository;

    @PostMapping("/acesso")
    public ResponseEntity postAcesso(@RequestBody Acesso acesso) {
        if (cadastroRepository.existsById(acesso.getFkCliente().getId())) {
            if (regiaoRepository.existsById(acesso.getFkRegiao().getIdRegiao())) {
                acessoRepository.save(acesso);
                return ResponseEntity.status(201).build();
            } else {
                return ResponseEntity.status(404).body(new ResponseGeneric(null,
                        Collections.singletonList("Não foi encontrado região para este Id")));
            }
        } else {
            List<String> list = new ArrayList<>();
            list.add("Não foi encontrado região para este Id");
            list.add("Não foi encontrado Cliente para este Id");
            return ResponseEntity.status(404).body(new ResponseGeneric("Erro ao fazer a requisição", list));
        }
    }
}
