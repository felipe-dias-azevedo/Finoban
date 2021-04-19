package com.bandtec.br.finoban.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.tomcat.jni.Local;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Entity
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer avalPositivo;
    private String feedbackAval;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataAval;
    private Integer fkAcesso;

    public Avaliacao(Integer id, Integer avalPositivo, String feedbackAval, LocalDateTime dataAval, Integer fkAcesso) {
        this.id = id;
        this.avalPositivo = avalPositivo;
        this.feedbackAval = feedbackAval;
        this.dataAval = dataAval;
        this.fkAcesso = fkAcesso;
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

    public Integer getFkAcesso() {
        return fkAcesso;
    }

    public void setFkAcesso(Integer fkAcesso) {
        this.fkAcesso = fkAcesso;
    }
}
