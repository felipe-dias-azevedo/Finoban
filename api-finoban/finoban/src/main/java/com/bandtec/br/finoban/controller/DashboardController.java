package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.dominio.enums.*;
import com.bandtec.br.finoban.repository.DashboardRepository;
import com.bandtec.br.finoban.dominio.views.DashboardResponse;
import com.bandtec.br.finoban.dominio.views.charts.*;
import com.bandtec.br.finoban.dominio.views.database.*;
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
        List<List<Object>> cepRegiaoEscolhida = tratarCepRegiaoEscolhida(repository.getCepRegiaoData());
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
        Double mediaDiaria;
        Integer tamanho;

        rendimentoMensals.add(Arrays.asList(new Object[] {"Data", "Valor"}));

        for (int i = 0; i < RendimentoMensal.TEMPO_LIMITE; i++) {
            mediaDiaria = 0.0;
            tamanho = 1;
            for (RendimentoMensalDatabaseView r : rendimentoData) {
                if (r.getDataHoraSaida().getDayOfMonth() == dataInicio.plusDays(i).getDayOfMonth()) {
                    mediaDiaria += r.getRenda();
                    tamanho++;
                }
            }
            rendimentoMensals.add(Arrays.asList(new Object[] {
                    dataInicio.plusDays(i).toLocalDate(), mediaDiaria / tamanho}));
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
        resultadoPercentualPerdas.add(Arrays.asList(new Object[] {"Label", "Value"}));
        resultadoPercentualPerdas.add(Arrays.asList(new Object[] {
                "Desistência", new PorcentualPerdas(confirmado, naoConfirmado).getPorcentual()}));

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
        ProjecaoRendimento projecaoRendimento = new ProjecaoRendimento();
        List<Double> valoresRendimento = new ArrayList<>();

        projecao.add(Arrays.asList(new Object[] {"Data", "Valor"}));

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
            projecao.add(Arrays.asList(new Object[] {
                    LocalDate.now().plusDays((long) i), projecaoRendimento.calculoRegressaoLinear(i)}));
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
        permanencia.add(Arrays.asList(new Object[] {"Tempo de Permanência", "Quantidade"}));

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
            permanencia.add(Arrays.asList(new Object[] {timeMinimo, quantidade}));
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
        permanencia.add(Arrays.asList(new Object[] {timeMinimo, quantidade}));

        return permanencia;
    }

    // GRAFICO 4
    public static List<List<Object>> tratarAvaliacaoSite
            (List<AvaliacaoSiteDatabaseView> avaliacao)
    {
        AvaliacaoSite avaliacaoSite = new AvaliacaoSite();

        List<List<Object>> resultadoAvaliacaoSite = new ArrayList<>();
        resultadoAvaliacaoSite.add(Arrays.asList(new Object[] {"Tipo de Avaliação", "Quantidade"}));

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
        resultadoAvaliacaoSite.add(Arrays.asList(new Object[] {"Gostou", avaliacaoSite.getGostou()}));
        resultadoAvaliacaoSite.add(Arrays.asList(new Object[] {"Não gostou", avaliacaoSite.getNaoGostou()}));
        resultadoAvaliacaoSite.add(Arrays.asList(new Object[] {"Sem feedback", avaliacaoSite.getSemFeedback()}));

        return resultadoAvaliacaoSite;
    }

    // GRAFICO 5
    public static List<List<Object>> tratarRegiaoRenda
            (List<RegiaoRendaDatabaseView> regiaoRenda)
    {
//        List<RegiaoRenda> valoresRegiaoRenda = new ArrayList<>();
        List<List<Object>> resultadoRegiaoRenda = new ArrayList<>();
        resultadoRegiaoRenda.add(Arrays.asList(new Object[] {"Valor Região", "Valor Renda"}));

        for (RegiaoRendaDatabaseView r : regiaoRenda) {
            resultadoRegiaoRenda.add(Arrays.asList(new Object[] {r.getValorRegiao(), r.getRenda()}));
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
        resultadoImovelIdades.add(Arrays.asList(new Object[]{"Valor Imóvel", "Idade"}));

        for (ValorImovelIdadeDatabaseView i : imovelIdade)
        {
            resultadoImovelIdades.add(Arrays.asList(new Object[]{
                    ValorImovelIdade.converterDataParaIdade(i.getDataNasc()), i.getValorImovel()}));
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
        resultadoRegioesEscolhidas.add(Arrays.asList(new Object[]{"Região Escolhida", "Quantidade"}));

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
        resultadoRegioesEscolhidas.add(Arrays.asList(new Object[]{"Centro", regioesEscolhidas.getCentro()}));
        resultadoRegioesEscolhidas.add(Arrays.asList(new Object[]{"Consolação", regioesEscolhidas.getConsolacao()}));
        resultadoRegioesEscolhidas.add(Arrays.asList(new Object[]{"Brooklin", regioesEscolhidas.getBrooklin()}));
        resultadoRegioesEscolhidas.add(Arrays.asList(new Object[]{"Mooca", regioesEscolhidas.getMooca()}));
        resultadoRegioesEscolhidas.add(Arrays.asList(new Object[]{"Santo Amaro", regioesEscolhidas.getSantoAmaro()}));
        resultadoRegioesEscolhidas.add(Arrays.asList(new Object[]{"Interlagos", regioesEscolhidas.getInterlagos()}));

        return resultadoRegioesEscolhidas;
    }

    // GRAFICO 8
    public static List<List<Object>> tratarValorImovelRenda
            (List<ValorImovelRendaDatabaseView> imovelRenda)
    {
//        List<ValorImovelRenda> valorImovelRendas = new ArrayList<>();
        List<List<Object>> resultadoImovelRendas = new ArrayList<>();
        resultadoImovelRendas.add(Arrays.asList(new Object[]{"Valor Imóvel", "Valor Renda"}));

        for (ValorImovelRendaDatabaseView i : imovelRenda)
        {
            resultadoImovelRendas.add(Arrays.asList(new Object[]{i.getValorImovel(), i.getRenda()}));
//            valorImovelRendas.add(
//                    new ValorImovelRenda(
//                            i.getValorImovel(),
//                            i.getRenda()));
        }

        return resultadoImovelRendas;
    }

    // GRAFICO 9
    public static List<List<Object>> tratarCepRegiaoEscolhida
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

        // tratando os dados sujos de cima para o front.

        List<List<Object>> resultadoCepRegiao = new ArrayList<>();
        resultadoCepRegiao.add(Arrays.asList(new Object[]{
                "Bairro", "Centro","Consolação","Brooklin",
                "Mooca","Santo Amaro","Interlagos"}));
        List<String> bairrosLidos = new ArrayList<>();
        Boolean bairroEmBairrosLidos;
        String bairroAtualLido;

        for (int i = 0; i < cepRegiaoEscolhidas.size(); i++) {
            bairroAtualLido = cepRegiaoEscolhidas.get(i).getBairroCliente();
            bairroEmBairrosLidos = false;
            for (int j = 0; j < bairrosLidos.size(); j++) {
                if (bairrosLidos.get(j).equals(bairroAtualLido)) {
                    bairroEmBairrosLidos = true;
                }
            }
            if (!bairroEmBairrosLidos) {
                resultadoCepRegiao.add(Arrays.asList(new Object[]{
                        bairroAtualLido,0,0,0,0,0,0}));
                for (int j = 0; j < cepRegiaoEscolhidas.size(); j++) {
                    if (bairroAtualLido.equals(cepRegiaoEscolhidas.get(j).getBairroCliente())) {
                        for (int k = 0; k < resultadoCepRegiao.get(0).size() - 1; k++) {
                            if (cepRegiaoEscolhidas.get(j).getRegiaoEscolhida().equals(
                                    resultadoCepRegiao.get(0).get(k + 1)))
                            {
                                resultadoCepRegiao.get(resultadoCepRegiao.size() - 1).set(k + 1,
                                        cepRegiaoEscolhidas.get(j).getContagem());
                            }
                        }
                    }
                }
                bairrosLidos.add(bairroAtualLido);
            }
        }

        return resultadoCepRegiao;
    }

    // GRAFICO 10
    public static List<List<Object>> tratarBancosEscolhidos
            (List<BancosEscolhidosDatabaseView> bancos)
    {
        BancosEscolhidos bancosEscolhidos = new BancosEscolhidos();

        List<List<Object>> resultadoBancosEscolhidos = new ArrayList<>();
        resultadoBancosEscolhidos.add(Arrays.asList(new Object[]{"Banco Escolhido", "Quantidade"}));

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
        resultadoBancosEscolhidos.add(Arrays.asList(new Object[]{"Banco Cifra", bancosEscolhidos.getCifra()}));
        resultadoBancosEscolhidos.add(Arrays.asList(new Object[]{"S16 Bank", bancosEscolhidos.getS16()}));
        resultadoBancosEscolhidos.add(Arrays.asList(new Object[]{"Banco do Presil", bancosEscolhidos.getPresil()}));

        return resultadoBancosEscolhidos;
    }
}
