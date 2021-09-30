package com.bandtec.br.finoban.service.usuarios;

import com.bandtec.br.finoban.dominio.entidades.Avaliacao;
import com.bandtec.br.finoban.dominio.enums.AvalPositivoEnum;
import com.bandtec.br.finoban.dominio.exceptions.AcessoNaoEncontradoException;
import com.bandtec.br.finoban.dominio.exceptions.AvalPositivoIncorretoException;
import com.bandtec.br.finoban.dominio.exceptions.AvaliacaoNaoEncontradaException;
import com.bandtec.br.finoban.dominio.exceptions.FeedbackNullException;
import com.bandtec.br.finoban.repository.AcessoRepository;
import com.bandtec.br.finoban.repository.AvaliacaoRepository;
import com.bandtec.br.finoban.repository.GestaoAvaliacoesRepository;
import com.bandtec.br.finoban.dominio.resposta.ResponseGeneric;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GestaoAvaliacoesService implements GestaoAvaliacoesRepository {

    private final AvaliacaoRepository avaliacaoRepository;
    private final AcessoRepository acessoRepository;

    @Override
    public List<Avaliacao> listarAvaliacoes() {
        return avaliacaoRepository.findAllByIdAvaliacao();
    }

    @Override
    public Avaliacao resgatarAvaliacaoPeloId(int id) {
        Optional<Avaliacao> avaliacao = avaliacaoRepository.findById(id);

        if (!avaliacao.isPresent())
            throw new AvaliacaoNaoEncontradaException();

        return avaliacao.get();
    }

    @Override
    public void deletarAvaliacaoPeloId(int id) {

        if (!avaliacaoRepository.existsById(id))
            throw new AvaliacaoNaoEncontradaException();

        avaliacaoRepository.deleteById(id);
    }

    @Override
    public void cadastrarAvaliacao(Avaliacao avaliacao) {

        if(avaliacao.getAvalPositivo() != null && avaliacao.getFeedbackAval() == null)
            throw new FeedbackNullException();

        if (!avaliacaoRepository.existsById(avaliacao.getFkAcesso().getIdEntrada()))
            throw new AcessoNaoEncontradoException();

        try {
            if (!AvalPositivoEnum.validarEnum(avaliacao.getAvalPositivo().getIndice())) {
                throw new AvalPositivoIncorretoException();
            }
        } catch (Exception ex) {
            throw new AvalPositivoIncorretoException();
        }


        avaliacaoRepository.save(avaliacao);
    }
}
