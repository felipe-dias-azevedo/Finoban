package com.bandtec.br.finoban.service.usuarios;

import com.bandtec.br.finoban.entidades.Avaliacao;
import com.bandtec.br.finoban.enums.AvalPositivoEnum;
import com.bandtec.br.finoban.exceptions.AcessoNaoEncontradoException;
import com.bandtec.br.finoban.exceptions.AvalPositivoIncorretoException;
import com.bandtec.br.finoban.exceptions.AvaliacaoNaoEncontradaException;
import com.bandtec.br.finoban.exceptions.FeedbackNullException;
import com.bandtec.br.finoban.repository.AcessoRepository;
import com.bandtec.br.finoban.repository.AvaliacaoRepository;
import com.bandtec.br.finoban.repository.GestaoAvaliacoesRepository;
import com.bandtec.br.finoban.resposta.ResponseGeneric;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GestaoAvaliacoesService implements GestaoAvaliacoesRepository {

    private final AvaliacaoRepository avaliacaoRepository;
    private final AcessoRepository acessoRepository;

    @Override
    public ResponseEntity listarAvaliacoes() {
        List<Avaliacao> avaliacaoList = avaliacaoRepository.findAllByIdAvaliacao();
        if (avaliacaoList.isEmpty())
            return ResponseEntity.status(204).build();

        return ResponseEntity.status(200).body(new ResponseGeneric(avaliacaoList));
    }

    @Override
    public ResponseEntity resgatarAvaliacaoPeloId(int id) {
        Optional<Avaliacao> avaliacao = avaliacaoRepository.findById(id);

        if (!avaliacao.isPresent())
            return new ResponseEntity(new AvaliacaoNaoEncontradaException(), HttpStatus.NOT_FOUND);

        return ResponseEntity.status(200).body(new ResponseGeneric(avaliacao));
    }

    @Override
    public ResponseEntity deletarAvaliacaoPeloId(int id) {

        if (!avaliacaoRepository.existsById(id))
            return new ResponseEntity(new AvaliacaoNaoEncontradaException(), HttpStatus.NOT_FOUND);

        avaliacaoRepository.deleteById(id);
        return ResponseEntity.status(204).build();
    }

    @Override
    public ResponseEntity cadastrarAvaliacao(Avaliacao avaliacao) {

        if(avaliacao.getAvalPositivo() != null && avaliacao.getFeedbackAval() == null)
            return new ResponseEntity(new FeedbackNullException(), HttpStatus.BAD_REQUEST);

        if (!avaliacaoRepository.existsById(avaliacao.getFkAcesso().getIdEntrada()))
            return new ResponseEntity(new AcessoNaoEncontradoException(), HttpStatus.NOT_FOUND);

        try {
            if (!AvalPositivoEnum.validarEnum(avaliacao.getAvalPositivo().getIndice())) {
                return new ResponseEntity(new AvalPositivoIncorretoException(), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            return new ResponseEntity(new AvalPositivoIncorretoException(), HttpStatus.BAD_REQUEST);
        }


        avaliacaoRepository.save(avaliacao);
        return ResponseEntity.status(201).build();
    }
}
