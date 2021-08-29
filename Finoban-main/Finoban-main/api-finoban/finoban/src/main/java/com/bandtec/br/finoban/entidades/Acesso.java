package com.bandtec.br.finoban.entidades;

import com.bandtec.br.finoban.enums.BancoEscolhidoEnum;
import com.bandtec.br.finoban.enums.PaginaSaidaEnum;
import com.bandtec.br.finoban.enums.StatusSaidaEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "acesso")
public class Acesso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEntrada;
    private LocalDateTime dataHoraEntrada;
    private LocalDateTime dataHoraSaida;
    private StatusSaidaEnum statusSaida;
    private PaginaSaidaEnum paginaSaida;
    private double renda;
    private double valorImovel;
    private Integer tempoFinanciamento;
    private int porcentagemRenda;
    private BancoEscolhidoEnum bancoEscolhido;

    @ManyToOne
    @JoinColumn(name = "fkRegiao")
    private Regiao fkRegiao;
    @ManyToOne
    @JoinColumn(name = "fkCliente")
    private Usuario fkCliente;


    public Acesso() {

    }

    public Integer getIdEntrada() {
        return idEntrada;
    }

    public void setIdEntrada(Integer idEntrada) {
        this.idEntrada = idEntrada;
    }

    public LocalDateTime getDataHoraEntrada() {
        return dataHoraEntrada;
    }

    public void setDataHoraEntrada(LocalDateTime dataHoraEntrada) {
        this.dataHoraEntrada = dataHoraEntrada;
    }

    public LocalDateTime getDataHoraSaida() {
        return dataHoraSaida;
    }

    public void setDataHoraSaida(LocalDateTime dataHoraSaida) {
        this.dataHoraSaida = dataHoraSaida;
    }

    public StatusSaidaEnum getStatusSaida() {
        return statusSaida;
    }

    public void setStatusSaida(Object statusSaida) {
        if (statusSaida.getClass().equals(String.class)) {
            this.statusSaida = StatusSaidaEnum.getConfirmouContratacao((String) statusSaida);
        } else {
            this.statusSaida = StatusSaidaEnum.getConfirmouContratacao((int) statusSaida);
        }
    }

    public PaginaSaidaEnum getPaginaSaida() {
        return paginaSaida;
    }

    public void setPaginaSaida(Object paginaSaida) {
        if (paginaSaida.getClass().equals(String.class)) {
            this.paginaSaida = PaginaSaidaEnum.getPaginaSaida((String) paginaSaida);
        } else {
            this.paginaSaida = PaginaSaidaEnum.getPaginaSaida((int) paginaSaida);
        }
    }

    public double getRenda() {
        return renda;
    }

    public void setRenda(double renda) {
        this.renda = renda;
    }

    public double getValorImovel() {
        return valorImovel;
    }

    public void setValorImovel(double valorImovel) {
        this.valorImovel = valorImovel;
    }

    public Integer getTempoFinanciamento() {
        return tempoFinanciamento;
    }

    public void setTempoFinanciamento(Integer tempoFinanciamento) {
        this.tempoFinanciamento = tempoFinanciamento;
    }

    public int getPorcentagemRenda() {
        return porcentagemRenda;
    }

    public void setPorcentagemRenda(int porcentagemRenda) {
        this.porcentagemRenda = porcentagemRenda;
    }

    public BancoEscolhidoEnum getBancoEscolhido() {
        return bancoEscolhido;
    }

    public void setBancoEscolhido(Object bancoEscolhido) {
        if (bancoEscolhido.getClass().equals(String.class)) {
            this.bancoEscolhido = BancoEscolhidoEnum.getBancoEscolhido((String) bancoEscolhido);
        } else {
            this.bancoEscolhido = BancoEscolhidoEnum.getBancoEscolhido((int) bancoEscolhido);
        }
    }

    public Regiao getFkRegiao() {
        return fkRegiao;
    }

    public void setFkRegiao(Regiao fkRegiao) {
        this.fkRegiao = fkRegiao;
    }

    public Usuario getFkCliente() {
        return fkCliente;
    }

    public void setFkCliente(Usuario fkCliente) {
        this.fkCliente = fkCliente;
    }
}
