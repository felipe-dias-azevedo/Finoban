package com.bandtec.br.finoban.adapter;

import com.bandtec.br.finoban.infraestrutura.adapters.TestReportAdapter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestReportAdapterTest {

    @Test
    void classNameHandleSucesso() {
        assertEquals("AcessoController", TestReportAdapter.classNameHandle("controller.AcessoControllerTest.deleteAcessoNotOK"));
    }

    @Test
    void classNameHandleSucessoSemDiretorioAntes() {
        assertEquals("FinobanApplications", TestReportAdapter.classNameHandle("FinobanApplicationTests.contextLoads"));
    }

    @Test
    void classNameHandleFalhou() {
        assertEquals("", TestReportAdapter.classNameHandle("dosajkndsanjdsasnkjdnkjwandkjwanjkdawjnkdwa"));
    }
}