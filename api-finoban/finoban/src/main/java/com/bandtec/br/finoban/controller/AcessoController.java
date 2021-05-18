package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.entidades.Acesso;
import com.bandtec.br.finoban.repository.AcessoRepository;
import com.bandtec.br.finoban.repository.CadastroRepository;
import com.bandtec.br.finoban.repository.RegiaoRepository;
import com.bandtec.br.finoban.resposta.ResponseGeneric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api-finoban/acessos")
public class AcessoController {

    @Autowired
    private AcessoRepository acessoRepository;

    @Autowired
    private CadastroRepository cadastroRepository;

    @Autowired
    private RegiaoRepository regiaoRepository;

    @PostMapping
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

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAcesso(@PathVariable int id) {
        if (acessoRepository.existsById(id)) {
            acessoRepository.deleteById(id);
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(404).body(new ResponseGeneric<>("Não foi encontrado acesso para este Id",
                    null));
        }
    }

    @GetMapping
    public ResponseEntity getAcessos() {
        List<Acesso> acessoList = acessoRepository.findAll();
        if (acessoList.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(acessoRepository.findAll());
        }
    }
}
