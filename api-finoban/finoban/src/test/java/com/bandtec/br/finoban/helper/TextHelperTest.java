package com.bandtec.br.finoban.helper;

import com.bandtec.br.finoban.infraestrutura.helpers.TextHelper;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TextHelperTest {

    @Test
    public void capitalizeSucessoTudoMinusculo() {
        assertEquals("Java", TextHelper.capitalize("java"));
    }

    @Test
    public void capitalizeSucessoTudoMaiusculo() {
        assertEquals("Java", TextHelper.capitalize("JAVA"));
    }

    @Test
    public void capitalizeSucessoJaCapitalizado() {
        assertEquals("Java", TextHelper.capitalize("Java"));
    }
}
