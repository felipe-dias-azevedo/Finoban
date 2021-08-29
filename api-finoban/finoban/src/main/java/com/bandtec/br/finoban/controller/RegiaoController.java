package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.entidades.Regiao;
import com.bandtec.br.finoban.entidades.Usuario;
import com.bandtec.br.finoban.repository.RegiaoRepository;
import com.bandtec.br.finoban.resposta.ResponseGeneric;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Regiões resgatadas com sucesso",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Regiao.class)))
                    ),
                    @ApiResponse(
                            responseCode = "204",
                            description = "Não encontramos nenhuma região"
                    )
            }
    )
    public ResponseEntity getRegioes() {
        List<Regiao> regiaoList = repository.findAllRegiaoLatest();
        if (regiaoList.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(repository.findAllRegiaoLatest());
        }
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Requisição feita com sucesso",
                    content = @Content(schema = @Schema(implementation = Regiao.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Região não encontrada para resgatar"
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity getRegiao(@PathVariable Integer id) {
        Optional<Regiao> regiao = repository.findById(id);
        if (regiao.isPresent()) {
            return ResponseEntity.status(200).body(regiao);
        } else {
            return ResponseEntity.status(404).build();
        }
    }


    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Região registrada com sucesso"
            )
    })
    @PostMapping
    public ResponseEntity postRegiao(@RequestBody Regiao regiao) {
//        System.out.println(token);
//        if (!token.equals("Zmlub2JhbmVhbWVsaG9yZG9tdW5kbw==")) {
//            return ResponseEntity.status(400).build();
//        }
        repository.save(regiao);
        return ResponseEntity.status(201).build();
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Região deletada com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Região não encontrada para excluir"
            )
    })
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

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Região atualizada com sucesso",
                    content = @Content(schema = @Schema(implementation = ResponseGeneric.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Região não encontrada para atualizar"
            )
    })
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
