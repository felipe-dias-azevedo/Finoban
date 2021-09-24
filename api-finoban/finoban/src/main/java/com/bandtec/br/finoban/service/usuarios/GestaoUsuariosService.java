package com.bandtec.br.finoban.service.usuarios;

import com.bandtec.br.finoban.criptografia.Criptografia;
import com.bandtec.br.finoban.entidades.RedefinicaoSenha;
import com.bandtec.br.finoban.entidades.Usuario;
import com.bandtec.br.finoban.exceptions.*;
import com.bandtec.br.finoban.models.Login;
import com.bandtec.br.finoban.helpers.RecursoesHelper;
import com.bandtec.br.finoban.models.RedefinirSenhaModel;
import com.bandtec.br.finoban.repository.RedefinicaoSenhaRepository;
import com.bandtec.br.finoban.repository.UsuarioRepository;
import com.bandtec.br.finoban.repository.GestaoUsuariosRepository;
import com.bandtec.br.finoban.resposta.ResponseGeneric;
import com.bandtec.br.finoban.service.SendEmailService;
import com.bandtec.br.finoban.service.TokenService;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GestaoUsuariosService implements GestaoUsuariosRepository {

    private final UsuarioRepository usuarioRepository;
    private final RedefinicaoSenhaRepository redefinicaoSenhaRepository;
    private SendEmailService sendEmailService;
    private List<String> usuariosLogados;
    private static final String URL_REDEFINIR_SENHA = "http://localhost:3000/usuarios/redefinir-senha/";

    @Override
    public ResponseEntity cadastrarUsuario(Usuario usuario) {

        if (usuarioRepository.existsUsuarioByEmail(usuario.getEmail()))
            return new ResponseEntity(new UsuarioJaCadastradoException(), HttpStatus.NOT_FOUND);

        usuarioRepository.save(usuario);
        atualizarDadosCadastrais(usuario);
        return ResponseEntity.status(201).body(new ResponseGeneric(usuario));
    }

    @Override
    public ResponseEntity listarUsuarios() {
        List<Usuario> usuarioList = (List<Usuario>) usuarioRepository.findAll();
        if (usuarioList.isEmpty())
            return ResponseEntity.status(204).build();

        return ResponseEntity.status(200).body(usuarioList);
    }

    @Override
    public ResponseEntity resgatarUsuarioPeloId(int id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if (!usuario.isPresent())
            return new ResponseEntity(new ClienteNaoEncontradoException(), HttpStatus.NOT_FOUND);

        return ResponseEntity.status(200).body(usuario);
    }

    @Override
    public ResponseEntity deletarUsuarioPeloId(int id) {
        if (!usuarioRepository.existsById(id))
            return new ResponseEntity(new ClienteNaoEncontradoException(), HttpStatus.NOT_FOUND);

        usuarioRepository.deleteById(id);
        return ResponseEntity.status(204).build();
    }

    @Override
    public ResponseEntity atualizarDadosCadastrais(Usuario usuario) {
        Usuario usuarioVerificar = usuarioRepository.findByEmailContaining(usuario.getEmail());
        if (usuarioVerificar == null)
            return new ResponseEntity(new EmailNaoEncontradoException(), HttpStatus.NOT_FOUND);

        Criptografia.criptografarComHashingMaisSaltMaisId(usuarioVerificar.getSenha(), usuarioVerificar.getId());
        String senhaCriptografada = Criptografia.criptografarComHashingMaisSaltMaisId(usuarioVerificar.getSenha(), usuarioVerificar.getId());
        usuarioVerificar.setSenha(senhaCriptografada);
        usuarioRepository.redefinirSenhaUsuario(senhaCriptografada, usuario.getId());
        return ResponseEntity.status(201).body(new ResponseGeneric(usuarioVerificar));
    }

    @Override
    public ResponseEntity atualizarDadosCadastrais(Usuario usuario, RedefinirSenhaModel redefinirSenhaModel) {
        Usuario usuarioVerificar = usuarioRepository.findByEmailContaining(usuario.getEmail());
        if (usuarioVerificar == null)
            return new ResponseEntity(new EmailNaoEncontradoException(), HttpStatus.NOT_FOUND);

        RedefinicaoSenha redefinicaoSenha = redefinicaoSenhaRepository.findAllByTokenJWT(redefinirSenhaModel.getTokenJwt());
        if (redefinicaoSenha.isExpirado())
            return new ResponseEntity(new TokenExpiradoException(), HttpStatus.NOT_FOUND);

        Criptografia.criptografarComHashingMaisSaltMaisId(usuarioVerificar.getSenha(), usuarioVerificar.getId());
        String senhaCriptografada = Criptografia.criptografarComHashingMaisSaltMaisId(usuarioVerificar.getSenha(), usuarioVerificar.getId());
        usuarioVerificar.setSenha(senhaCriptografada);
        usuarioRepository.redefinirSenhaUsuario(senhaCriptografada, usuario.getId());
        redefinicaoSenhaRepository.updateCampoExpirado(redefinicaoSenha.getId());
        return ResponseEntity.status(201).body(new ResponseGeneric(usuarioVerificar));
    }

    @Override
    public ResponseEntity efetuarLogin(Login login) {
        Usuario verificaEmail = usuarioRepository.findByEmailContaining(login.getEmail());
        if (verificaEmail == null)
            return new ResponseEntity(new EmailNaoEncontradoException(), HttpStatus.NOT_FOUND);

        String senhaCriptografada = Criptografia.criptografarComHashingMaisSaltMaisId(login.getSenha(),
                verificaEmail.getId());

        if (!verificaEmail.getSenha().equals(senhaCriptografada))
            return new ResponseEntity(new SenhaIncorretaException(), HttpStatus.NOT_FOUND);

        String emailLogado = login.getEmail();
        return RecursoesHelper.verificarUsuariosLogados(usuariosLogados, emailLogado, verificaEmail, usuariosLogados.size());
    }

    @Override
    public ResponseEntity efetuarLogoff(Login login) {
        return RecursoesHelper.efetuarLogoffUsuarioLogado(usuariosLogados, login, usuariosLogados.size());
    }

    @Override
    public ResponseEntity iniciarRedefinicaoSenha(Usuario usuario) throws MessagingException, IOException {
        TokenService tokenService = new TokenService();
        String jwt = tokenService.createJWT(usuario);
        redefinicaoSenhaRepository.save(new RedefinicaoSenha(usuario, jwt));
        sendEmailService.sendEmail(usuario, URL_REDEFINIR_SENHA + jwt);
        return ResponseEntity.status(201).build();
    }

    @Override
    public ResponseEntity verificarRedeficicaoSenha(String jwt) {
        TokenService tokenService = new TokenService();
        if (tokenService.jwtExpirado(jwt))
            return new ResponseEntity(new TokenExpiradoException(), HttpStatus.NOT_FOUND);

        RedefinicaoSenha redefinicaoSenha = redefinicaoSenhaRepository.findAllByTokenJWT(jwt);
        if (redefinicaoSenha == null)
            return new ResponseEntity(new TokenInvalidoException(), HttpStatus.NOT_FOUND);

        if (redefinicaoSenha.isExpirado())
            return new ResponseEntity(new TokenExpiradoException(), HttpStatus.NOT_FOUND);

        return ResponseEntity.status(200).body(new ResponseGeneric(tokenService.converterToModel(jwt)));
    }
}
