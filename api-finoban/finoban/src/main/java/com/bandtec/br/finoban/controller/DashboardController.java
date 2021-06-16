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

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@RestController
@RequestMapping("/api-finoban/dashboard")
public class DashboardController {

    @Autowired
    private DashboardRepository repository;

    @GetMapping
    public ResponseEntity getDataDashboard() {

        List<RendimentoMensal> rendimentoMensal = tratarRendimentoMensal(repository.getRendimentoMensalData());
        Double porcentualPerdas = tratarPorcentualPerdas(repository.getPorcentualPerdaData());
        List<RendimentoMensal> projecaoRendimento = tratarProjecaoRendimento(rendimentoMensal);
        List<TempoPermanencia> tempoPermanencia = tratarTempoPermanencia(repository.getTempoPermanenciaData());
        AvaliacaoSite avaliacaoSite = tratarAvaliacaoSite(repository.getAvaliacaoSiteData());
        List<RegiaoRenda> regiaoRenda = tratarRegiaoRenda(repository.getRegiaoRendaData());
        List<ValorImovelIdade> valorImovelIdade = tratarValorImovelIdade(repository.getValorImovelIdadeData());
        RegioesEscolhidas regioesEscolhidas = tratarRegioesEscolhidas(repository.getRegioesEscolhidasData());
        List<ValorImovelRenda> valorImovelRenda = tratarValorImovelRenda(repository.getValorImovelRendaData());
        List<CepRegiaoEscolhida> cepRegiaoEscolhida = tratarCepRegiaoEscolhida(repository.getCepRegiaoData());
        BancosEscolhidos bancosEscolhidos = tratarBancosEscolhidos(repository.getBancosEscolhidosData());

        DashboardResponse dataDashboard = new DashboardResponse(
                rendimentoMensal, porcentualPerdas, projecaoRendimento, tempoPermanencia,
                avaliacaoSite, regiaoRenda, valorImovelIdade, regioesEscolhidas,
                valorImovelRenda, cepRegiaoEscolhida, bancosEscolhidos);

        return ResponseEntity.status(200).body(dataDashboard);
    }

    // GRAFICO 0
    public static List<RendimentoMensal> tratarRendimentoMensal
            (List<RendimentoMensalDatabaseView> rendimentoData)
    {
        LocalDateTime dataInicio = LocalDateTime.now().minusDays(RendimentoMensal.TEMPO_LIMITE);

        List<RendimentoMensal> rendimentoMensals = new ArrayList<RendimentoMensal>();
//        RendimentoMensal objetoRendimentoMensal = new RendimentoMensal();
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
            rendimentoMensals.add(new RendimentoMensal(dataInicio.plusDays(i).toLocalDate(), (mediaDiaria / tamanho)));
//            objetoRendimentoMensal.adicionarValor(dataInicio.plusDays(i).toLocalDate(), (mediaDiaria / tamanho));
        }
//        return objetoRendimentoMensal;
        return rendimentoMensals;
    }

    // GRAFICO 1
    public static Double tratarPorcentualPerdas
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

        return new PorcentualPerdas(confirmado, naoConfirmado).getPorcentual();
    }

    // GRAFICO 2
    public static List<RendimentoMensal> tratarProjecaoRendimento
            (List<RendimentoMensal> rendimentoMensal)
    {
        if (rendimentoMensal == null) {
            return null;
        }

        List<RendimentoMensal> projecao = new ArrayList<RendimentoMensal>();
        ProjecaoRendimento projecaoRendimento = new ProjecaoRendimento();
        List<Double> valoresRendimento = new ArrayList<Double>();

        for (RendimentoMensal rm : rendimentoMensal) {
            valoresRendimento.add(rm.getValor());
        }

        List<Double> dias = new ArrayList<>();

        for (double i = 1.0; i <= RendimentoMensal.TEMPO_LIMITE; i++) {
            dias.add(i);
        }

        projecaoRendimento.gerarRegressaoLinear(dias, valoresRendimento);

        for (double i = 1; i <= RendimentoMensal.TEMPO_LIMITE; i++) {
            projecao.add(new RendimentoMensal(
                    LocalDate.now().plusDays((long) i),
                    projecaoRendimento.calculoRegressaoLinear(i)));
        }
        return projecao;
    }

    // GRAFICO 3
    public static List<TempoPermanencia> tratarTempoPermanencia
            (List<TempoPermanenciaDatabaseView> tempoPermanencia)
    {
//        TempoPermanencia permanencia = new TempoPermanencia();
        List<TempoPermanencia> permanencia = new ArrayList<TempoPermanencia>();

        LocalTime timeMinimo = LocalTime.of(0, 0, 0);
        Integer segundosAcrescentar = 0;

        Long diferencaTempoAtual;
        Integer quantidade = 0;

        while (timeMinimo.getMinute() < TempoPermanencia.LIMITE_TEMPO.getMinute()) {
            for (TempoPermanenciaDatabaseView p : tempoPermanencia) {
                if (p.getDataHoraEntrada() != null && p.getDataHoraSaida() != null) {
                    diferencaTempoAtual = TempoPermanencia.diferencaTempoEpoch(p.getDataHoraEntrada(), p.getDataHoraSaida());

                /*if (timeAtual.getSecond() < (timeMinimo.getSecond() + segundosAcrescentar)
                        || timeAtual.getMinute() == timeMinimo.getMinute()) {*/
                    if (diferencaTempoAtual > segundosAcrescentar
                            && diferencaTempoAtual < segundosAcrescentar + TempoPermanencia.INTERVALO_TEMPO) {
                        quantidade++;
                    }
                }
            }
            segundosAcrescentar += TempoPermanencia.INTERVALO_TEMPO;
            timeMinimo = timeMinimo.plusSeconds(TempoPermanencia.INTERVALO_TEMPO);
//            permanencia.adicionarValor(timeMinimo, quantidade);
            permanencia.add(new TempoPermanencia(quantidade, timeMinimo));
            quantidade = 0;
        }

        for (TempoPermanenciaDatabaseView p : tempoPermanencia) {
            if (p.getDataHoraEntrada() != null && p.getDataHoraSaida() != null) {
                diferencaTempoAtual = TempoPermanencia.diferencaTempoEpoch(p.getDataHoraEntrada(), p.getDataHoraSaida());
                if (diferencaTempoAtual > segundosAcrescentar) {
                    quantidade++;
                }
            }
        }
        timeMinimo = timeMinimo.plusSeconds(TempoPermanencia.INTERVALO_TEMPO);
//        permanencia.adicionarValor(timeMinimo, quantidade);
        permanencia.add(new TempoPermanencia(quantidade, timeMinimo));

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
    public static List<RegiaoRenda> tratarRegiaoRenda
            (List<RegiaoRendaDatabaseView> regiaoRenda)
    {
        List<RegiaoRenda> valoresRegiaoRenda = new ArrayList<RegiaoRenda>();

        for (RegiaoRendaDatabaseView r : regiaoRenda) {
            valoresRegiaoRenda.add(new RegiaoRenda(r.getDescricaoRegiao(), r.getValorRegiao(), r.getRenda()));
        }

        return valoresRegiaoRenda;
    }

    // GRAFICO 6
    public static List<ValorImovelIdade> tratarValorImovelIdade
            (List<ValorImovelIdadeDatabaseView> imovelIdade)
    {
        List<ValorImovelIdade> valorImovelIdades = new ArrayList<ValorImovelIdade>();

        for (ValorImovelIdadeDatabaseView i : imovelIdade)
        {
            valorImovelIdades.add(
                    new ValorImovelIdade(
                            ValorImovelIdade.converterDataParaIdade(i.getDataNasc()),
                            i.getValorImovel()));
        }

        return valorImovelIdades;
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
    public static List<ValorImovelRenda> tratarValorImovelRenda
            (List<ValorImovelRendaDatabaseView> imovelRenda)
    {
        List<ValorImovelRenda> valorImovelRendas = new ArrayList<ValorImovelRenda>();

        for (ValorImovelRendaDatabaseView i : imovelRenda)
        {
            valorImovelRendas.add(
                    new ValorImovelRenda(
                            i.getValorImovel(),
                            i.getRenda()));
        }

        return valorImovelRendas;
    }

    // GRAFICO 9
    public static List<CepRegiaoEscolhida> tratarCepRegiaoEscolhida
            (List<CepRegiaoEscolhidaDatabaseView> cepRegiao)
    {
        List<CepRegiaoEscolhida> cepRegiaoEscolhidas = new ArrayList<CepRegiaoEscolhida>();

        List<BairrosRegioes> bairrosRegioesInseridos = new ArrayList<BairrosRegioes>();
        String regiaoAtual;
        String bairroAtual;
        Integer iteradorRegiao;
        Boolean isIn;

        for (CepRegiaoEscolhidaDatabaseView cr : cepRegiao) {
            if (cr.getRegiaoEscolhida() != null && cr.getBairroCliente() != null) {
                bairroAtual = cr.getBairroCliente();
                regiaoAtual = cr.getRegiaoEscolhida();
                iteradorRegiao = 0;
                isIn = false;
                for (BairrosRegioes br : bairrosRegioesInseridos) {
                    if (bairroAtual.equals(br.getBairro()) && regiaoAtual.equals(br.getRegiao())) {
                        isIn = true;
                    }
                }
                if (!isIn) {
                    for (CepRegiaoEscolhidaDatabaseView c : cepRegiao) {
                        if (bairroAtual.equals(c.getBairroCliente())
                                && regiaoAtual.equals(c.getRegiaoEscolhida())) {
                            iteradorRegiao += 1;
                        }
                    }
                    cepRegiaoEscolhidas.add(new CepRegiaoEscolhida(bairroAtual, regiaoAtual, iteradorRegiao));
                    bairrosRegioesInseridos.add(new BairrosRegioes(bairroAtual, regiaoAtual));
                }
            }
        }

        return cepRegiaoEscolhidas;
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
