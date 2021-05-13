package com.bandtec.br.finoban.enums;

import java.text.Normalizer;
import java.util.Locale;

public enum AvalPositivoEnum {
    GOSTOU(0, "Gostou"),
    NAO_GOSTOU(1, "Não gostou"),
    NAO_DEU_FEEDBACK(2, "Não deu feedback");


    AvalPositivoEnum(int indice, String avaliacao) {
    }

    public static AvalPositivoEnum getAvaliacaoEnum(int indice) {
        switch (indice) {
            case 0:
                return GOSTOU;
            case 1:
                return NAO_GOSTOU;
            case 2:
                return NAO_DEU_FEEDBACK;
            default:
                return null;
        }
    }

    public static AvalPositivoEnum getAvaliacaoEnum(String avaliacao) {
        switch (tirarAcentos(avaliacao.toLowerCase(Locale.ROOT))) {
            case "gostou":
                return GOSTOU;
            case "nao gostou":
                return NAO_GOSTOU;
            case "nao deu feedback":
                return NAO_DEU_FEEDBACK;
            default:
                return null;
        }
    }


    public static String tirarAcentos(String texto) {
        return Normalizer.normalize(texto, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }
}
