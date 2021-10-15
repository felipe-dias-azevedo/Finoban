package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.dominio.entidades.Permissoes;
import com.bandtec.br.finoban.dominio.resposta.ResponseGeneric;
import com.bandtec.br.finoban.repository.PermissoesInterface;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-finoban/permissoes")
@AllArgsConstructor
public class PermissoesController {

    private PermissoesInterface permissoesRepository;

    @GetMapping
    public ResponseEntity getAllPermissoes() {
        List<Permissoes> permissoesList = permissoesRepository.resgatarTodasAsPermissoes();
        if (permissoesList.isEmpty())
            return ResponseEntity.status(204).build();

        return ResponseEntity.status(200).body(new ResponseGeneric<>(permissoesList));
    }

    @PostMapping
    public ResponseEntity postPermissao(@RequestBody Permissoes permissoes) {
        permissoesRepository.cadastrarPermissao(permissoes);
        return ResponseEntity.status(201).build();
    }

    @PutMapping
    public ResponseEntity atualizarCargo(@RequestBody Permissoes permissoes) {
        permissoesRepository.atualizarPermissao(permissoes);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarPermissao(@PathVariable int id) {
        permissoesRepository.deletarPermissao(id);
        return ResponseEntity.status(204).build();
    }

}
