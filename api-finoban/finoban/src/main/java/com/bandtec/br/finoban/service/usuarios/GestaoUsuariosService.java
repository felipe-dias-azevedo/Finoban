package com.bandtec.br.finoban.service.usuarios;

import com.bandtec.br.finoban.controller.CadastroController;
import com.bandtec.br.finoban.dominio.RedefinicaoSenhaModel;
import com.bandtec.br.finoban.dominio.TokenDecodificadoModel;
import com.bandtec.br.finoban.dominio.resposta.respostasLogin.RespostaLoginUsuario;
import com.bandtec.br.finoban.infraestrutura.constantes.Constantes;
import com.bandtec.br.finoban.infraestrutura.criptografia.Criptografia;
import com.bandtec.br.finoban.dominio.entidades.RedefinicaoSenha;
import com.bandtec.br.finoban.dominio.entidades.Usuario;
import com.bandtec.br.finoban.dominio.exceptions.*;
import com.bandtec.br.finoban.dominio.Login;
import com.bandtec.br.finoban.dominio.RedefinirSenhaModel;
import com.bandtec.br.finoban.repository.database.RedefinicaoSenhaRepository;
import com.bandtec.br.finoban.repository.database.UsuarioRepository;
import com.bandtec.br.finoban.repository.GestaoUsuariosRepository;
import com.bandtec.br.finoban.service.AuthService;
import com.bandtec.br.finoban.service.SendEmailService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GestaoUsuariosService implements GestaoUsuariosRepository {

    @Autowired
    private AuthService authService;
    private final UsuarioRepository usuarioRepository;
    private final RedefinicaoSenhaRepository redefinicaoSenhaRepository;

    private final SendEmailService sendEmailService;
    private final LoginUsuarioService loginUsuarioService;
    private static final Logger log = LogManager.getLogger(CadastroController.class.getName());

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

        log.info(usuario.get());

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
    public RespostaLoginUsuario efetuarLogin(Login login) {
        Usuario usuario = usuarioRepository.findByEmailContaining(login.getEmail());
        if (usuario == null)
            throw new EmailNaoEncontradoException();

        String senhaCriptografada = Criptografia.criptografarComHashingMaisSaltMaisId(login.getSenha(),
                usuario.getId());

        if (!usuario.getSenha().equals(senhaCriptografada))
            throw new SenhaIncorretaException();

        return loginUsuarioService.logarUsuario(usuario);
    }

    @Override
    public void efetuarLogoff(Login login) {
        Usuario usuario = usuarioRepository.findByEmailContaining(login.getEmail());
        if (usuario == null)
            throw new EmailNaoEncontradoException();

        loginUsuarioService.realizarLogoffUsuario(usuario);
    }

    @Override
    public void iniciarRedefinicaoSenha(RedefinicaoSenhaModel redefinicaoSenhaModel) {
        Usuario usuario = usuarioRepository.findByEmailContaining(redefinicaoSenhaModel.getEmail());
        if (usuario == null)
            throw new EmailNaoEncontradoException();

        String jwt = authService.createJWT(usuario);
        redefinicaoSenhaRepository.save(new RedefinicaoSenha(usuario, jwt));
        sendEmailService.sendEmail(usuario, Constantes.URL_REDEFINIR_SENHA + jwt);
    }

    @Override
    public TokenDecodificadoModel verificarRedeficicaoSenha(String jwt) {
        if (authService.jwtExpirado(jwt))
            throw new TokenExpiradoException();

        RedefinicaoSenha redefinicaoSenha = redefinicaoSenhaRepository.findAllByTokenJWT(jwt);
        if (redefinicaoSenha == null)
            throw new TokenInvalidoException();


        if (redefinicaoSenha.isExpirado())
            throw new TokenExpiradoException();

        return authService.converterToModel(jwt);
    }
}
