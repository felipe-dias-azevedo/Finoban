package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.repository.AvaliacaoRepository;
import com.bandtec.br.finoban.models.Cadastro;
import com.bandtec.br.finoban.repository.CadastroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api-finoban")
public class CadastroController {

    private Cadastro cadastro;

    @Autowired
    private CadastroRepository cadastroRepository;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @PostMapping("/cadastro")
    public ResponseEntity novoCadastro(@RequestBody Cadastro novoCadastro) {
        novoCadastro.setDataCriacao(LocalDateTime.now());
        cadastroRepository.save(novoCadastro);
        return ResponseEntity.status(201).body("Cadastro efetuado com sucesso");
    }

    @GetMapping("/usuarios")
    public ResponseEntity listarUsuarios() {
        return ResponseEntity.status(200).body(cadastroRepository.findAll());
    }

//    @PostMapping("/avaliacao")
//    public ResponseEntity novaAvaliacao(@RequestBody Avaliacao novaAvaliacao) {
//        cadastro = new Cadastro("1", "5555","444","333","4444",4444);
//        novaAvaliacao.setDataAval(LocalDateTime.now());
//        avaliacaoRepository.save(new Avaliacao(novaAvaliacao.getAvalPositivo(), novaAvaliacao.getFeedbackAval(), novaAvaliacao.getDataAval(), cadastro));
//        return ResponseEntity.status(201).body("Avaliacao cadastrada com sucesso");
//    }
}
