package com.bandtec.br.finoban.views;

import com.bandtec.br.finoban.views.charts.*;

import java.util.List;

public class DashboardResponse {

    private List<List<Object>> rendimentoMensal;
    private List<List<Object>> porcentualPerdas;
    private List<List<Object>> projecaoRendimento;
    private List<List<Object>> tempoPermanencia;
    private List<List<Object>> avaliacaoSite;
    private List<List<Object>> regiaoRenda;
    private List<List<Object>> valorImovelIdade;
    private List<List<Object>> regioesEscolhidas;
    private List<List<Object>> valorImovelRenda;
    private List<CepRegiaoEscolhida> cepRegiaoEscolhida;
    private List<List<Object>> bancosEscolhidos;

    public DashboardResponse(
            List<List<Object>> rendimentoMensal,
            List<List<Object>> porcentualPerdas,
            List<List<Object>> projecaoRendimento,
            List<List<Object>> tempoPermanencia,
            List<List<Object>> avaliacaoSite,
            List<List<Object>> regiaoRenda,
            List<List<Object>> valorImovelIdade,
            List<List<Object>> regioesEscolhidas,
            List<List<Object>> valorImovelRenda,
            List<CepRegiaoEscolhida> cepRegiaoEscolhida,
            List<List<Object>> bancosEscolhidos)
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

    public List<List<Object>> getPorcentualPerdas() {
        return porcentualPerdas;
    }

    public List<List<Object>> getProjecaoRendimento() {
        return projecaoRendimento;
    }

    public List<List<Object>> getTempoPermanencia() {
        return tempoPermanencia;
    }

    public List<List<Object>> getAvaliacaoSite() {
        return avaliacaoSite;
    }

    public List<List<Object>> getRegiaoRenda() {
        return regiaoRenda;
    }

    public List<List<Object>> getValorImovelIdade() {
        return valorImovelIdade;
    }

    public List<List<Object>> getRegioesEscolhidas() {
        return regioesEscolhidas;
    }

    public List<List<Object>> getValorImovelRenda() {
        return valorImovelRenda;
    }

    public List<CepRegiaoEscolhida> getCepRegiaoEscolhida() {
        return cepRegiaoEscolhida;
    }

    public List<List<Object>> getBancosEscolhidos() {
        return bancosEscolhidos;
    }
}
