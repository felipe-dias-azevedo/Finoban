package com.bandtec.br.finoban.service.usuarios;

import com.bandtec.br.finoban.dominio.RedefinicaoSenhaModel;
import com.bandtec.br.finoban.dominio.TokenDecodificadoModel;
import com.bandtec.br.finoban.infraestrutura.constantes.Constantes;
import com.bandtec.br.finoban.infraestrutura.criptografia.Criptografia;
import com.bandtec.br.finoban.dominio.entidades.RedefinicaoSenha;
import com.bandtec.br.finoban.dominio.entidades.Usuario;
import com.bandtec.br.finoban.dominio.exceptions.*;
import com.bandtec.br.finoban.dominio.Login;
import com.bandtec.br.finoban.infraestrutura.helpers.RecursoesHelper;
import com.bandtec.br.finoban.dominio.RedefinirSenhaModel;
import com.bandtec.br.finoban.repository.RedefinicaoSenhaRepository;
import com.bandtec.br.finoban.repository.UsuarioRepository;
import com.bandtec.br.finoban.repository.GestaoUsuariosRepository;
import com.bandtec.br.finoban.dominio.resposta.ResponseGeneric;
import com.bandtec.br.finoban.service.SendEmailService;
import com.bandtec.br.finoban.service.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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


    @Override
    public void cadastrarUsuario(Usuario usuario) {

        if (usuarioRepository.existsUsuarioByEmail(usuario.getEmail()))
            throw new UsuarioJaCadastradoException();

        usuarioRepository.save(usuario);
        atualizarDadosCadastrais(usuario);
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return (List<Usuario>) usuarioRepository.findAll();
    }

    @Override
    public Usuario resgatarUsuarioPeloId(int id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if (!usuario.isPresent())
            throw new ClienteNaoEncontradoException();

        return usuario.get();
    }

    @Override
    public void deletarUsuarioPeloId(int id) {
        if (!usuarioRepository.existsById(id))
            throw new ClienteNaoEncontradoException();

        usuarioRepository.deleteById(id);
    }

    @Override
    public Usuario atualizarDadosCadastrais(Usuario usuario) {
        Usuario usuarioVerificar = usuarioRepository.findByEmailContaining(usuario.getEmail());
        if (usuarioVerificar == null)
            throw new EmailNaoEncontradoException();

        String senhaCriptografada = Criptografia.criptografarComHashingMaisSaltMaisId(usuarioVerificar.getSenha(), usuarioVerificar.getId());
        usuarioVerificar.setSenha(senhaCriptografada);
        usuarioRepository.redefinirSenhaUsuario(senhaCriptografada, usuario.getId());
        return usuarioVerificar;
    }

    @Override
    public Usuario atualizarDadosCadastrais(Usuario usuario, RedefinirSenhaModel redefinirSenhaModel) {

        RedefinicaoSenha redefinicaoSenha = redefinicaoSenhaRepository.findAllByTokenJWT(redefinirSenhaModel.getTokenJwt());
        if (redefinicaoSenha.isExpirado())
            throw new TokenExpiradoException();

        Criptografia.criptografarComHashingMaisSaltMaisId(usuario.getSenha(), usuario.getId());
        String senhaCriptografada = Criptografia.criptografarComHashingMaisSaltMaisId(usuario.getSenha(), usuario.getId());
        usuario.setSenha(senhaCriptografada);
        usuarioRepository.redefinirSenhaUsuario(senhaCriptografada, usuario.getId());
        redefinicaoSenhaRepository.updateCampoExpirado(redefinicaoSenha.getId());
        return usuario;
    }

    @Override
    public Usuario efetuarLogin(Login login) {
        Usuario verificaEmail = usuarioRepository.findByEmailContaining(login.getEmail());
        if (verificaEmail == null)
            throw new EmailNaoEncontradoException();

        String senhaCriptografada = Criptografia.criptografarComHashingMaisSaltMaisId(login.getSenha(),
                verificaEmail.getId());

        if (!verificaEmail.getSenha().equals(senhaCriptografada))
            throw new SenhaIncorretaException();

        String emailLogado = login.getEmail();
        return RecursoesHelper.verificarUsuariosLogados(usuariosLogados, emailLogado, verificaEmail, usuariosLogados.size());
    }

    @Override
    public String efetuarLogoff(Login login) {
        return RecursoesHelper.efetuarLogoffUsuarioLogado(usuariosLogados, login, usuariosLogados.size());
    }

    @Override
    public void iniciarRedefinicaoSenha(RedefinicaoSenhaModel redefinicaoSenhaModel) {
        Usuario usuario = usuarioRepository.findByEmailContaining(redefinicaoSenhaModel.getEmail());
        if (usuario == null)
            throw new EmailNaoEncontradoException();

        TokenService tokenService = new TokenService();
        String jwt = tokenService.createJWT(usuario);
        redefinicaoSenhaRepository.save(new RedefinicaoSenha(usuario, jwt));
        sendEmailService.sendEmail(usuario, Constantes.URL_REDEFINIR_SENHA + jwt);
    }

    @Override
    public TokenDecodificadoModel verificarRedeficicaoSenha(String jwt) {
        TokenService tokenService = new TokenService();
        if (tokenService.jwtExpirado(jwt))
            throw new TokenExpiradoException();

        RedefinicaoSenha redefinicaoSenha = redefinicaoSenhaRepository.findAllByTokenJWT(jwt);
        if (redefinicaoSenha == null)
            throw new TokenInvalidoException();


        if (redefinicaoSenha.isExpirado())
            throw new TokenExpiradoException();

        return tokenService.converterToModel(jwt);
    }
}
