package com.bandtec.br.finoban.enums;

import java.util.Locale;

public enum BancoEscolhidoEnum {
    BANCO_CIFRA(0,"Banco Cifra"),
    BANCO_S16_BANK(1,"Banco s16 Bank"),
    BANCO_DO_PRESIL(2,"Banco do Presil");

    BancoEscolhidoEnum(int indice, String nomebanco) {
        this.indice = indice;
        this.nomebanco = nomebanco;
    }

    private Integer indice;
    private String nomebanco;

    public Integer getIndice() {
        return indice;
    }

    public void setIndice(Integer indice) {
        this.indice = indice;
    }

    public String getNomebanco() {
        return nomebanco;
    }

    public void setNomebanco(String nomebanco) {
        this.nomebanco = nomebanco;
    }

    public static BancoEscolhidoEnum getBancoEscolhido(String bancoEscolhido) {
        switch (bancoEscolhido.toLowerCase(Locale.ROOT)) {
            case "banco cifra":
                return BANCO_CIFRA;
            case "banco s16 bank":
                return BANCO_S16_BANK;
            case "banco do presil":
                return BANCO_DO_PRESIL;
            default:
                return null;
        }
    }

    public static BancoEscolhidoEnum getBancoEscolhido(int indice) {
        switch (indice){
            case 0:
                return BANCO_CIFRA;
            case 1:
                return BANCO_S16_BANK;
            case 2:
                return BANCO_DO_PRESIL;
            default:
                return null;
        }
    }
}
