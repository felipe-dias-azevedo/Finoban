package com.bandtec.br.finoban.views;

import com.bandtec.br.finoban.views.charts.*;

import java.util.List;

public class DashboardResponse {

    private List<RendimentoMensal> rendimentoMensal;
    private Double porcentualPerdas;
    private List<RendimentoMensal> projecaoRendimento;
    private List<TempoPermanencia> tempoPermanencia;
    private AvaliacaoSite avaliacaoSite;
    private List<RegiaoRenda> regiaoRenda;
    private List<ValorImovelIdade> valorImovelIdade;
    private RegioesEscolhidas regioesEscolhidas;
    private List<ValorImovelRenda> valorImovelRenda;
    private List<CepRegiaoEscolhida> cepRegiaoEscolhida;
    private BancosEscolhidos bancosEscolhidos;

    public DashboardResponse(
            List<RendimentoMensal> rendimentoMensal,
            Double porcentualPerdas,
            List<RendimentoMensal> projecaoRendimento,
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

    public List<RendimentoMensal> getRendimentoMensal() {
        return rendimentoMensal;
    }

    public Double getPorcentualPerdas() {
        return porcentualPerdas;
    }

    public List<RendimentoMensal> getProjecaoRendimento() {
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
