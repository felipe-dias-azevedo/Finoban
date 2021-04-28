package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.entidades.Regiao;
import com.bandtec.br.finoban.repository.RegiaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api-finoban/regioes")
public class RegiaoController {

    @Autowired
    private RegiaoRepository repository;

    @GetMapping
    public ResponseEntity getRegioes() {
        return ResponseEntity.status(200).body(repository.findAllRegiaoLatest());
    }

    @PostMapping
    public ResponseEntity postRegioes(@RequestParam("token") String token, @RequestBody Regiao regiao) {
        System.out.println(token);
        if (!token.equals("Zmlub2JhbmVhbWVsaG9yZG9tdW5kbw==")) {
            return ResponseEntity.status(400).build();
        }
        repository.save(regiao);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteRegiao(@RequestParam(value = "token")String token, @PathVariable int id) {
        if (!token.equals("Zmlub2JhbmVhbWVsaG9yZG9tdW5kbw==")) {
            return ResponseEntity.status(400).build();
        }
        if (!repository.existsById(id)) {
            return ResponseEntity.status(404).build();
        }
        repository.deleteById(id);
        return ResponseEntity.status(200).build();
    }

    @PutMapping
    public ResponseEntity atualizarRegiao(@RequestBody Regiao regiao, @RequestParam("token")String token) {
        if (!token.equals("Zmlub2JhbmVhbWVsaG9yZG9tdW5kbw==")) {
            return ResponseEntity.status(400).build();
        }
        if(!repository.existsById(regiao.getIdRegiao())) {
            return ResponseEntity.status(404).build();
        }
        repository.setRegiaoById(regiao.getDescricaoRegiao(), regiao.getValorRegiao(), regiao.getDataCraw(), regiao.getIdRegiao());
        return ResponseEntity.status(200).build();
    }
}
