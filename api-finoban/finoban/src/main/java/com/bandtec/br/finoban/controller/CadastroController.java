package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.dominio.entidades.Avaliacao;
import com.bandtec.br.finoban.dominio.exceptions.EmailNaoEncontradoException;
import com.bandtec.br.finoban.dominio.RedefinicaoSenhaModel;
import com.bandtec.br.finoban.dominio.entidades.Usuario;
import com.bandtec.br.finoban.dominio.RedefinirSenhaModel;
import com.bandtec.br.finoban.dominio.resposta.SingleResponse;
import com.bandtec.br.finoban.dominio.resposta.UsuarioRespostaSimples;
import com.bandtec.br.finoban.repository.database.UsuarioRepository;
import com.bandtec.br.finoban.service.usuarios.GestaoAvaliacoesService;
import com.bandtec.br.finoban.service.usuarios.GestaoUsuariosService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api-finoban")
@AllArgsConstructor
public class CadastroController {

    private GestaoUsuariosService gestaoUsuariosService;

    private GestaoAvaliacoesService gestaoAvaliacoesService;

    private UsuarioRepository usuarioRepository;

    private static final Logger log = LogManager.getLogger(CadastroController.class.getName());

    @PostMapping("/cadastro")
    public ResponseEntity novoCadastro(@Valid @RequestBody Usuario novoCadastro) {
        novoCadastro.setDataCriacao(LocalDateTime.now());
        gestaoUsuariosService.cadastrarUsuario(novoCadastro);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/usuarios")
    public ResponseEntity listarUsuarios() {
        log.info("GET api-finoban/usuarios");
        List<Usuario> usuarioList = gestaoUsuariosService.listarUsuarios();
        if (usuarioList.isEmpty())
            return ResponseEntity.status(204).build();

        return ResponseEntity.status(200)
                .body(new SingleResponse<>(UsuarioRespostaSimples.converterListaUsuarioParaUsuarioSimples(usuarioList)));
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity getUsuario(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(new SingleResponse<>(new UsuarioRespostaSimples(gestaoUsuariosService.resgatarUsuarioPeloId(id))));
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity deleteUsuario(@PathVariable int id) {
       gestaoUsuariosService.deletarUsuarioPeloId(id);
       return ResponseEntity.status(204).build();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Avaliação cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Falha ao relacionar avaliação com Entrada")
    })
    @PostMapping("/avaliacao")
    public ResponseEntity novaAvaliacao(@Valid @RequestBody Avaliacao novaAvaliacao) {
        novaAvaliacao.setDataAval(LocalDateTime.now());
        gestaoAvaliacoesService.cadastrarAvaliacao(novaAvaliacao);
        return ResponseEntity.status(201).build();
    }

    @ApiResponses(value = {

    })
    @GetMapping("/avaliacoes")
    public ResponseEntity getAvaliacoes() {
        List<Avaliacao> avaliacoes = gestaoAvaliacoesService.listarAvaliacoes();
        if (avaliacoes.isEmpty())
            return ResponseEntity.status(204).build();

        return ResponseEntity.status(200).body(new SingleResponse(avaliacoes));
    }

    @GetMapping("/avaliacoes/{id}")
    public ResponseEntity getAvaliacao(@PathVariable int id) {
        return ResponseEntity.status(200).body(new SingleResponse(gestaoAvaliacoesService.resgatarAvaliacaoPeloId(id)));
    }

    @DeleteMapping("/avaliacoes/{id}")
    public ResponseEntity deleteAvaliacao(@PathVariable int id) {
        gestaoAvaliacoesService.deletarAvaliacaoPeloId(id);
        return ResponseEntity.status(204).build();
    }

    @PostMapping("/usuarios/iniciar-redefinicao-senha")
    public ResponseEntity iniciarRedefinicaoSenha(@RequestBody RedefinicaoSenhaModel redefinicaoSenhaModel) throws MessagingException, IOException {
        gestaoUsuariosService.iniciarRedefinicaoSenha(redefinicaoSenhaModel);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/usuarios/redefinir-senha/verificar/{jwt}")
    public ResponseEntity verificarRedefinicaoSenha(@PathVariable String jwt) {
        return ResponseEntity.status(200).body(new SingleResponse<>(gestaoUsuariosService.verificarRedeficicaoSenha(jwt)));
    }

    @PostMapping("/usuarios/redefinir-senha")
    public ResponseEntity redefinirSenha(@RequestBody RedefinirSenhaModel redefinirSenhaModel) {
        Usuario usuario = usuarioRepository.findByEmailContaining(redefinirSenhaModel.getEmail());
        if (usuario == null)
            throw new EmailNaoEncontradoException();

        usuario.setSenha(redefinirSenhaModel.getNovaSenha());
        return ResponseEntity.status(200).body(new SingleResponse<>(new UsuarioRespostaSimples(gestaoUsuariosService.atualizarDadosCadastrais(usuario, redefinirSenhaModel))));
    }

}
