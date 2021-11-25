package com.bandtec.br.finoban.dominio.requisicao;

import com.bandtec.br.finoban.http.HttpRequestFinoban;
import com.google.gson.Gson;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.util.HashMap;

public class FinanciamentoFinobanRequest extends HttpRequestFinoban {

    private Gson gson;

    public FinanciamentoFinobanRequest(BancoRequisicaoModel bancoRequisicaoModel) {
        this.gson = new Gson();
        setPayload(this.gson.toJson(bancoRequisicaoModel));
        setHttpMethod(HttpMethod.POST);
        setUriRelative("http://localhost:8080/api-finoban/financiamento");
        setHeaders(new HashMap<>()
        {{
            put("Content-Type", new MediaType(MediaType.APPLICATION_JSON));
        }});
    }
}
