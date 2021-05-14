package com.bandtec.br.finoban.enums;

import org.apache.commons.lang3.NotImplementedException;

import java.text.Normalizer;
import java.util.Locale;

public enum StatusSaidaEnum {
    CONFIRMOU_CONTRATACAO(0, "Confirmou contratação"),
    NAO_CONFIRMOU(1, "Não confirmou");


    StatusSaidaEnum(int indice, String status) {
    }

    public static StatusSaidaEnum getConfirmouContratacao(int indice) {
        switch (indice) {
            case 0:
                return CONFIRMOU_CONTRATACAO;
            case 1:
                return NAO_CONFIRMOU;
            default:
                return null;
        }
    }

    public static StatusSaidaEnum getConfirmouContratacao(String status) {
        switch (tirarAcentos(status.toLowerCase(Locale.ROOT))) {
            case "confirmou contratacao":
                return CONFIRMOU_CONTRATACAO;
            case "nao confirmou":
                return NAO_CONFIRMOU;
            default:
                return null;
        }
    }

    public static String tirarAcentos(String texto) {
        return Normalizer.normalize(texto, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }
}
