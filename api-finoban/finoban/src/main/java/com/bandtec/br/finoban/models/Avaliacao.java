package com.bandtec.br.finoban.models;

import com.bandtec.br.finoban.demo.Cadastro;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Entity
public class Avaliacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer avalPositivo;
    private String feedbackAval;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataAval;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_cliente", nullable = false)
    private Cadastro cadastro;

    public Avaliacao(Integer id, Integer avalPositivo, String feedbackAval, LocalDateTime dataAval, Cadastro cadastro) {
        this.id = id;
        this.avalPositivo = avalPositivo;
        this.feedbackAval = feedbackAval;
        this.dataAval = dataAval;
        this.cadastro = cadastro;
    }

    public Avaliacao(Avaliacao novaAvaliacao, Cadastro cadastro) {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAvalPositivo() {
        return avalPositivo;
    }

    public void setAvalPositivo(Integer avalPositivo) {
        this.avalPositivo = avalPositivo;
    }

    public String getFeedbackAval() {
        return feedbackAval;
    }

    public void setFeedbackAval(String feedbackAval) {
        this.feedbackAval = feedbackAval;
    }

    public LocalDateTime getDataAval() {
        return dataAval;
    }

    public void setDataAval(LocalDateTime dataAval) {
        this.dataAval = dataAval;
    }

    public Cadastro getCadastro() {
        return cadastro;
    }

    public void setCadastro(Cadastro cadastro) {
        this.cadastro = cadastro;
    }
}
