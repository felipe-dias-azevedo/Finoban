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

    @PostMapping("/avaliacao")
    public ResponseEntity novaAvaliacao(@RequestBody Avaliacao novaAvaliacao) {

        cadastro = new Cadastro("1", "5555","444","333","4444",4444);

        novaAvaliacao.setDataAval(LocalDateTime.now());
        avaliacaoRepository.save(new Avaliacao(novaAvaliacao, cadastro));
        return ResponseEntity.status(201).body("Avaliacao cadastrada com sucesso");
    }

}
