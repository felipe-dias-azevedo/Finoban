package com.bandtec.br.finoban.dominio.requisicao;

import com.bandtec.br.finoban.http.HttpRequestFinoban;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.util.HashMap;

public class GetStatusHealthCheckApiCifraRequest extends HttpRequestFinoban {
    public GetStatusHealthCheckApiCifraRequest() {
        setHeaders(new HashMap<>()
        {{
            put("Content-Type", new MediaType(MediaType.APPLICATION_JSON));
        }});
        setHttpMethod(HttpMethod.GET);
        setUriRelative("http://localhost:8008/health-check");
    }
}
