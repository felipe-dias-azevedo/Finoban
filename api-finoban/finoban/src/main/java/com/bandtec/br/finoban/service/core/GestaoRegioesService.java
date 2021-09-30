package com.bandtec.br.finoban.service.core;

import com.bandtec.br.finoban.dominio.entidades.Regiao;
import com.bandtec.br.finoban.dominio.exceptions.RegiaoNaoEncontradaException;
import com.bandtec.br.finoban.repository.GestaoRegioesRepository;
import com.bandtec.br.finoban.repository.RegiaoRepository;
import com.bandtec.br.finoban.dominio.resposta.ResponseGeneric;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GestaoRegioesService implements GestaoRegioesRepository {

    private final RegiaoRepository regiaoRepository;

    @Override
    public List<Regiao> listarRegioes() {
        return regiaoRepository.findAllRegiaoLatest();
    }

    @Override
    public Regiao resgatarRegiaoPeloId(int id) {
        Optional<Regiao> regiao = regiaoRepository.findById(id);
        if (!regiao.isPresent())
            throw new RegiaoNaoEncontradaException();

        return regiao.get();
    }

    @Override
    public void registrarRegiao(Regiao regiao) {
        regiaoRepository.save(regiao);
    }

    @Override
    public void deletarRegiaoPeloId(int id) {
        if (!regiaoRepository.existsById(id))
            throw new RegiaoNaoEncontradaException();

        regiaoRepository.deleteById(id);
    }

    @Override
    public Regiao atualizarRegiao(Regiao regiao) {
        if (!regiaoRepository.existsById(regiao.getIdRegiao()))
            throw new RegiaoNaoEncontradaException();

        regiaoRepository.setRegiaoById(regiao.getDescricaoRegiao(), regiao.getValorRegiao(), regiao.getDataCraw(), regiao.getIdRegiao());
        return regiaoRepository.findById(regiao.getIdRegiao()).get();
    }
}
