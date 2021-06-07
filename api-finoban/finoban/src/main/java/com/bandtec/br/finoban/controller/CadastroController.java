package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.entidades.Avaliacao;
import com.bandtec.br.finoban.repository.AcessoRepository;
import com.bandtec.br.finoban.repository.AvaliacaoRepository;
import com.bandtec.br.finoban.entidades.Usuario;
import com.bandtec.br.finoban.repository.CadastroRepository;
import com.bandtec.br.finoban.resposta.ResponseGeneric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api-finoban")
public class CadastroController {

    private Usuario cadastro;

    @Autowired
    private CadastroRepository cadastroRepository;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private AcessoRepository acessoRepository;

    @PostMapping("/cadastro")
    public ResponseEntity novoCadastro(@Valid @RequestBody Usuario novoCadastro) {
        novoCadastro.setDataCriacao(LocalDateTime.now());
        cadastroRepository.save(novoCadastro);
        return ResponseEntity.status(201).body("Cadastro efetuado com sucesso");
    }

    @GetMapping("/usuarios")
    public ResponseEntity listarUsuarios() {
        List<Usuario> usuarioList = (List<Usuario>) cadastroRepository.findAll();
        if (usuarioList.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(cadastroRepository.findAll());
        }
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity getUsuario(@PathVariable Integer id) {
        Optional<Usuario> usuario = cadastroRepository.findById(id);
        if (usuario.isPresent()) {
            return ResponseEntity.status(200).body(usuario);
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @PostMapping("/avaliacao")
    public ResponseEntity novaAvaliacao(@Valid @RequestBody Avaliacao novaAvaliacao) {
        if(novaAvaliacao.getAvalPositivo() != null && novaAvaliacao.getFeedbackAval() == null) {
            return ResponseEntity.status(400).body(new ResponseGeneric("feedbackAval não pode ser nulo",
                    null));
        }
        if (novaAvaliacao.getAvalPositivo().ordinal() == 2 || novaAvaliacao.getAvalPositivo().ordinal() == 1
        || novaAvaliacao.getAvalPositivo().ordinal() == 0) {
            if (acessoRepository.existsById(novaAvaliacao.getFkAcesso().getIdEntrada())) {
                novaAvaliacao.setDataAval(LocalDateTime.now());
                avaliacaoRepository.save(novaAvaliacao);
                return ResponseEntity.status(201).build();
            } else {
                return ResponseEntity.status(404)
                        .body(new ResponseGeneric("Não encontramos nenhum acesso com este Id",
                        null));
            }
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/avaliacoes")
    public ResponseEntity getAvaliacoes() {
        List<Avaliacao> avaliacaoList = (List<Avaliacao>) avaliacaoRepository.findAll();
        if (avaliacaoList.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(avaliacaoList);
        }
    }

    @GetMapping("/avaliacoes/{id}")
    public ResponseEntity getAvaliacao(@PathVariable int id) {
        Optional<Avaliacao> avaliacao = avaliacaoRepository.findById(id);
        if (avaliacao.isPresent()) {
            return ResponseEntity.status(200).body(avaliacao);
        } else {
            return ResponseEntity.status(404).body(new ResponseGeneric("Não encontramos nenhuma " +
                    "avaliação com este id", null));
        }
    }

    @DeleteMapping("/avaliacoes/{id}")
    public ResponseEntity deleteAvaliacao(@PathVariable int id) {
        if (avaliacaoRepository.existsById(id)) {
            avaliacaoRepository.deleteById(id);
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(404).body(new ResponseGeneric("Não encontramos nenhuma " +
                    "avaliação com este id", null));
        }
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity deleteUsuario(@PathVariable int id) {
        if (cadastroRepository.existsById(id)) {
            cadastroRepository.deleteById(id);
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }
}
