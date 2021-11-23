package com.bandtec.br.finoban.service;


import com.bandtec.br.finoban.controller.CadastroController;
import com.bandtec.br.finoban.dominio.StatusHealthCheckApisExternas;
import com.bandtec.br.finoban.dominio.requisicao.GetStatusHealthCheckApiPresilRequest;
import com.bandtec.br.finoban.dominio.requisicao.GetStatusHealthCkeckApiS16BankRequest;
import com.bandtec.br.finoban.dominio.resposta.SingleResponse;
import com.bandtec.br.finoban.dominio.resposta.StatusHealthCkeck;
import com.bandtec.br.finoban.http.HttpConnectionFinoban;
import com.bandtec.br.finoban.http.HttpRepository;
import com.bandtec.br.finoban.http.HttpRequestFinoban;
import com.bandtec.br.finoban.http.HttpResponseFinoban;
import com.bandtec.br.finoban.infraestrutura.integration.ClienteApisExternasCifra;
import com.bandtec.br.finoban.infraestrutura.integration.ClienteApisExternasPresil;
import com.bandtec.br.finoban.infraestrutura.integration.ClienteApisExternasS16Bank;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
public class HealthCheckApisExternasService extends HttpRepository<StatusHealthCheckApisExternas> {

    private ClienteApisExternasCifra clienteApisExternasCifra;

    private Gson gson;

    private static final Logger log = LogManager.getLogger(CadastroController.class.getName());

    public StatusHealthCheckApisExternas obterStatusHealthCheckApisExternas() {

        var statusHealthCkeckApisExternas = new StatusHealthCheckApisExternas();

        try {
            var respostaStatusHealthCheckApiCifra = this.clienteApisExternasCifra.getStatusCifra();
            statusHealthCkeckApisExternas.setStatusApiCifra(respostaStatusHealthCheckApiCifra.getStatusHealthCheck());
            log.info("API Cifra - " + this.gson.toJson(respostaStatusHealthCheckApiCifra));
        } catch (Exception exception) {
            log.error("NOK API Cifra");
            statusHealthCkeckApisExternas.setStatusApiCifra("NOK");
        }

        try {
            var respostaStatusHealthCkeckApiPresil = this.getStatusHealthCheckApiPresil();
            statusHealthCkeckApisExternas.setStatusApiPresil(respostaStatusHealthCkeckApiPresil.getStatusHealthCheck());
            log.info("API Presil - " + this.gson.toJson(respostaStatusHealthCkeckApiPresil));
        } catch (Exception exception) {
            log.error("NOK API Presil" + exception);
            statusHealthCkeckApisExternas.setStatusApiPresil("NOK");
        }

        try {
            var respostaStatusHealthCkeckS16Bank = this.getStatusHealthCheckApiS16Bank();
            statusHealthCkeckApisExternas.setStatusApi16Bank(respostaStatusHealthCkeckS16Bank.getStatusHealthCheck());
            log.info("API s16 Bank - " +this.gson.toJson(respostaStatusHealthCkeckS16Bank));
        } catch (Exception exception) {
            log.error("NOK API S16 Bank");
            statusHealthCkeckApisExternas.setStatusApi16Bank("NOK");
        }

        return statusHealthCkeckApisExternas;
    }

    public StatusHealthCkeck getStatusHealthCheckApiS16Bank() throws Exception {
        var httpClient = this.getHttpConnection().doRequest(new GetStatusHealthCkeckApiS16BankRequest());
        return (StatusHealthCkeck) httpClient;
    }

    public StatusHealthCkeck getStatusHealthCheckApiPresil() throws Exception {
        var httpClient = this.getHttpConnection().doRequest(new GetStatusHealthCheckApiPresilRequest());
        return (StatusHealthCkeck) httpClient;
    }
}
