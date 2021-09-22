package com.bandtec.br.finoban.service.usuarios;

import com.bandtec.br.finoban.criptografia.Criptografia;
import com.bandtec.br.finoban.entidades.Usuario;
import com.bandtec.br.finoban.exceptions.ClienteNaoEncontradoException;
import com.bandtec.br.finoban.exceptions.EmailNaoEncontradoException;
import com.bandtec.br.finoban.exceptions.SenhaIncorretaException;
import com.bandtec.br.finoban.models.Login;
import com.bandtec.br.finoban.recursoes.RecursoesHelper;
import com.bandtec.br.finoban.repository.UsuarioRepository;
import com.bandtec.br.finoban.repository.GestaoUsuariosRepository;
import com.bandtec.br.finoban.resposta.ResponseGeneric;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GestaoUsuariosService implements GestaoUsuariosRepository {

    private final UsuarioRepository usuarioRepository;
    private List<String> usuariosLogados;

    @Override
    public ResponseEntity cadastrarUsuario(Usuario usuario) {
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

        deletarUsuarioPeloId(usuarioVerificar.getId());
        usuarioVerificar.setSenha(Criptografia.criptografarComHashingMaisSaltMaisId(usuarioVerificar.getSenha(), usuarioVerificar.getId()+1));
        usuarioRepository.save(usuarioVerificar);
        return ResponseEntity.status(200).body(new ResponseGeneric(usuarioVerificar));
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
}
