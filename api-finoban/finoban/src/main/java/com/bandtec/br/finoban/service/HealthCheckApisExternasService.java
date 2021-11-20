package com.bandtec.br.finoban.service;


import com.bandtec.br.finoban.controller.CadastroController;
import com.bandtec.br.finoban.dominio.StatusHealthCheckApisExternas;
import com.bandtec.br.finoban.dominio.resposta.SingleResponse;
import com.bandtec.br.finoban.dominio.resposta.StatusHealthCkeck;
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
public class HealthCheckApisExternasService implements com.bandtec.br.finoban.repository.StatusHealthCheckApisExternas {

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

    @Override
    public StatusHealthCkeck getStatusHealthCheckApiS16Bank() throws IOException {
        var httpClient = HttpClients.createDefault();
        var httpGet = new HttpGet("http://localhost:5000/health-check");
        ResponseHandler responseHandler = response -> {
            if (response.getStatusLine().getStatusCode() == 200) {
                var entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            } else {
                throw new ClientProtocolException("Unexpected response status: " + response
                        .getStatusLine()
                        .getStatusCode());
            }
        };
        var responseBody = httpClient.execute(httpGet, responseHandler);
        return this.gson.fromJson(responseBody.toString(), StatusHealthCkeck.class);
    }

    @Override
    public StatusHealthCkeck getStatusHealthCheckApiPresil() throws IOException {
        var httpClient = HttpClients.createDefault();
        var httpGet = new HttpGet("http://localhost:3333/admin/health-check");
        ResponseHandler responseHandler = response -> {
            if (response.getStatusLine().getStatusCode() == 200) {
                var entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            } else {
                throw new ClientProtocolException("Unexpected response status: " + response
                        .getStatusLine()
                        .getStatusCode());
            }
        };
        var responseBody = httpClient.execute(httpGet, responseHandler);
        return this.gson.fromJson(responseBody.toString(), StatusHealthCkeck.class);
    }
}
