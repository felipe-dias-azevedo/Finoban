package com.bandtec.br.finoban.models;

import org.apache.tomcat.jni.Local;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Acesso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime dataHoraEntrada;
    private Integer statusSaida;
    private LocalDateTime dataHoraSaida;
    private Integer paginaSaida;
    private Integer fkSaida;
    private Integer fkCliente;

    public Acesso(LocalDateTime dataHoraEntrada, Integer statusSaida, LocalDateTime dataHoraSaida,
                     Integer paginaSaida, Integer fkSaida, Integer fkCliente) {
        this.dataHoraEntrada = dataHoraEntrada;
        this.statusSaida = statusSaida;
        this.dataHoraSaida = dataHoraSaida;
        this.paginaSaida = paginaSaida;
        this.fkSaida = fkSaida;
        this.fkCliente = fkCliente;
    }

    public LocalDateTime getDataHoraEntrada() {
        return dataHoraEntrada;
    }

    public void setDataHoraEntrada(LocalDateTime dataHoraEntrada) {
        this.dataHoraEntrada = dataHoraEntrada;
    }

    public Integer getStatusSaida() {
        return statusSaida;
    }

    public void setStatusSaida(Integer statusSaida) {
        this.statusSaida = statusSaida;
    }

    public LocalDateTime getDataHoraSaida() {
        return dataHoraSaida;
    }

    public void setDataHoraSaida(LocalDateTime dataHoraSaida) {
        this.dataHoraSaida = dataHoraSaida;
    }

    public Integer getPaginaSaida() {
        return paginaSaida;
    }

    public void setPaginaSaida(Integer paginaSaida) {
        this.paginaSaida = paginaSaida;
    }

    public Integer getFkSaida() {
        return fkSaida;
    }

    public void setFkSaida(Integer fkSaida) {
        this.fkSaida = fkSaida;
    }

    public Integer getFkCliente() {
        return fkCliente;
    }

    public void setFkCliente(Integer fkCliente) {
        this.fkCliente = fkCliente;
    }
}
