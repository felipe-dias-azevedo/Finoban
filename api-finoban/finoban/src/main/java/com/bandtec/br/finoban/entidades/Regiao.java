package com.bandtec.br.finoban.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Regiao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRegiao;

    private String descricaoRegiao;

    private Integer valorRegiao;

    private LocalDateTime dataCraw;

    public Integer getIdRegiao() {
        return idRegiao;
    }

    public void setIdRegiao(Integer idRegiao) {
        this.idRegiao = idRegiao;
    }

    public String getDescricaoRegiao() {
        return descricaoRegiao;
    }

    public void setDescricaoRegiao(String descricaoRegiao) {
        this.descricaoRegiao = descricaoRegiao;
    }

    public Integer getValorRegiao() {
        return valorRegiao;
    }

    public void setValorRegiao(Integer valorRegiao) {
        this.valorRegiao = valorRegiao;
    }

    public LocalDateTime getDataCraw() {
        return dataCraw;
    }

    public void setDataCraw(LocalDateTime dataCraw) {
        this.dataCraw = dataCraw;
    }
}
