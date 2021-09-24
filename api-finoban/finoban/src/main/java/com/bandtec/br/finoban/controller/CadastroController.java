package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.entidades.Avaliacao;
import com.bandtec.br.finoban.entidades.RedefinicaoSenha;
import com.bandtec.br.finoban.exceptions.EmailNaoEncontradoException;
import com.bandtec.br.finoban.models.RedefinicaoSenhaModel;
import com.bandtec.br.finoban.entidades.Usuario;
import com.bandtec.br.finoban.models.RedefinirSenhaModel;
import com.bandtec.br.finoban.repository.UsuarioRepository;
import com.bandtec.br.finoban.service.usuarios.GestaoAvaliacoesService;
import com.bandtec.br.finoban.service.usuarios.GestaoUsuariosService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api-finoban")
@AllArgsConstructor
public class CadastroController {

    private GestaoUsuariosService gestaoUsuariosService;
    private GestaoAvaliacoesService gestaoAvaliacoesService;
    private UsuarioRepository usuarioRepository;

    @PostMapping("/cadastro")
    public ResponseEntity novoCadastro(@Valid @RequestBody Usuario novoCadastro) {
        novoCadastro.setDataCriacao(LocalDateTime.now());
        return gestaoUsuariosService.cadastrarUsuario(novoCadastro);
    }

    @GetMapping("/usuarios")
    public ResponseEntity listarUsuarios() {
        return gestaoUsuariosService.listarUsuarios();
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity getUsuario(@PathVariable Integer id) {
        return gestaoUsuariosService.resgatarUsuarioPeloId(id);
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity deleteUsuario(@PathVariable int id) {
       return gestaoUsuariosService.deletarUsuarioPeloId(id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Avaliação cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Falha ao relacionar avaliação com Entrada")
    })
    @PostMapping("/avaliacao")
    public ResponseEntity novaAvaliacao(@Valid @RequestBody Avaliacao novaAvaliacao) {
        novaAvaliacao.setDataAval(LocalDateTime.now());
        return gestaoAvaliacoesService.cadastrarAvaliacao(novaAvaliacao);
    }

    @ApiResponses(value = {

    })
    @GetMapping("/avaliacoes")
    public ResponseEntity getAvaliacoes() {
        return gestaoAvaliacoesService.listarAvaliacoes();
    }

    @GetMapping("/avaliacoes/{id}")
    public ResponseEntity getAvaliacao(@PathVariable int id) {
        return gestaoAvaliacoesService.resgatarAvaliacaoPeloId(id);
    }

    @DeleteMapping("/avaliacoes/{id}")
    public ResponseEntity deleteAvaliacao(@PathVariable int id) {
        return gestaoAvaliacoesService.deletarAvaliacaoPeloId(id);
    }

    @PostMapping("/usuarios/iniciar-redefinicao-senha")
    public ResponseEntity iniciarRedefinicaoSenha(@RequestBody RedefinicaoSenhaModel redefinicaoSenhaModel) throws MessagingException, IOException {
        Usuario usuario = usuarioRepository.findByEmailContaining(redefinicaoSenhaModel.getEmail());
        if (usuario == null)
            return new ResponseEntity(new EmailNaoEncontradoException(), HttpStatus.NOT_FOUND);

        return gestaoUsuariosService.iniciarRedefinicaoSenha(usuario);
    }

    @GetMapping("/usuarios/redefinir-senha/verificar/{jwt}")
    public ResponseEntity verificarRedefinicaoSenha(@PathVariable String jwt) {
        return gestaoUsuariosService.verificarRedeficicaoSenha(jwt);
    }

    @PostMapping("/usuarios/redefinir-senha")
    public ResponseEntity redefinirSenha(@RequestBody RedefinirSenhaModel redefinirSenhaModel) {
        Usuario usuario = usuarioRepository.findByEmailContaining(redefinirSenhaModel.getEmail());
        if (usuario == null)
            return new ResponseEntity(new EmailNaoEncontradoException(), HttpStatus.NOT_FOUND);

        usuario.setSenha(redefinirSenhaModel.getNovaSenha());
        return gestaoUsuariosService.atualizarDadosCadastrais(usuario, redefinirSenhaModel);
    }

}
