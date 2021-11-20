package com.bandtec.br.finoban.repository;

import com.bandtec.br.finoban.dominio.resposta.StatusHealthCkeck;

import java.io.IOException;

public interface StatusHealthCheckApisExternas {

    StatusHealthCkeck getStatusHealthCheckApiS16Bank() throws IOException;
    StatusHealthCkeck getStatusHealthCheckApiPresil() throws IOException;

}
