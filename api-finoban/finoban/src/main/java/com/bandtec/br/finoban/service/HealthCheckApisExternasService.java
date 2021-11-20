package com.bandtec.br.finoban.service;


import com.bandtec.br.finoban.controller.CadastroController;
import com.bandtec.br.finoban.dominio.StatusHealthCheckApisExternas;
import com.bandtec.br.finoban.infraestrutura.integration.ClienteApisExternasCifra;
import com.bandtec.br.finoban.infraestrutura.integration.ClienteApisExternasPresil;
import com.bandtec.br.finoban.infraestrutura.integration.ClienteApisExternasS16Bank;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HealthCheckApisExternasService {

    private ClienteApisExternasCifra clienteApisExternasCifra;
    private ClienteApisExternasS16Bank clienteApisExternasS16Bank;
    private ClienteApisExternasPresil clienteApisExternasPresil;
    private Gson gson;

    private static final Logger log = LogManager.getLogger(CadastroController.class.getName());

    public StatusHealthCheckApisExternas obterStatusHealthCheckApisExternas() {

        var statusHealthCkeckApisExternas = new StatusHealthCheckApisExternas();

        try {
            var respostaStatusHealthCheckApiCifra = this.clienteApisExternasCifra.getStatusCifra();
            statusHealthCkeckApisExternas.setStatusApiCifra(respostaStatusHealthCheckApiCifra.getStatusHealthCkeck());
            log.info("API Cifra - " + this.gson.toJson(respostaStatusHealthCheckApiCifra));
        } catch (Exception exception) {
            log.error("NOK API Cifra");
            statusHealthCkeckApisExternas.setStatusApiCifra("NOK");
        }

        try {
            var respostaStatusHealthCkeckApiPresil = this.clienteApisExternasPresil.getStatusPresil();
            statusHealthCkeckApisExternas.setStatusApiPresil(respostaStatusHealthCkeckApiPresil.getStatusHealthCkeck());
            log.info("API Presil - " + this.gson.toJson(respostaStatusHealthCkeckApiPresil));
        } catch (Exception exception) {
            log.error("NOK API Presil" + exception);
            statusHealthCkeckApisExternas.setStatusApiPresil("NOK");
        }

        try {
            var respostaStatusHealthCkeckS16Bank = this.clienteApisExternasS16Bank.getStatusS16Bank();
            statusHealthCkeckApisExternas.setStatusApi16Bank(respostaStatusHealthCkeckS16Bank.getStatusHealthCkeck());
            log.info("API s16 Bank - " +this.gson.toJson(respostaStatusHealthCkeckS16Bank));
        } catch (Exception exception) {
            log.error("NOK API S16 Bank");
            statusHealthCkeckApisExternas.setStatusApi16Bank("NOK");
        }

        return statusHealthCkeckApisExternas;
    }

}
