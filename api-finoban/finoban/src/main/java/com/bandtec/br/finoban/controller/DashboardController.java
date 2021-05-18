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
        ProjecaoRendimento projecaoRendimento = null;
        TempoPermanencia tempoPermanencia = tratarTempoPermanencia(repository.getTempoPermanenciaData());
        AvaliacaoSite avaliacaoSite = tratarAvaliacaoSite(repository.getAvaliacaoSiteData());
        RegiaoRenda regiaoRenda = null;
        ValorImovelIdade valorImovelIdade = null;
        RegioesEscolhidas regioesEscolhidas = tratarRegioesEscolhidas(repository.getRegioesEscolhidasData());
        ValorImovelRenda valorImovelRenda = null;
        CepRegiaoEscolhida cepRegiaoEscolhida = null;
        BancosEscolhidos bancosEscolhidos = tratarBancosEscolhidos(repository.getBancosEscolhidosData());

        DashboardResponse dataDashboard = new DashboardResponse(
                rendimentoMensal, porcentualPerdas, projecaoRendimento, tempoPermanencia,
                avaliacaoSite, regiaoRenda, valorImovelIdade, regioesEscolhidas,
                valorImovelRenda, cepRegiaoEscolhida, bancosEscolhidos);

        return ResponseEntity.status(200).body(dataDashboard);
    }

    public static RendimentoMensal tratarRendimentoMensal
            (List<RendimentoMensalDatabaseView> rendimentoData)
    {
        System.out.println("\nRENDIMENTO MENSAL");
        for (RendimentoMensalDatabaseView rendimentoDatum : rendimentoData) {
            System.out.println(rendimentoDatum.getRenda() + " " + rendimentoDatum.getDataHoraSaida());
        }
        return null;
    }

    public static PorcentualPerdas tratarPorcentualPerdas
            (List<PorcentualPerdasDatabaseView> perdas)
    {
        System.out.println("\nPORCENTUAL PERDAS");
        for (PorcentualPerdasDatabaseView p : perdas) {
            System.out.println(StatusSaidaEnum.getConfirmouContratacao(p.getStatusSaida()).getStatus() + " " + p.getContagem());
        }
        return null;
    }

    public static ProjecaoRendimento tratarProjecaoRendimento
            (RendimentoMensal rendimentoMensal)
    {
        System.out.println("\nPROJECAO RENDIMENTO");
        return null;
    }

    public static TempoPermanencia tratarTempoPermanencia
            (List<TempoPermanenciaDatabaseView> tempoPermanencia)
    {
        System.out.println("\nTEMPO DE PERMANENCIA");
        for (TempoPermanenciaDatabaseView p : tempoPermanencia) {
            System.out.println(p.getDataHoraEntrada() + " " + p.getDataHoraSaida());
        }
        return null;
    }

    public static AvaliacaoSite tratarAvaliacaoSite
            (List<AvaliacaoSiteDatabaseView> avaliacao)
    {
        System.out.println("\nAVALIACAO SITE");
        for (AvaliacaoSiteDatabaseView a : avaliacao) {
            System.out.println(AvalPositivoEnum.getAvaliacaoEnum(a.getAvalPositivo()).getAvaliacao() + " " + a.getContagem());
        }
        return null;
    }

    public static RegioesEscolhidas tratarRegioesEscolhidas
            (List<RegioesEscolhidasDatabaseView> regioes)
    {
        System.out.println("\nREGIOES ESCOLHIDAS");
        for (RegioesEscolhidasDatabaseView r : regioes) {
            System.out.println(r.getDescricaoRegiao() + " " + r.getContagem());
        }
        return null;
    }

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
