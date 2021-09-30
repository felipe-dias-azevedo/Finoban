package com.bandtec.br.finoban.dominio.entidades;

import com.bandtec.br.finoban.dominio.enums.AvalPositivoEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "avaliacao")
public class Avaliacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAvaliacao;
    private AvalPositivoEnum avalPositivo; // enum

    private String feedbackAval;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataAval;

    @OneToOne
    @JoinColumn(name = "fk_acesso")
    private Acesso fkAcesso;

    public Integer getIdAvaliacao() {
        return idAvaliacao;
    }

    public void setIdAvaliacao(Integer idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public AvalPositivoEnum getAvalPositivo() {
        return avalPositivo;
    }

    public void setAvalPositivo(Object avalPositivo) {
        if (avalPositivo.getClass().equals(String.class)) {
            this.avalPositivo = AvalPositivoEnum.getAvaliacaoEnum((String) avalPositivo);
        } else {
            this.avalPositivo = AvalPositivoEnum.getAvaliacaoEnum((int) avalPositivo);
        }
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

    public Acesso getFkAcesso() {
        return fkAcesso;
    }

    public void setFkAcesso(Acesso fkAcesso) {
        this.fkAcesso = fkAcesso;
    }

}
