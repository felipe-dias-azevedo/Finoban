package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.demo.AvaliacaoRepository;
import com.bandtec.br.finoban.demo.Cadastro;
import com.bandtec.br.finoban.demo.CadastroRepository;
import com.bandtec.br.finoban.models.Avaliacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@RestController
@RequestMapping("/api-finoban")
public class CadastroController {

    @Autowired
    private CadastroRepository cadastroRepository;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @PostMapping("/cadastro")
    public ResponseEntity novoCadastro(@RequestBody Cadastro novoCadastro) {
        cadastroRepository.save(novoCadastro);
        return ResponseEntity.status(201).body("Cadastro efetuado com sucesso");
    }

    @PostMapping("/avaliacao")
    public ResponseEntity novaAvaliacao(@RequestBody Avaliacao novaAvaliacao) {
        novaAvaliacao.setDataAval(LocalDateTime.now());
        avaliacaoRepository.save(novaAvaliacao);
        return ResponseEntity.status(201).body("Avaliacao cadastrada com sucesso");
    }


    @GetMapping("/listar")
    public Iterable<Cadastro> obterUsuarios() {
        return cadastroRepository.findAll();
    }

}
