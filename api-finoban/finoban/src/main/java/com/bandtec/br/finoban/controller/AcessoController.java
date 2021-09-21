package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.entidades.Acesso;
import com.bandtec.br.finoban.repository.*;
import com.bandtec.br.finoban.resposta.ResponseGeneric;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api-finoban/acessos")
@AllArgsConstructor
public class AcessoController {

    private GestaoAcessosRepository gestaoUsuariosRepository;

    @GetMapping("/{id}")
    public ResponseEntity getAcesso(@PathVariable int id) {
        return gestaoUsuariosRepository.resgatarAcessoPeloId(id);
    }

    @GetMapping
    public ResponseEntity getAcessos() {
        return gestaoUsuariosRepository.resgatarTodosAcessos();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registro concluido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Falha ao concluir requisição")
    })
    @PostMapping
    public ResponseEntity postAcesso(@RequestBody Acesso acesso) {
       return gestaoUsuariosRepository.postAcesso(acesso);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAcesso(@PathVariable int id) {
        return gestaoUsuariosRepository.deletarAcessoPeloId(id);
    }

    // USAR HASHING PARA DELETAR ULTIMOS ACESSOS
}
