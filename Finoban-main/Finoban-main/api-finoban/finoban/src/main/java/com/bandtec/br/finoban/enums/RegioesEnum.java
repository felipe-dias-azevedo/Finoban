package com.bandtec.br.finoban.enums;

import java.text.Normalizer;
import java.util.Locale;

public enum RegioesEnum {

    CENTRO(0, "Centro"),
    CONSOLACAO(1, "Consolação"),
    BROOKLIN(2, "Brooklin"),
    MOOCA(3, "Mooca"),
    SANTO_AMARO(4, "Santo Amaro"),
    INTERLAGOS(5, "Interlagos");

    RegioesEnum(Integer indice, String descricao) {
        this.indice = indice;
        this.descricao = descricao;
    }

    private Integer indice;
    private String descricao;

    public Integer getIndice() {
        return indice;
    }

    public void setIndice(Integer indice) {
        this.indice = indice;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public static RegioesEnum getAvaliacaoEnum(int indice) {
        switch (indice) {
            case 0:
                return CENTRO;
            case 1:
                return CONSOLACAO;
            case 2:
                return BROOKLIN;
            case 3:
                return MOOCA;
            case 4:
                return SANTO_AMARO;
            case 5:
                return INTERLAGOS;
            default:
                return null;
        }
    }

    public static RegioesEnum getAvaliacaoEnum(String avaliacao) {
        switch (tirarAcentos(avaliacao.toLowerCase(Locale.ROOT))) {
            case "centro":
                return CENTRO;
            case "consolacao":
                return CONSOLACAO;
            case "brooklin":
                return BROOKLIN;
            case "mooca":
                return MOOCA;
            case "santo amaro":
                return SANTO_AMARO;
            case "interlagos":
                return INTERLAGOS;
            default:
                return null;
        }
    }

    public static String tirarAcentos(String texto) {
        return Normalizer.normalize(texto, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }
}
