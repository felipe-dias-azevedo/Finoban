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
import java.util.*;

@RestController
@RequestMapping("/api-finoban/dashboard")
public class DashboardController {

    @Autowired
    private DashboardRepository repository;

    @GetMapping
    public ResponseEntity<DashboardResponse> getDataDashboard() {

        List<List<Object>> rendimentoMensal = tratarRendimentoMensal(repository.getRendimentoMensalData());
        List<List<Object>> porcentualPerdas = tratarPorcentualPerdas(repository.getPorcentualPerdaData());
        List<List<Object>> projecaoRendimento = tratarProjecaoRendimento(rendimentoMensal);
        List<List<Object>> tempoPermanencia = tratarTempoPermanencia(repository.getTempoPermanenciaData());
        List<List<Object>> avaliacaoSite = tratarAvaliacaoSite(repository.getAvaliacaoSiteData());
        List<List<Object>> regiaoRenda = tratarRegiaoRenda(repository.getRegiaoRendaData());
        List<List<Object>> valorImovelIdade = tratarValorImovelIdade(repository.getValorImovelIdadeData());
        List<List<Object>> regioesEscolhidas = tratarRegioesEscolhidas(repository.getRegioesEscolhidasData());
        List<List<Object>> valorImovelRenda = tratarValorImovelRenda(repository.getValorImovelRendaData());
        List<CepRegiaoEscolhida> cepRegiaoEscolhida = tratarCepRegiaoEscolhida(repository.getCepRegiaoData());
        List<List<Object>> bancosEscolhidos = tratarBancosEscolhidos(repository.getBancosEscolhidosData());

        DashboardResponse dataDashboard = new DashboardResponse(
                rendimentoMensal, porcentualPerdas, projecaoRendimento, tempoPermanencia,
                avaliacaoSite, regiaoRenda, valorImovelIdade, regioesEscolhidas,
                valorImovelRenda, cepRegiaoEscolhida, bancosEscolhidos);

        return ResponseEntity.status(200).body(dataDashboard);
    }

    // GRAFICO 0
    public static List<List<Object>> tratarRendimentoMensal
            (List<RendimentoMensalDatabaseView> rendimentoData)
    {
        LocalDateTime dataInicio = LocalDateTime.now().minusDays(RendimentoMensal.TEMPO_LIMITE);

        List<List<Object>> rendimentoMensals = new ArrayList<>();
        List<Object> valorRendimento = new ArrayList<>();
        Double mediaDiaria;
        Integer tamanho;

        valorRendimento.add("data");
        valorRendimento.add("valor");
        rendimentoMensals.add(valorRendimento);

        for (int i = 0; i < RendimentoMensal.TEMPO_LIMITE; i++) {
            valorRendimento = new ArrayList<>();
            mediaDiaria = 0.0;
            tamanho = 1;
            for (RendimentoMensalDatabaseView r : rendimentoData) {
                if (r.getDataHoraSaida().getDayOfMonth() == dataInicio.plusDays(i).getDayOfMonth()) {
                    mediaDiaria += r.getRenda();
                    tamanho++;
                }
            }
            valorRendimento.add(dataInicio.plusDays(i).toLocalDate());
            valorRendimento.add(mediaDiaria / tamanho);
            rendimentoMensals.add(valorRendimento);
//            rendimentoMensals.add(new RendimentoMensal(, ()));
        }
        return rendimentoMensals;
    }

    // GRAFICO 1
    public static List<List<Object>> tratarPorcentualPerdas
            (List<PorcentualPerdasDatabaseView> perdas)
    {
        Double confirmado = 0.0;
        Double naoConfirmado = 0.0;

        for (PorcentualPerdasDatabaseView p : perdas) {
            if (Objects.equals(
                    StatusSaidaEnum.getConfirmouContratacao(p.getStatusSaida()),
                    StatusSaidaEnum.CONFIRMOU_CONTRATACAO))
            {
                confirmado = p.getContagem().doubleValue();
            }
            else if (Objects.equals(
                    StatusSaidaEnum.getConfirmouContratacao(p.getStatusSaida()),
                    StatusSaidaEnum.NAO_CONFIRMOU))
            {
                naoConfirmado = p.getContagem().doubleValue();
            }
        }

        List<List<Object>> resultadoPercentualPerdas = new ArrayList<>();
        List<Object> labelPercentualPerdas = new ArrayList<>();
        List<Object> valoresPercentualPerdas = new ArrayList<>();
        labelPercentualPerdas.add("Label");
        labelPercentualPerdas.add("Value");
        valoresPercentualPerdas.add("Desistência");
        valoresPercentualPerdas.add(
                new PorcentualPerdas(confirmado, naoConfirmado).getPorcentual());
        resultadoPercentualPerdas.add(labelPercentualPerdas);
        resultadoPercentualPerdas.add(valoresPercentualPerdas);

        return resultadoPercentualPerdas;
    }

    // GRAFICO 2
    public static List<List<Object>> tratarProjecaoRendimento
            (List<List<Object>> rendimentoMensal)
    {
        if (rendimentoMensal == null) {
            return null;
        }

        List<List<Object>> projecao = new ArrayList<>();
        List<Object> valoresProjecao = new ArrayList<>();
        ProjecaoRendimento projecaoRendimento = new ProjecaoRendimento();
        List<Double> valoresRendimento = new ArrayList<>();

        valoresProjecao.add("data");
        valoresProjecao.add("valor");
        projecao.add(valoresProjecao);

        for (int i = 1; i < rendimentoMensal.size(); i++) {
            List<Object> rendimentoAnterior = rendimentoMensal.get(i);
            if (rendimentoAnterior.get(1) instanceof Double) {
                valoresRendimento.add((Double) rendimentoAnterior.get(1));
            }
        }
//        for (RendimentoMensal rm : rendimentoMensal) {
//            valoresRendimento.add(rm.getValor());
//        }

        List<Double> dias = new ArrayList<>();

        for (double i = 1.0; i <= RendimentoMensal.TEMPO_LIMITE; i++) {
            dias.add(i);
        }

        projecaoRendimento.gerarRegressaoLinear(dias, valoresRendimento);

        for (double i = 1; i <= RendimentoMensal.TEMPO_LIMITE; i++) {
            valoresProjecao = new ArrayList<>();
            valoresProjecao.add(LocalDate.now().plusDays((long) i));
            valoresProjecao.add(projecaoRendimento.calculoRegressaoLinear(i));
            projecao.add(valoresProjecao);
//            projecao.add(new RendimentoMensal(
//                    LocalDate.now().plusDays((long) i),
//                    projecaoRendimento.calculoRegressaoLinear(i)));
        }
        return projecao;
    }

    // GRAFICO 3
    public static List<List<Object>> tratarTempoPermanencia
            (List<TempoPermanenciaDatabaseView> tempoPermanencia)
    {
//        TempoPermanencia permanencia = new TempoPermanencia();
//        List<TempoPermanencia> permanencia = new ArrayList<TempoPermanencia>();
        List<List<Object>> permanencia = new ArrayList<>();
        List<Object> valoresPermanencia = new ArrayList<>();
        valoresPermanencia.add("Tempo de Permanência");
        valoresPermanencia.add("Quantidade");
        permanencia.add(valoresPermanencia);

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
//            permanencia.add(new TempoPermanencia(quantidade, timeMinimo));
            valoresPermanencia = new ArrayList<>();
            valoresPermanencia.add(timeMinimo);
            valoresPermanencia.add(quantidade);
            permanencia.add(valoresPermanencia);
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
//        permanencia.add(new TempoPermanencia(quantidade, timeMinimo));
        valoresPermanencia = new ArrayList<>();
        valoresPermanencia.add(timeMinimo);
        valoresPermanencia.add(quantidade);
        permanencia.add(valoresPermanencia);

        return permanencia;
    }

    // GRAFICO 4
    public static List<List<Object>> tratarAvaliacaoSite
            (List<AvaliacaoSiteDatabaseView> avaliacao)
    {
        AvaliacaoSite avaliacaoSite = new AvaliacaoSite();

        List<List<Object>> resultadoAvaliacaoSite = new ArrayList<>();
        List<Object> valoresAvaliacaoSite = new ArrayList<>();
        valoresAvaliacaoSite.add("Tipo de Avaliação");
        valoresAvaliacaoSite.add("Quantidade");
        resultadoAvaliacaoSite.add(valoresAvaliacaoSite);

        for (AvaliacaoSiteDatabaseView a : avaliacao) {
            switch (Objects.requireNonNull(
                    AvalPositivoEnum.getAvaliacaoEnum(a.getAvalPositivo())))
            {
                case GOSTOU:
                    avaliacaoSite.setGostou(a.getContagem());
                    break;
                case NAO_GOSTOU:
                    avaliacaoSite.setNaoGostou(a.getContagem());
                    break;
                case NAO_DEU_FEEDBACK:
                    avaliacaoSite.setSemFeedback(a.getContagem());
                    break;
            }
        }
        valoresAvaliacaoSite = new ArrayList<>();
        valoresAvaliacaoSite.add("Gostou");
        valoresAvaliacaoSite.add(avaliacaoSite.getGostou());
        resultadoAvaliacaoSite.add(valoresAvaliacaoSite);

        valoresAvaliacaoSite = new ArrayList<>();
        valoresAvaliacaoSite.add("Não Gostou");
        valoresAvaliacaoSite.add(avaliacaoSite.getNaoGostou());
        resultadoAvaliacaoSite.add(valoresAvaliacaoSite);

        valoresAvaliacaoSite = new ArrayList<>();
        valoresAvaliacaoSite.add("Sem Feedback");
        valoresAvaliacaoSite.add(avaliacaoSite.getSemFeedback());
        resultadoAvaliacaoSite.add(valoresAvaliacaoSite);

        return resultadoAvaliacaoSite;
    }

    // GRAFICO 5
    public static List<List<Object>> tratarRegiaoRenda
            (List<RegiaoRendaDatabaseView> regiaoRenda)
    {
//        List<RegiaoRenda> valoresRegiaoRenda = new ArrayList<>();
        List<List<Object>> resultadoRegiaoRenda = new ArrayList<>();
        List<Object> valoresRegiaoRenda = new ArrayList<>();
        valoresRegiaoRenda.add("Valor Região");
        valoresRegiaoRenda.add("Valor Renda");
        resultadoRegiaoRenda.add(valoresRegiaoRenda);

        for (RegiaoRendaDatabaseView r : regiaoRenda) {
            valoresRegiaoRenda = new ArrayList<>();
            valoresRegiaoRenda.add(r.getValorRegiao());
            valoresRegiaoRenda.add(r.getRenda());
            resultadoRegiaoRenda.add(valoresRegiaoRenda);
//            valoresRegiaoRenda.add(new RegiaoRenda(r.getDescricaoRegiao(), r.getValorRegiao(), r.getRenda()));
        }

        return resultadoRegiaoRenda;
    }

    // GRAFICO 6
    public static List<List<Object>> tratarValorImovelIdade
            (List<ValorImovelIdadeDatabaseView> imovelIdade)
    {
//        List<ValorImovelIdade> valorImovelIdades = new ArrayList<>();
        List<List<Object>> resultadoImovelIdades = new ArrayList<>();
        List<Object> valoresImovelIdades = new ArrayList<>();
        valoresImovelIdades.add("Valor Imóvel");
        valoresImovelIdades.add("Idade");
        resultadoImovelIdades.add(valoresImovelIdades);

        for (ValorImovelIdadeDatabaseView i : imovelIdade)
        {
            valoresImovelIdades = new ArrayList<>();
            valoresImovelIdades.add(
                    ValorImovelIdade.converterDataParaIdade(i.getDataNasc()));
            valoresImovelIdades.add(i.getValorImovel());
            resultadoImovelIdades.add(valoresImovelIdades);
//            valorImovelIdades.add(
//                    new ValorImovelIdade(
//                            ValorImovelIdade.converterDataParaIdade(i.getDataNasc()),
//                            i.getValorImovel()));
        }

        return resultadoImovelIdades;
    }

    // GRAFICO 7
    public static List<List<Object>> tratarRegioesEscolhidas
            (List<RegioesEscolhidasDatabaseView> regioes)
    {
        RegioesEscolhidas regioesEscolhidas = new RegioesEscolhidas();

        List<List<Object>> resultadoRegioesEscolhidas = new ArrayList<>();
        List<Object> valoresRegioesEscolhidas = new ArrayList<>();
        valoresRegioesEscolhidas.add("Região Escolhida");
        valoresRegioesEscolhidas.add("Quantidade");
        resultadoRegioesEscolhidas.add(valoresRegioesEscolhidas);

        for (RegioesEscolhidasDatabaseView r : regioes) {
            switch (Objects.requireNonNull(
                    RegioesEnum.getAvaliacaoEnum(r.getDescricaoRegiao())))
            {
                case CENTRO:
                    regioesEscolhidas.setCentro(r.getContagem());
                    break;
                case CONSOLACAO:
                    regioesEscolhidas.setConsolacao(r.getContagem());
                    break;
                case BROOKLIN:
                    regioesEscolhidas.setBrooklin(r.getContagem());
                    break;
                case MOOCA:
                    regioesEscolhidas.setMooca(r.getContagem());
                    break;
                case SANTO_AMARO:
                    regioesEscolhidas.setSantoAmaro(r.getContagem());
                    break;
                case INTERLAGOS:
                    regioesEscolhidas.setInterlagos(r.getContagem());
                    break;
            }
        }
        valoresRegioesEscolhidas = new ArrayList<>();
        valoresRegioesEscolhidas.add("Centro");
        valoresRegioesEscolhidas.add(regioesEscolhidas.getCentro());
        resultadoRegioesEscolhidas.add(valoresRegioesEscolhidas);

        valoresRegioesEscolhidas = new ArrayList<>();
        valoresRegioesEscolhidas.add("Consolação");
        valoresRegioesEscolhidas.add(regioesEscolhidas.getConsolacao());
        resultadoRegioesEscolhidas.add(valoresRegioesEscolhidas);

        valoresRegioesEscolhidas = new ArrayList<>();
        valoresRegioesEscolhidas.add("Brooklin");
        valoresRegioesEscolhidas.add(regioesEscolhidas.getBrooklin());
        resultadoRegioesEscolhidas.add(valoresRegioesEscolhidas);

        valoresRegioesEscolhidas = new ArrayList<>();
        valoresRegioesEscolhidas.add("Mooca");
        valoresRegioesEscolhidas.add(regioesEscolhidas.getMooca());
        resultadoRegioesEscolhidas.add(valoresRegioesEscolhidas);

        valoresRegioesEscolhidas = new ArrayList<>();
        valoresRegioesEscolhidas.add("Santo Amaro");
        valoresRegioesEscolhidas.add(regioesEscolhidas.getSantoAmaro());
        resultadoRegioesEscolhidas.add(valoresRegioesEscolhidas);

        valoresRegioesEscolhidas = new ArrayList<>();
        valoresRegioesEscolhidas.add("Interlagos");
        valoresRegioesEscolhidas.add(regioesEscolhidas.getInterlagos());
        resultadoRegioesEscolhidas.add(valoresRegioesEscolhidas);

        return resultadoRegioesEscolhidas;
    }

    // GRAFICO 8
    public static List<List<Object>> tratarValorImovelRenda
            (List<ValorImovelRendaDatabaseView> imovelRenda)
    {
//        List<ValorImovelRenda> valorImovelRendas = new ArrayList<>();
        List<List<Object>> resultadoImovelRendas = new ArrayList<>();
        List<Object> valoresImovelRendas = new ArrayList<>();
        valoresImovelRendas.add("Valor Imóvel");
        valoresImovelRendas.add("Valor Renda");
        resultadoImovelRendas.add(valoresImovelRendas);

        for (ValorImovelRendaDatabaseView i : imovelRenda)
        {
            valoresImovelRendas = new ArrayList<>();
            valoresImovelRendas.add(i.getValorImovel());
            valoresImovelRendas.add(i.getRenda());
            resultadoImovelRendas.add(valoresImovelRendas);
//            valorImovelRendas.add(
//                    new ValorImovelRenda(
//                            i.getValorImovel(),
//                            i.getRenda()));
        }

        return resultadoImovelRendas;
    }

    // GRAFICO 9
    public static List<CepRegiaoEscolhida> tratarCepRegiaoEscolhida
            (List<CepRegiaoEscolhidaDatabaseView> cepRegiao)
    {
        List<CepRegiaoEscolhida> cepRegiaoEscolhidas = new ArrayList<>();

        List<BairrosRegioes> bairrosRegioesInseridos = new ArrayList<>();
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
    public static List<List<Object>> tratarBancosEscolhidos
            (List<BancosEscolhidosDatabaseView> bancos)
    {
        BancosEscolhidos bancosEscolhidos = new BancosEscolhidos();

        List<List<Object>> resultadoBancosEscolhidos = new ArrayList<>();
        List<Object> valoresBancosEscolhidos = new ArrayList<>();
        valoresBancosEscolhidos.add("Banco Escolhido");
        valoresBancosEscolhidos.add("Quantidade");
        resultadoBancosEscolhidos.add(valoresBancosEscolhidos);

        for (BancosEscolhidosDatabaseView b : bancos) {
            switch (Objects.requireNonNull(
                    BancoEscolhidoEnum.getBancoEscolhido(b.getBancoEscolhido())))
            {
                case BANCO_CIFRA:
                    bancosEscolhidos.setCifra(b.getContagem());
                    break;
                case BANCO_S16_BANK:
                    bancosEscolhidos.setS16(b.getContagem());
                    break;
                case BANCO_DO_PRESIL:
                    bancosEscolhidos.setPresil(b.getContagem());
                    break;
            }
        }
        valoresBancosEscolhidos = new ArrayList<>();
        valoresBancosEscolhidos.add("Banco Cifra");
        valoresBancosEscolhidos.add(bancosEscolhidos.getCifra());
        resultadoBancosEscolhidos.add(valoresBancosEscolhidos);

        valoresBancosEscolhidos = new ArrayList<>();
        valoresBancosEscolhidos.add("S16 Bank");
        valoresBancosEscolhidos.add(bancosEscolhidos.getS16());
        resultadoBancosEscolhidos.add(valoresBancosEscolhidos);

        valoresBancosEscolhidos = new ArrayList<>();
        valoresBancosEscolhidos.add("Banco do Presil");
        valoresBancosEscolhidos.add(bancosEscolhidos.getPresil());
        resultadoBancosEscolhidos.add(valoresBancosEscolhidos);

        return resultadoBancosEscolhidos;
    }
}
