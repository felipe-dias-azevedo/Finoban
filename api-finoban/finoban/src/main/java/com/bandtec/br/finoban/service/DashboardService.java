package com.bandtec.br.finoban.service;

import com.bandtec.br.finoban.dominio.DAO.*;
import com.bandtec.br.finoban.dominio.enums.AvalPositivoEnum;
import com.bandtec.br.finoban.dominio.enums.BancoEscolhidoEnum;
import com.bandtec.br.finoban.dominio.enums.RegioesEnum;
import com.bandtec.br.finoban.dominio.enums.StatusSaidaEnum;
import com.bandtec.br.finoban.dominio.resposta.DashboardDTO;
import com.bandtec.br.finoban.dominio.views.charts.*;
import com.bandtec.br.finoban.repository.database.DashboardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class DashboardService {

    private DashboardRepository repository;

    public DashboardDTO obterDadosDashboard() {

        List<List<Object>> rendimentoMensal = tratarRendimentoMensal(repository.getRendimentoMensalData());

        return new DashboardDTO(
                rendimentoMensal,
                tratarPorcentualPerdas(repository.getPorcentualPerdaData()),
                tratarProjecaoRendimento(rendimentoMensal),
                tratarTempoPermanencia(repository.getTempoPermanenciaData()),
                tratarAvaliacaoSite(repository.getAvaliacaoSiteData()),
                tratarRegiaoRenda(repository.getRegiaoRendaData()),
                tratarValorImovelIdade(repository.getValorImovelIdadeData()),
                tratarRegioesEscolhidas(repository.getRegioesEscolhidasData()),
                tratarValorImovelRenda(repository.getValorImovelRendaData()),
                tratarCepRegiaoEscolhida(repository.getCepRegiaoData()),
                tratarBancosEscolhidos(repository.getBancosEscolhidosData())
        );
    }

    // GRAFICO 1
    private List<List<Object>> tratarRendimentoMensal
    (List<RendimentoMensalDAO> rendimentoData)
    {
        LocalDateTime dataInicio = LocalDateTime.now().minusDays(RendimentoMensal.TEMPO_LIMITE);

        List<List<Object>> rendimentoMensals = new ArrayList<>();
        Double mediaDiaria;
        Integer tamanho;

        rendimentoMensals.add(Arrays.asList(new Object[] {"Data", "Valor"}));

        for (int i = 0; i < RendimentoMensal.TEMPO_LIMITE; i++) {
            mediaDiaria = 0.0;
            tamanho = 1;
            for (RendimentoMensalDAO r : rendimentoData) {
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

    // GRAFICO 2
    private List<List<Object>> tratarPorcentualPerdas
    (List<PorcentualPerdasDAO> perdas)
    {
        Double confirmado = 0.0;
        Double naoConfirmado = 0.0;

        for (PorcentualPerdasDAO p : perdas) {
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

    // GRAFICO 3
    private List<List<Object>> tratarProjecaoRendimento
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

    // GRAFICO 4
    private List<List<Object>> tratarTempoPermanencia
    (List<TempoPermanenciaDAO> tempoPermanencia)
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
            for (TempoPermanenciaDAO p : tempoPermanencia) {
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

        for (TempoPermanenciaDAO p : tempoPermanencia) {
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

    // GRAFICO 5
    private List<List<Object>> tratarAvaliacaoSite
    (List<AvaliacaoSiteDAO> avaliacao)
    {
        AvaliacaoSite avaliacaoSite = new AvaliacaoSite();

        List<List<Object>> resultadoAvaliacaoSite = new ArrayList<>();
        resultadoAvaliacaoSite.add(Arrays.asList(new Object[] {"Tipo de Avaliação", "Quantidade"}));

        for (AvaliacaoSiteDAO a : avaliacao) {
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

    // GRAFICO 6
    private List<List<Object>> tratarRegiaoRenda
    (List<RegiaoRendaDAO> regiaoRenda)
    {
//        List<RegiaoRenda> valoresRegiaoRenda = new ArrayList<>();
        List<List<Object>> resultadoRegiaoRenda = new ArrayList<>();
        resultadoRegiaoRenda.add(Arrays.asList(new Object[] {"Valor Região", "Valor Renda"}));

        for (RegiaoRendaDAO r : regiaoRenda) {
            resultadoRegiaoRenda.add(Arrays.asList(new Object[] {r.getValorRegiao(), r.getRenda()}));
//            valoresRegiaoRenda.add(new RegiaoRenda(r.getDescricaoRegiao(), r.getValorRegiao(), r.getRenda()));
        }

        return resultadoRegiaoRenda;
    }

    // GRAFICO 7
    private List<List<Object>> tratarValorImovelIdade
    (List<ValorImovelIdadeDAO> imovelIdade)
    {
//        List<ValorImovelIdade> valorImovelIdades = new ArrayList<>();
        List<List<Object>> resultadoImovelIdades = new ArrayList<>();
        resultadoImovelIdades.add(Arrays.asList(new Object[]{"Valor Imóvel", "Idade"}));

        for (ValorImovelIdadeDAO i : imovelIdade)
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

    // GRAFICO 8
    private List<List<Object>> tratarRegioesEscolhidas
    (List<RegioesEscolhidasDAO> regioes)
    {
        RegioesEscolhidas regioesEscolhidas = new RegioesEscolhidas();

        List<List<Object>> resultadoRegioesEscolhidas = new ArrayList<>();
        resultadoRegioesEscolhidas.add(Arrays.asList(new Object[]{"Região Escolhida", "Quantidade"}));

        for (RegioesEscolhidasDAO r : regioes) {
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

    // GRAFICO 9
    private List<List<Object>> tratarValorImovelRenda
    (List<ValorImovelRendaDAO> imovelRenda)
    {
//        List<ValorImovelRenda> valorImovelRendas = new ArrayList<>();
        List<List<Object>> resultadoImovelRendas = new ArrayList<>();
        resultadoImovelRendas.add(Arrays.asList(new Object[]{"Valor Imóvel", "Valor Renda"}));

        for (ValorImovelRendaDAO i : imovelRenda)
        {
            resultadoImovelRendas.add(Arrays.asList(new Object[]{i.getValorImovel(), i.getRenda()}));
//            valorImovelRendas.add(
//                    new ValorImovelRenda(
//                            i.getValorImovel(),
//                            i.getRenda()));
        }

        return resultadoImovelRendas;
    }

    // GRAFICO 10
    private List<List<Object>> tratarCepRegiaoEscolhida
    (List<CepRegiaoEscolhidaDAO> cepRegiao)
    {
        List<CepRegiaoEscolhida> cepRegiaoEscolhidas = new ArrayList<>();

        List<BairrosRegioes> bairrosRegioesInseridos = new ArrayList<>();
        String regiaoAtual;
        String bairroAtual;
        Integer iteradorRegiao;
        Boolean isIn;

        for (CepRegiaoEscolhidaDAO cr : cepRegiao) {
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
                    for (CepRegiaoEscolhidaDAO c : cepRegiao) {
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

    // GRAFICO 11
    private List<List<Object>> tratarBancosEscolhidos
    (List<BancosEscolhidosDAO> bancos)
    {
        BancosEscolhidos bancosEscolhidos = new BancosEscolhidos();

        List<List<Object>> resultadoBancosEscolhidos = new ArrayList<>();
        resultadoBancosEscolhidos.add(Arrays.asList(new Object[]{"Banco Escolhido", "Quantidade"}));

        for (BancosEscolhidosDAO b : bancos) {
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
