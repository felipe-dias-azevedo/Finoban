package com.bandtec.br.finoban.views;

import com.bandtec.br.finoban.views.charts.*;

public class DashboardResponse {

    private RendimentoMensal rendimentoMensal;
    private PorcentualPerdas porcentualPerdas;
    private ProjecaoRendimento projecaoRendimento;
    private TempoPermanencia tempoPermanencia;
    private AvaliacaoSite avaliacaoSite;
    private RegiaoRenda regiaoRenda;
    private ValorImovelIdade valorImovelIdade;
    private RegioesEscolhidas regioesEscolhidas;
    private ValorImovelRenda valorImovelRenda;
    private CepRegiaoEscolhida cepRegiaoEscolhida;
    private BancosEscolhidos bancosEscolhidos;

    public DashboardResponse(
            RendimentoMensal rendimentoMensal,
            PorcentualPerdas porcentualPerdas,
            ProjecaoRendimento projecaoRendimento,
            TempoPermanencia tempoPermanencia,
            AvaliacaoSite avaliacaoSite,
            RegiaoRenda regiaoRenda,
            ValorImovelIdade valorImovelIdade,
            RegioesEscolhidas regioesEscolhidas,
            ValorImovelRenda valorImovelRenda,
            CepRegiaoEscolhida cepRegiaoEscolhida,
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

    public RendimentoMensal getRendimentoMensal() {
        return rendimentoMensal;
    }

    public PorcentualPerdas getPorcentualPerdas() {
        return porcentualPerdas;
    }

    public ProjecaoRendimento getProjecaoRendimento() {
        return projecaoRendimento;
    }

    public TempoPermanencia getTempoPermanencia() {
        return tempoPermanencia;
    }

    public AvaliacaoSite getAvaliacaoSite() {
        return avaliacaoSite;
    }

    public RegiaoRenda getRegiaoRenda() {
        return regiaoRenda;
    }

    public ValorImovelIdade getValorImovelIdade() {
        return valorImovelIdade;
    }

    public RegioesEscolhidas getRegioesEscolhidas() {
        return regioesEscolhidas;
    }

    public ValorImovelRenda getValorImovelRenda() {
        return valorImovelRenda;
    }

    public CepRegiaoEscolhida getCepRegiaoEscolhida() {
        return cepRegiaoEscolhida;
    }

    public BancosEscolhidos getBancosEscolhidos() {
        return bancosEscolhidos;
    }
}
