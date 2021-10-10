package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.dominio.entidades.Permissoes;
import com.bandtec.br.finoban.dominio.resposta.ResponseGeneric;
import com.bandtec.br.finoban.service.admin.PermissoesService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-finoban/permissoes")
@AllArgsConstructor
public class PermissoesController {

    private PermissoesService permissoesService;

    @GetMapping
    public ResponseEntity getAllPermissoes() {
        List<Permissoes> permissoesList = permissoesService.resgatarTodasAsPermissoes();
        if (permissoesList.isEmpty())
            return ResponseEntity.status(204).build();

        return ResponseEntity.status(200).body(new ResponseGeneric<>(permissoesList));
    }

    @PostMapping
    public ResponseEntity postPermissao(@RequestBody Permissoes permissoes) {
        permissoesService.cadastrarPermissao(permissoes);
        return ResponseEntity.status(201).build();
    }

    @PutMapping
    public ResponseEntity atualizarCargo(@RequestBody Permissoes permissoes) {
        permissoesService.atualizarPermissao(permissoes);
        return ResponseEntity.status(201).build();
    }

}
