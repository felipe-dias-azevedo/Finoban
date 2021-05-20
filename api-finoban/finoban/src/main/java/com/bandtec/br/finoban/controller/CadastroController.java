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
        return ResponseEntity.status(200).body(cadastroRepository.findAll());
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
        if (novaAvaliacao.getAvalPositivo().ordinal() == 2) {
            if (acessoRepository.existsById(novaAvaliacao.getFkAcesso().getIdEntrada())) {
                avaliacaoRepository.save(novaAvaliacao);
                return ResponseEntity.status(201).build();
            } else {
                return ResponseEntity.status(404)
                        .body(new ResponseGeneric("Não encontramos nenhum acesso com este Id",
                        null));
            }
        } else if(novaAvaliacao.getAvalPositivo() != null && novaAvaliacao.getFeedbackAval() == null) {
            return ResponseEntity.status(400).body(new ResponseGeneric("feedbackAval não pode ser nulo",
                    null));
        }
        return ResponseEntity.status(404).build();
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
