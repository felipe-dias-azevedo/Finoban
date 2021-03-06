package com.bandtec.br.finoban.service;

import com.bandtec.br.finoban.controller.CadastroController;
import com.bandtec.br.finoban.dominio.StatusHealthCheckApisExternas;
import com.bandtec.br.finoban.dominio.requisicao.GetStatusHealthCheckApiCifraRequest;
import com.bandtec.br.finoban.dominio.requisicao.GetStatusHealthCheckApiPresilRequest;
import com.bandtec.br.finoban.dominio.requisicao.GetStatusHealthCkeckApiS16BankRequest;
import com.bandtec.br.finoban.dominio.resposta.StatusHealthCkeck;
import com.bandtec.br.finoban.http.HttpRepository;
import com.bandtec.br.finoban.infraestrutura.integration.ClienteApisExternasCifra;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class HealthCheckApisExternasService extends HttpRepository {

    private Gson gson;

    private static final Logger log = LogManager.getLogger(HealthCheckApisExternasService.class.getName());

    public StatusHealthCheckApisExternas obterStatusHealthCheckApisExternas() {

        var statusHealthCkeckApisExternas = new StatusHealthCheckApisExternas();

        try {
            var respostaStatusHealthCheckApiCifra = this.getStatusHealthCheckApiCifra();
            statusHealthCkeckApisExternas.setStatusApiCifra(respostaStatusHealthCheckApiCifra.getStatusHealthCheck());
            log.info("API Cifra - " + this.gson.toJson(respostaStatusHealthCheckApiCifra));
        } catch (Exception exception) {
            log.error("NOK API Cifra " + exception);
            statusHealthCkeckApisExternas.setStatusApiCifra("NOK");
        }

        try {
            var respostaStatusHealthCkeckApiPresil = this.getStatusHealthCheckApiPresil();
            statusHealthCkeckApisExternas.setStatusApiPresil(respostaStatusHealthCkeckApiPresil.getStatusHealthCheck());
            log.info("API Presil - " + this.gson.toJson(respostaStatusHealthCkeckApiPresil));
        } catch (Exception exception) {
            log.error("NOK API Presil " + exception);
            statusHealthCkeckApisExternas.setStatusApiPresil("NOK");
        }

        try {
            var respostaStatusHealthCkeckS16Bank = this.getStatusHealthCheckApiS16Bank();
            statusHealthCkeckApisExternas.setStatusApi16Bank(respostaStatusHealthCkeckS16Bank.getStatusHealthCheck());
            log.info("API s16 Bank - " +this.gson.toJson(respostaStatusHealthCkeckS16Bank));
        } catch (Exception exception) {
            log.error("NOK API S16 Bank " + exception);
            statusHealthCkeckApisExternas.setStatusApi16Bank("NOK");
        }

        return statusHealthCkeckApisExternas;
    }

    public StatusHealthCkeck getStatusHealthCheckApiCifra() throws Exception {
        var httpClient = this.getHttpConnection().doRequest(new GetStatusHealthCheckApiCifraRequest());
        log.info("Time request API Cifra Health Check: " + httpClient.getTimeRequestResponse());
        return this.gson.fromJson(httpClient.getContentResponse(), StatusHealthCkeck.class);
    }

    public StatusHealthCkeck getStatusHealthCheckApiS16Bank() throws Exception {
        var httpClient = this.getHttpConnection().doRequest(new GetStatusHealthCkeckApiS16BankRequest());
        log.info("Time request API S16 Bank Health Check: " + httpClient.getTimeRequestResponse());
        return this.gson.fromJson(httpClient.getContentResponse(), StatusHealthCkeck.class);
    }

    public StatusHealthCkeck getStatusHealthCheckApiPresil() throws Exception {
        var httpClient = this.getHttpConnection().doRequest(new GetStatusHealthCheckApiPresilRequest());
        log.info("Time request API Presil Health Check: " + httpClient.getTimeRequestResponse());
        return this.gson.fromJson(httpClient.getContentResponse(), StatusHealthCkeck.class);
    }
}
