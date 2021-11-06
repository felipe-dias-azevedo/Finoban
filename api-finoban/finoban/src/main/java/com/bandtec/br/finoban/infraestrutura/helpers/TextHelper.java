package com.bandtec.br.finoban.infraestrutura.helpers;

public class TextHelper {

    /** Tornar a string capitalizada. Exemplo: "java" -> "Java" */
    public static String capitalize(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
    }
}
