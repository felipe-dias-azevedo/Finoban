package com.bandtec.br.finoban.infraestrutura.helpers;

public class NumberHelper {

    public static Double valueOf(long number) {
        return Double.longBitsToDouble(number);
    }

    public static Double valueOf(int number) {
        return (double) number;
    }

    public static Double round(Double number, Integer countsToRound) {
        double rounding = Math.pow(10, countsToRound);
        return Math.round(number * rounding) / rounding;
    }
}
