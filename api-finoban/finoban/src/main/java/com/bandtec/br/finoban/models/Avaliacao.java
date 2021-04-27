package com.bandtec.br.finoban.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Avaliacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer avalPositivo;
    private String feedbackAval;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataAval;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
//    @JoinColumn(name = "fk_cliente", nullable = false)
//    private Cadastro cadastro;

    public Avaliacao(Integer avalPositivo, String feedbackAval, LocalDateTime dataAval) {
        this.avalPositivo = avalPositivo;
        this.feedbackAval = feedbackAval;
        this.dataAval = dataAval;
    }

    public Avaliacao() {

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
}
