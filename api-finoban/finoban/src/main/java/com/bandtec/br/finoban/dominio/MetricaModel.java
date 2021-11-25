package com.bandtec.br.finoban.dominio;

import com.bandtec.br.finoban.dominio.entidades.Metricas;

import java.util.List;
import java.util.stream.Collectors;

public class MetricaModel {

    private int id;
    private UsuarioSimples usuario;
    private String nomeBanco;
    private double valorImovel;

    public MetricaModel(int id, UsuarioSimples usuario, String nomeBanco, double valorImovel) {
        this.id = id;
        this.usuario = usuario;
        this.nomeBanco = nomeBanco;
        this.valorImovel = valorImovel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UsuarioSimples getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioSimples usuario) {
        this.usuario = usuario;
    }

    public String getNomeBanco() {
        return nomeBanco;
    }

    public void setNomeBanco(String nomeBanco) {
        this.nomeBanco = nomeBanco;
    }

    public double getValorImovel() {
        return valorImovel;
    }

    public void setValorImovel(double valorImovel) {
        this.valorImovel = valorImovel;
    }
}
