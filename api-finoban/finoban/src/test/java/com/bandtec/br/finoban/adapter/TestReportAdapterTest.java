package com.bandtec.br.finoban.adapter;

import com.bandtec.br.finoban.infraestrutura.adapters.TestReportAdapter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestReportAdapterTest {

    @Test
    void domainNameHandleSucessoController() {
        assertEquals("Controller", TestReportAdapter.domainNameHandle("controller.AcessoControllerTest.deleteAcessoNotOK"));
    }

    @Test
    void domainNameHandleSucessoService() {
        assertEquals("Service", TestReportAdapter.domainNameHandle("service.TokenServiceTest.decodeTokenNotOK"));
    }

    @Test
    void domainNameHandleSucessoHelper() {
        assertEquals("Helper", TestReportAdapter.domainNameHandle("helper.DateHelperTest.converterDataParaPadraoISOFuncionaDate"));
    }

    @Test
    void domainNameHandleSucessoAdapter() {
        assertEquals("Adapter", TestReportAdapter.domainNameHandle("adapter.blablabla.blablablabla"));
    }

    @Test
    void domainNameHandleSucessoSemDiretorioAntes() {
        assertEquals("", TestReportAdapter.domainNameHandle("FinobanApplicationTests.contextLoads"));
    }

    @Test
    void domainNameHandleFalhou() {
        assertEquals("", TestReportAdapter.domainNameHandle("dosajkndsanjdsasnkjdnkjwandkjwanjkdawjnkdwa"));
    }

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