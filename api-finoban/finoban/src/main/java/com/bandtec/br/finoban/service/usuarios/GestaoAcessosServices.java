package com.bandtec.br.finoban.service.usuarios;

import com.bandtec.br.finoban.dominio.entidades.Acesso;
import com.bandtec.br.finoban.dominio.exceptions.AcessoNaoEncontradoException;
import com.bandtec.br.finoban.dominio.exceptions.ClienteNaoEncontradoException;
import com.bandtec.br.finoban.dominio.exceptions.RegiaoNaoEncontradaException;
import com.bandtec.br.finoban.repository.database.AcessoRepository;
import com.bandtec.br.finoban.repository.database.UsuarioRepository;
import com.bandtec.br.finoban.repository.GestaoAcessosRepository;
import com.bandtec.br.finoban.repository.database.RegiaoRepository;
import lombok.AllArgsConstructor;
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
    public Acesso resgatarAcessoPeloId(int id) {
        Optional<Acesso> acesso = acessoRepository.findById(id);

        if (!acesso.isPresent())
            throw new AcessoNaoEncontradoException();

        return acesso.get();
    }

    @Override
    public List<Acesso> resgatarTodosAcessos() {
        return acessoRepository.findAllByIdEntrada();
    }

    @Override
    public void deletarAcessoPeloId(int id) {
        if (!acessoRepository.existsById(id))
            throw new AcessoNaoEncontradoException();

        acessoRepository.deleteById(id);
    }

    @Override
    public void postAcesso(Acesso acesso) {
        if (!cadastroRepository.existsById(acesso.getFkCliente().getId()))
            throw new ClienteNaoEncontradoException();

        if (!regiaoRepository.existsById(acesso.getFkRegiao().getIdRegiao()))
            throw new RegiaoNaoEncontradaException();

        acessoRepository.save(acesso);
    }
}
