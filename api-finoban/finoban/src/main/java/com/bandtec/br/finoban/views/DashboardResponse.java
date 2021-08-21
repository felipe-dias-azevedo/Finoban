package com.bandtec.br.finoban.views;

import com.bandtec.br.finoban.views.charts.*;

import java.util.List;

public class DashboardResponse {

    private List<List<Object>> rendimentoMensal;
    private Double porcentualPerdas;
    private List<List<Object>> projecaoRendimento;
    private List<TempoPermanencia> tempoPermanencia;
    private AvaliacaoSite avaliacaoSite;
    private List<RegiaoRenda> regiaoRenda;
    private List<ValorImovelIdade> valorImovelIdade;
    private RegioesEscolhidas regioesEscolhidas;
    private List<ValorImovelRenda> valorImovelRenda;
    private List<CepRegiaoEscolhida> cepRegiaoEscolhida;
    private BancosEscolhidos bancosEscolhidos;

    public DashboardResponse(
            List<List<Object>> rendimentoMensal,
            Double porcentualPerdas,
            List<List<Object>> projecaoRendimento,
            List<TempoPermanencia> tempoPermanencia,
            AvaliacaoSite avaliacaoSite,
            List<RegiaoRenda> regiaoRenda,
            List<ValorImovelIdade> valorImovelIdade,
            RegioesEscolhidas regioesEscolhidas,
            List<ValorImovelRenda> valorImovelRenda,
            List<CepRegiaoEscolhida> cepRegiaoEscolhida,
            BancosEscolhidos bancosEscolhidos)
    {
        this.rendimentoMensal = rendimentoMensal;
        this.porcentualPerdas = porcentualPerdas;
        this.projecaoRendimento = projecaoRendimento;
        this.tempoPermanencia = tempoPermanencia;
        this.avaliacaoSite = avaliacaoSite;
        this.regiaoRenda = regiaoRenda;
        this.valorImovelIdade = valorImovelIdade;
        this.regioesEscolhidas = regioesEscolhidas;
        this.valorImovelRenda = valorImovelRenda;
        this.cepRegiaoEscolhida = cepRegiaoEscolhida;
        this.bancosEscolhidos = bancosEscolhidos;
    }

    public List<List<Object>> getRendimentoMensal() {
        return rendimentoMensal;
    }

    public Double getPorcentualPerdas() {
        return porcentualPerdas;
    }

    public List<List<Object>> getProjecaoRendimento() {
        return projecaoRendimento;
    }

    public List<TempoPermanencia> getTempoPermanencia() {
        return tempoPermanencia;
    }

    public AvaliacaoSite getAvaliacaoSite() {
        return avaliacaoSite;
    }

    public List<RegiaoRenda> getRegiaoRenda() {
        return regiaoRenda;
    }

    public List<ValorImovelIdade> getValorImovelIdade() {
        return valorImovelIdade;
    }

    public RegioesEscolhidas getRegioesEscolhidas() {
        return regioesEscolhidas;
    }

    public List<ValorImovelRenda> getValorImovelRenda() {
        return valorImovelRenda;
    }

    public List<CepRegiaoEscolhida> getCepRegiaoEscolhida() {
        return cepRegiaoEscolhida;
    }

    public BancosEscolhidos getBancosEscolhidos() {
        return bancosEscolhidos;
    }
}
