package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.entidades.Acesso;
import com.bandtec.br.finoban.models.Pilha;
import com.bandtec.br.finoban.repository.AcessoRepository;
import com.bandtec.br.finoban.repository.CadastroRepository;
import com.bandtec.br.finoban.repository.RegiaoRepository;
import com.bandtec.br.finoban.resposta.ResponseGeneric;
import com.bandtec.br.finoban.resposta.RespostaApi;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api-finoban/acessos")
public class AcessoController {

    @Autowired
    private AcessoRepository acessoRepository;

    @GetMapping("/{id}")
    public ResponseEntity getAcesso(@PathVariable int id) {
        Optional<Acesso> acesso = acessoRepository.findById(id);
        if (acesso.isPresent()) {
            return ResponseEntity.status(200).body(acesso);
        } else {
            return ResponseEntity.status(404).body(new ResponseGeneric<>("Não foi encontrado acesso para este Id",
                    null));
        }
    }

    @Autowired
    private CadastroRepository cadastroRepository;

    @Autowired
    private RegiaoRepository regiaoRepository;

    Pilha<Integer> pilhaUltimos = new Pilha<>(5);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registro concluido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Falha ao concluir requisição")
    })
    @PostMapping
    public ResponseEntity postAcesso(@RequestBody Acesso acesso) {
        if (cadastroRepository.existsById(acesso.getFkCliente().getId())) {
            if (regiaoRepository.existsById(acesso.getFkRegiao().getIdRegiao())) {
                acessoRepository.save(acesso);
                return ResponseEntity.status(201).build();
            } else {
                return ResponseEntity.status(404).body(new ResponseGeneric("Não foi encontrado região para " +
                        "este Id", null));
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

    @DeleteMapping("/ultimos")
    public ResponseEntity getUltimosAcessos() {

        if (pilhaUltimos.isEmpty()) {
            List<Acesso> acessoList = acessoRepository.findTop5ByOrderByDataHoraSaidaDesc();
            if (acessoList.isEmpty()) {
                return ResponseEntity.status(404).build();
            }
            for (int i = acessoList.size() - 1; i >= 0; i--) {
                pilhaUltimos.push(acessoList.get(i).getIdEntrada());
            }
            acessoRepository.deleteById(pilhaUltimos.pop());
            return ResponseEntity.status(200).build();
        }
        acessoRepository.deleteById(pilhaUltimos.pop());
        return ResponseEntity.status(200).build();
    }


}
