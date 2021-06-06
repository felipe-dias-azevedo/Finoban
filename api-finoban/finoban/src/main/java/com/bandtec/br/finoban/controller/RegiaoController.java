package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.entidades.Regiao;
import com.bandtec.br.finoban.entidades.Usuario;
import com.bandtec.br.finoban.repository.RegiaoRepository;
import com.bandtec.br.finoban.resposta.ResponseGeneric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api-finoban/regioes")
public class RegiaoController {

    @Autowired
    private RegiaoRepository repository;

    @GetMapping
    public ResponseEntity getRegioes() {
        List<Regiao> regiaoList = repository.findAllRegiaoLatest();
        if (regiaoList.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(repository.findAllRegiaoLatest());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getRegiao(@PathVariable Integer id) {
        Optional<Regiao> regiao = repository.findById(id);
        if (regiao.isPresent()) {
            return ResponseEntity.status(200).body(regiao);
        } else {
            return ResponseEntity.status(404).build();
        }
    }


    @PostMapping
    public ResponseEntity postRegiao(@RequestBody Regiao regiao) {
//        System.out.println(token);
//        if (!token.equals("Zmlub2JhbmVhbWVsaG9yZG9tdW5kbw==")) {
//            return ResponseEntity.status(400).build();
//        }
        repository.save(regiao);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteRegiao(@PathVariable int id) {
//        if (!token.equals("Zmlub2JhbmVhbWVsaG9yZG9tdW5kbw==")) {
//            return ResponseEntity.status(400).build();
//        }
        if (!repository.existsById(id)) {
            return ResponseEntity.status(404).build();
        }
        repository.deleteById(id);
        return ResponseEntity.status(200).build();
    }

    @PutMapping
    public ResponseEntity atualizarRegiao(@RequestBody Regiao regiao) {
//        if (!token.equals("Zmlub2JhbmVhbWVsaG9yZG9tdW5kbw==")) {
//            return ResponseEntity.status(400).build();
//        }
        if (!repository.existsById(regiao.getIdRegiao())) {
            return ResponseEntity.status(404).build();
        }
        repository.setRegiaoById(regiao.getDescricaoRegiao(), regiao.getValorRegiao(), regiao.getDataCraw(), regiao.getIdRegiao());
        return ResponseEntity.status(200).body(new ResponseGeneric("Região atualizada.", null));
    }
}
