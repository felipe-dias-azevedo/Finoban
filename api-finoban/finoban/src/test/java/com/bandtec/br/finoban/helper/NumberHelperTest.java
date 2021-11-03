package com.bandtec.br.finoban.helper;

import com.bandtec.br.finoban.infraestrutura.helpers.NumberHelper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NumberHelperTest {

    @Test
    public void roundNumberSucessoDuasCasasDecimais() {
        assertEquals(37.40, NumberHelper.round(37.40321321312, 2));
    }

    @Test
    public void roundNumberSucessoUmaCasaDecimal() {
        assertEquals(37.4, NumberHelper.round(37.40321321312, 1));
    }

    @Test
    public void roundNumberSucessoZeroCasasDecimais() {
        assertEquals(37, NumberHelper.round(37.40321321312, 0));
    }
}
