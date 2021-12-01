package com.bandtec.br.finoban.dominio.requisicao;

import com.bandtec.br.finoban.http.HttpRequestFinoban;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.util.HashMap;

public class GetStatusHealthCkeckApiS16BankRequest extends HttpRequestFinoban {
    public GetStatusHealthCkeckApiS16BankRequest() {
        setHeaders(new HashMap<>()
        {{
            put("Content-Type", new MediaType(MediaType.APPLICATION_JSON));
        }});
        setHttpMethod(HttpMethod.GET);
        setUriRelative("http://localhost:5000/health-check");
    }
}
