package com.bandtec.br.finoban.enums;

import java.util.Locale;

public enum PaginaSaidaEnum {
    HOME(0, "Pagina Inicial"),
    LOGIN(1, "Login"),
    CADASTRO(2,"Cadastro"),
    SIMULADOR(3,"Simulador"),
    VISUALIZACAO(4, "Visualização dos dados");


    PaginaSaidaEnum(int indice, String pagina) {
    }

    public static PaginaSaidaEnum getPaginaSaida(String pagina) {
        switch (pagina.toLowerCase(Locale.ROOT)) {
            case "pagina inicial":
                return HOME;
            case "login":
                return LOGIN;
            case "cadastro":
                return CADASTRO;
            case "simulador":
                return SIMULADOR;
            case "visualização dos dados":
                return VISUALIZACAO;
            default:
                return null;
        }
    }
    public static PaginaSaidaEnum getPaginaSaida(int indice) {
        switch (indice) {
            case 0:
                return HOME;
            case 1:
                return LOGIN;
            case 2:
                return CADASTRO;
            case 3:
                return SIMULADOR;
            case 4:
                return VISUALIZACAO;
            default:
                return null;
        }
    }
}
