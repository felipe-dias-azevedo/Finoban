package com.bandtec.br.finoban.service.core;

import com.bandtec.br.finoban.entidades.Regiao;
import com.bandtec.br.finoban.exceptions.RegiaoNaoEncontradaException;
import com.bandtec.br.finoban.repository.GestaoRegioesRepository;
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
public class GestaoRegioesService implements GestaoRegioesRepository {

    private final RegiaoRepository regiaoRepository;

    @Override
    public ResponseEntity listarRegioes() {
        List<Regiao> regiaoList = regiaoRepository.findAllRegiaoLatest();
        if (regiaoList.isEmpty())
            return ResponseEntity.status(204).build();

        return ResponseEntity.status(200).body(regiaoRepository.findAllRegiaoLatest());
    }

    @Override
    public ResponseEntity resgatarRegiaoPeloId(int id) {
        Optional<Regiao> regiao = regiaoRepository.findById(id);
        if (!regiao.isPresent())
            return new ResponseEntity(new RegiaoNaoEncontradaException(), HttpStatus.NOT_FOUND);

        return ResponseEntity.status(200).body(regiao);
    }

    @Override
    public ResponseEntity registrarRegiao(Regiao regiao) {
        regiaoRepository.save(regiao);
        return ResponseEntity.status(201).build();
    }

    @Override
    public ResponseEntity deletarRegiaoPeloId(int id) {
        if (!regiaoRepository.existsById(id))
            return new ResponseEntity(new RegiaoNaoEncontradaException(), HttpStatus.NOT_FOUND);

        regiaoRepository.deleteById(id);
        return ResponseEntity.status(204).build();
    }

    @Override
    public ResponseEntity atualizarRegiao(Regiao regiao) {
        if (!regiaoRepository.existsById(regiao.getIdRegiao())) {
            return new ResponseEntity(new RegiaoNaoEncontradaException(), HttpStatus.NOT_FOUND);
        }
        regiaoRepository.setRegiaoById(regiao.getDescricaoRegiao(), regiao.getValorRegiao(), regiao.getDataCraw(), regiao.getIdRegiao());
        return ResponseEntity.status(200).body(new ResponseGeneric(regiaoRepository.findById(regiao.getIdRegiao())));
    }
}
