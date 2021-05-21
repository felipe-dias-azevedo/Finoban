package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.enums.*;
import com.bandtec.br.finoban.repository.DashboardRepository;
import com.bandtec.br.finoban.views.DashboardResponse;
import com.bandtec.br.finoban.views.charts.*;
import com.bandtec.br.finoban.views.database.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/finoban/dashboard")
public class DashboardController {

    @Autowired
    private DashboardRepository repository;

    @GetMapping
    public ResponseEntity getDataDashboard() {

        RendimentoMensal rendimentoMensal = tratarRendimentoMensal(repository.getRendimentoMensalData());
        PorcentualPerdas porcentualPerdas = tratarPorcentualPerdas(repository.getPorcentualPerdaData());
        ProjecaoRendimento projecaoRendimento = tratarProjecaoRendimento(rendimentoMensal);
        TempoPermanencia tempoPermanencia = tratarTempoPermanencia(repository.getTempoPermanenciaData());
        AvaliacaoSite avaliacaoSite = tratarAvaliacaoSite(repository.getAvaliacaoSiteData());
        RegiaoRenda regiaoRenda = tratarRegiaoRenda(repository.getRegiaoRendaData());
        ValorImovelIdade valorImovelIdade = tratarValorImovelIdade(repository.getValorImovelIdadeData());
        RegioesEscolhidas regioesEscolhidas = tratarRegioesEscolhidas(repository.getRegioesEscolhidasData());
        ValorImovelRenda valorImovelRenda = tratarValorImovelRenda(repository.getValorImovelRendaData());
        CepRegiaoEscolhida cepRegiaoEscolhida = tratarCepRegiaoEscolhida(repository.getCepRegiaoData());
        BancosEscolhidos bancosEscolhidos = tratarBancosEscolhidos(repository.getBancosEscolhidosData());

        DashboardResponse dataDashboard = new DashboardResponse(
                rendimentoMensal, porcentualPerdas, projecaoRendimento, tempoPermanencia,
                avaliacaoSite, regiaoRenda, valorImovelIdade, regioesEscolhidas,
                valorImovelRenda, cepRegiaoEscolhida, bancosEscolhidos);

        return ResponseEntity.status(200).body(dataDashboard);
    }

    // GRAFICO 0
    public static RendimentoMensal tratarRendimentoMensal
            (List<RendimentoMensalDatabaseView> rendimentoData)
    {
        LocalDateTime dataInicio = LocalDateTime.now().minusDays(RendimentoMensal.TEMPO_LIMITE);

        RendimentoMensal objetoRendimentoMensal = new RendimentoMensal();
        Double mediaDiaria;
        Integer tamanho;

        for (int i = 0; i < RendimentoMensal.TEMPO_LIMITE; i++) {
            mediaDiaria = 0.0;
            tamanho = 1;
            for (RendimentoMensalDatabaseView r : rendimentoData) {
                if (r.getDataHoraSaida().getDayOfMonth() == dataInicio.plusDays(i).getDayOfMonth()) {
                    mediaDiaria += r.getRenda();
                    tamanho++;
                }
            }
            objetoRendimentoMensal.adicionarValor(dataInicio.plusDays(i).toLocalDate(), (mediaDiaria / tamanho));
        }
        return objetoRendimentoMensal;
    }

    // GRAFICO 1
    public static PorcentualPerdas tratarPorcentualPerdas
            (List<PorcentualPerdasDatabaseView> perdas)
    {
        Double confirmado = 0.0;
        Double naoConfirmado = 0.0;

        for (PorcentualPerdasDatabaseView p : perdas) {
            if (Objects.equals(StatusSaidaEnum.getConfirmouContratacao(p.getStatusSaida()),
                    StatusSaidaEnum.CONFIRMOU_CONTRATACAO))
            {
                confirmado = p.getContagem().doubleValue();
            }
            else if (Objects.equals(StatusSaidaEnum.getConfirmouContratacao(p.getStatusSaida()),
                    StatusSaidaEnum.NAO_CONFIRMOU))
            {
                naoConfirmado = p.getContagem().doubleValue();
            }
        }

        return new PorcentualPerdas(confirmado, naoConfirmado);
    }

    // GRAFICO 2
    public static ProjecaoRendimento tratarProjecaoRendimento
            (RendimentoMensal rendimentoMensal)
    {

        if (rendimentoMensal == null) {
            return null;
        }

        ProjecaoRendimento projecaoRendimento = new ProjecaoRendimento();

        List<Double> dias = new ArrayList<>();

        for (double i = 1.0; i <= ProjecaoRendimento.TEMPO_LIMITE; i++) {
            dias.add(i);
        }

        projecaoRendimento.gerarRegressaoLinear(dias, rendimentoMensal.getValores());

//        for (double i = ProjecaoRendimento.TEMPO_LIMITE+1; i <= ProjecaoRendimento.TEMPO_LIMITE*2; i++) {
        for (double i = 1; i <= ProjecaoRendimento.TEMPO_LIMITE; i++) {
            projecaoRendimento.adicionarValor(
                    LocalDate.now().plusDays((long) i),
                    projecaoRendimento.calculoRegressaoLinear(i));
        }
        return projecaoRendimento;
    }

    // GRAFICO 3
    public static TempoPermanencia tratarTempoPermanencia
            (List<TempoPermanenciaDatabaseView> tempoPermanencia)
    {
        TempoPermanencia permanencia = new TempoPermanencia();

        LocalTime timeMinimo = LocalTime.of(0, 0, 0);
        Integer segundosAcrescentar = 0;

        LocalTime timeAtual;
        Integer quantidade = 0;

        while (timeMinimo.getMinute() < TempoPermanencia.LIMITE_TEMPO.getMinute()) {
            for (TempoPermanenciaDatabaseView p : tempoPermanencia) {
                timeAtual = permanencia.diferencaTempo(p.getDataHoraEntrada(), p.getDataHoraSaida());

                if (timeAtual.getSecond() < (timeMinimo.getSecond() + segundosAcrescentar)
                        && timeAtual.getMinute() == timeMinimo.getMinute()) {
                    quantidade++;
                }
            }
            segundosAcrescentar += TempoPermanencia.INTERVALO_TEMPO;
            timeMinimo = timeMinimo.plusSeconds(TempoPermanencia.INTERVALO_TEMPO);
            permanencia.adicionarValor(timeMinimo, quantidade);
            quantidade = 0;
        }

        for (TempoPermanenciaDatabaseView p : tempoPermanencia) {
            timeAtual = permanencia.diferencaTempo(p.getDataHoraEntrada(), p.getDataHoraSaida());
            if (timeAtual.getMinute() >= timeMinimo.getMinute()) {
                quantidade++;
            }
        }
        timeMinimo = timeMinimo.plusSeconds(TempoPermanencia.INTERVALO_TEMPO);
        permanencia.adicionarValor(timeMinimo, quantidade);

        return permanencia;
    }

    // GRAFICO 4
    public static AvaliacaoSite tratarAvaliacaoSite
            (List<AvaliacaoSiteDatabaseView> avaliacao)
    {
        AvaliacaoSite avaliacaoSite = new AvaliacaoSite();

        for (AvaliacaoSiteDatabaseView a : avaliacao) {
            if (Objects.equals(AvalPositivoEnum.getAvaliacaoEnum(a.getAvalPositivo()),
                    AvalPositivoEnum.GOSTOU))
            {
                avaliacaoSite.setGostou(a.getContagem());
            }
            else if (Objects.equals(AvalPositivoEnum.getAvaliacaoEnum(a.getAvalPositivo()),
                    AvalPositivoEnum.NAO_GOSTOU))
            {
                avaliacaoSite.setNaoGostou(a.getContagem());
            }
            else if (Objects.equals(AvalPositivoEnum.getAvaliacaoEnum(a.getAvalPositivo()),
                    AvalPositivoEnum.NAO_DEU_FEEDBACK))
            {
                avaliacaoSite.setSemFeedback(a.getContagem());
            }
        }

        return avaliacaoSite;
    }

    // GRAFICO 5
    public static RegiaoRenda tratarRegiaoRenda
            (List<RegiaoRendaDatabaseView> regiaoRenda)
    {
        System.out.println("\nREGIOES / RENDA");
        for (RegiaoRendaDatabaseView r : regiaoRenda) {
            System.out.println(r.getDescricaoRegiao() + " " + r.getRenda());
        }
        return null;
    }

    // GRAFICO 6
    public static ValorImovelIdade tratarValorImovelIdade
            (List<ValorImovelIdadeDatabaseView> imovelIdade)
    {
        System.out.println("\nIDADE / VALOR IMOVEL");
        for (ValorImovelIdadeDatabaseView i : imovelIdade) {
            System.out.println(ValorImovelIdade.converterDataParaIdade(i.getDataNasc()) + "anos " + i.getRenda());
        }
        return null;
    }

    // GRAFICO 7
    public static RegioesEscolhidas tratarRegioesEscolhidas
            (List<RegioesEscolhidasDatabaseView> regioes)
    {
        RegioesEscolhidas regioesEscolhidas = new RegioesEscolhidas();

        for (RegioesEscolhidasDatabaseView r : regioes) {
            if (Objects.equals(RegioesEnum.getAvaliacaoEnum(r.getDescricaoRegiao()),
                    RegioesEnum.CENTRO))
            {
                regioesEscolhidas.setCentro(r.getContagem());
            }
            else if (Objects.equals(RegioesEnum.getAvaliacaoEnum(r.getDescricaoRegiao()),
                    RegioesEnum.CONSOLACAO))
            {
                regioesEscolhidas.setConsolacao(r.getContagem());
            }
            else if (Objects.equals(RegioesEnum.getAvaliacaoEnum(r.getDescricaoRegiao()),
                    RegioesEnum.BROOKLIN))
            {
                regioesEscolhidas.setBrooklin(r.getContagem());
            }
            else if (Objects.equals(RegioesEnum.getAvaliacaoEnum(r.getDescricaoRegiao()),
                    RegioesEnum.MOOCA))
            {
                regioesEscolhidas.setMooca(r.getContagem());
            }
            else if (Objects.equals(RegioesEnum.getAvaliacaoEnum(r.getDescricaoRegiao()),
                    RegioesEnum.SANTO_AMARO))
            {
                regioesEscolhidas.setSantoAmaro(r.getContagem());
            }
            else if (Objects.equals(RegioesEnum.getAvaliacaoEnum(r.getDescricaoRegiao()),
                    RegioesEnum.INTERLAGOS))
            {
                regioesEscolhidas.setInterlagos(r.getContagem());
            }
        }

        return regioesEscolhidas;
    }

    // GRAFICO 8
    public static ValorImovelRenda tratarValorImovelRenda
            (List<ValorImovelRendaDatabaseView> imovelRenda)
    {
        System.out.println("\nVALOR IMOVEL / RENDA");
        for (ValorImovelRendaDatabaseView i : imovelRenda) {
            System.out.println(i.getValorImovel() + " " + i.getRenda());
        }
        return null;
    }

    // GRAFICO 9
    public static CepRegiaoEscolhida tratarCepRegiaoEscolhida
            (List<CepRegiaoEscolhidaDatabaseView> cepRegiao)
    {
        System.out.println("\nCEP / REGIAO");
        for (CepRegiaoEscolhidaDatabaseView c : cepRegiao) {
            System.out.println(c.getCep() + " " + c.getDescricaoRegiao());
        }
        return null;
    }

    // GRAFICO 10
    public static BancosEscolhidos tratarBancosEscolhidos
            (List<BancosEscolhidosDatabaseView> bancos)
    {
        BancosEscolhidos bancosEscolhidos = new BancosEscolhidos();

        for (BancosEscolhidosDatabaseView b : bancos) {
            if (Objects.equals(BancoEscolhidoEnum.getBancoEscolhido(b.getBancoEscolhido()),
                    BancoEscolhidoEnum.BANCO_CIFRA))
            {
                bancosEscolhidos.setCifra(b.getContagem());
            }
            else if (Objects.equals(BancoEscolhidoEnum.getBancoEscolhido(b.getBancoEscolhido()),
                    BancoEscolhidoEnum.BANCO_S16_BANK))
            {
                bancosEscolhidos.setS16(b.getContagem());
            }
            else if (Objects.equals(BancoEscolhidoEnum.getBancoEscolhido(b.getBancoEscolhido()),
                    BancoEscolhidoEnum.BANCO_DO_PRESIL))
            {
                bancosEscolhidos.setPresil(b.getContagem());
            }
        }

        return bancosEscolhidos;
    }
}
