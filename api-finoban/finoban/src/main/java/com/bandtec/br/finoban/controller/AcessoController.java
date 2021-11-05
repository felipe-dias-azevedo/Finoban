package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.dominio.entidades.Acesso;
import com.bandtec.br.finoban.dominio.resposta.SingleResponse;
import com.bandtec.br.finoban.repository.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-finoban/acessos")
@AllArgsConstructor
public class AcessoController {

    private GestaoAcessosRepository gestaoUsuariosRepository;

    @GetMapping("/{id}")
    public ResponseEntity getAcesso(@PathVariable int id) {
        return ResponseEntity.status(200).body(new SingleResponse(gestaoUsuariosRepository.resgatarAcessoPeloId(id)));
    }

    @GetMapping
    public ResponseEntity getAcessos() {
        List<Acesso> acessoList = gestaoUsuariosRepository.resgatarTodosAcessos();
        if (acessoList.isEmpty())
            return ResponseEntity.status(204).build();

        return ResponseEntity.status(200).body(new SingleResponse(acessoList));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registro concluido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Falha ao concluir requisição")
    })
    @PostMapping
    public ResponseEntity postAcesso(@RequestBody Acesso acesso) {
       gestaoUsuariosRepository.postAcesso(acesso);
       return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAcesso(@PathVariable int id) {
        gestaoUsuariosRepository.deletarAcessoPeloId(id);
        return ResponseEntity.status(204).build();
    }

    // USAR HASHING PARA DELETAR ULTIMOS ACESSOS
}
