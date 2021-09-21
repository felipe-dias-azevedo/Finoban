package com.bandtec.br.finoban.service.usuarios;

import com.bandtec.br.finoban.entidades.Usuario;
import com.bandtec.br.finoban.exceptions.ClienteNaoEncontradoException;
import com.bandtec.br.finoban.repository.UsuarioRepository;
import com.bandtec.br.finoban.repository.GestaoUsuariosRepository;
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

    @Override
    public ResponseEntity cadastrarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
        return ResponseEntity.status(201).body("Cadastro efetuado com sucesso");
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
}
