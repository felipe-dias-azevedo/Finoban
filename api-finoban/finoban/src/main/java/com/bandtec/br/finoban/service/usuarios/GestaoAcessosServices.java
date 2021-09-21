package com.bandtec.br.finoban.service.usuarios;

import com.bandtec.br.finoban.entidades.Acesso;
import com.bandtec.br.finoban.exceptions.AcessoNaoEncontradoException;
import com.bandtec.br.finoban.exceptions.ClienteNaoEncontradoException;
import com.bandtec.br.finoban.repository.AcessoRepository;
import com.bandtec.br.finoban.repository.UsuarioRepository;
import com.bandtec.br.finoban.repository.GestaoAcessosRepository;
import com.bandtec.br.finoban.repository.RegiaoRepository;
import com.bandtec.br.finoban.resposta.ResponseGeneric;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GestaoAcessosServices implements GestaoAcessosRepository {

    private final AcessoRepository acessoRepository;
    private final UsuarioRepository cadastroRepository;
    private final RegiaoRepository regiaoRepository;

    @Override
    public ResponseEntity resgatarAcessoPeloId(int id) {
        Optional<Acesso> acesso = acessoRepository.findById(id);

        if (!acesso.isPresent())
            return new ResponseEntity(new AcessoNaoEncontradoException(), HttpStatus.NOT_FOUND);

        return ResponseEntity.status(200).body(new ResponseGeneric(acesso));
    }

    @Override
    public ResponseEntity resgatarTodosAcessos() {
        List<Acesso> acessoList = acessoRepository.findAllByIdEntrada();

        if (acessoList.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(new ResponseGeneric(acessoList));
        }
    }

    @Override
    public ResponseEntity deletarAcessoPeloId(int id) {
        if (!acessoRepository.existsById(id))
            return new ResponseEntity(new AcessoNaoEncontradoException(), HttpStatus.NOT_FOUND);

        acessoRepository.deleteById(id);
        return ResponseEntity.status(204).build();
    }

    @Override
    public ResponseEntity postAcesso(Acesso acesso) {

        if (!cadastroRepository.existsById(acesso.getFkCliente().getId()))
            return new ResponseEntity(new ClienteNaoEncontradoException(), HttpStatus.NOT_FOUND);

        if (!regiaoRepository.existsById(acesso.getFkRegiao().getIdRegiao()))
            return new ResponseEntity(new AcessoNaoEncontradoException(), HttpStatus.NOT_FOUND);

        acessoRepository.save(acesso);
        return ResponseEntity.status(201).build();

    }
}
