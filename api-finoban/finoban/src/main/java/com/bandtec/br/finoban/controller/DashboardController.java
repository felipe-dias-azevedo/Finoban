package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.enums.*;
import com.bandtec.br.finoban.repository.DashboardRepository;
import com.bandtec.br.finoban.views.DashboardResponse;
import com.bandtec.br.finoban.views.charts.*;
import com.bandtec.br.finoban.views.database.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
        Double mediaDiaria = 0.0;
        Integer tamanho = 1;

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
        System.out.println("\nPORCENTUAL PERDAS");
        for (PorcentualPerdasDatabaseView p : perdas) {
            System.out.println(StatusSaidaEnum.getConfirmouContratacao(p.getStatusSaida()).getStatus() + " " + p.getContagem());
        }
        return null;
    }

    // GRAFICO 2
    public static ProjecaoRendimento tratarProjecaoRendimento
            (RendimentoMensal rendimentoMensal)
    {

        ProjecaoRendimento projecaoRendimento = new ProjecaoRendimento();

        List<Double> dias = new ArrayList<Double>();
        List<Double> valores = new ArrayList<Double>();

        for (int i = 1; i <= ProjecaoRendimento.TEMPO_LIMITE; i++) {
            dias.add((double) i);
        }

        projecaoRendimento.gerarRegressaoLinear(rendimentoMensal.getValores(), dias);

        for (int i = ProjecaoRendimento.TEMPO_LIMITE+1; i <= ProjecaoRendimento.TEMPO_LIMITE*2; i++) {
            projecaoRendimento.adicionarValor(
                    LocalDate.now().plusDays(i - ProjecaoRendimento.TEMPO_LIMITE),
                    projecaoRendimento.calculoRegressaoLinear((double) i));
        }
//        return projecaoRendimento;
        return null;
    }

    // GRAFICO 3
    public static TempoPermanencia tratarTempoPermanencia
            (List<TempoPermanenciaDatabaseView> tempoPermanencia)
    {
        System.out.println("\nTEMPO DE PERMANENCIA");
        for (TempoPermanenciaDatabaseView p : tempoPermanencia) {
            System.out.println(p.getDataHoraEntrada() + " " + p.getDataHoraSaida());
        }
        return null;
    }

    // GRAFICO 4
    public static AvaliacaoSite tratarAvaliacaoSite
            (List<AvaliacaoSiteDatabaseView> avaliacao)
    {
        System.out.println("\nAVALIACAO SITE");
        for (AvaliacaoSiteDatabaseView a : avaliacao) {
            System.out.println(AvalPositivoEnum.getAvaliacaoEnum(a.getAvalPositivo()).getAvaliacao() + " " + a.getContagem());
        }
        return null;
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
        System.out.println("\nREGIOES ESCOLHIDAS");
        for (RegioesEscolhidasDatabaseView r : regioes) {
            System.out.println(r.getDescricaoRegiao() + " " + r.getContagem());
        }
        return null;
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
        System.out.println("\nBANCOS ESCOLHIDOS");
        for (BancosEscolhidosDatabaseView b : bancos) {
            System.out.println(BancoEscolhidoEnum.getBancoEscolhido(b.getBancoEscolhido()).getNomebanco() + " " + b.getContagem());
        }
        return null;
    }
}
