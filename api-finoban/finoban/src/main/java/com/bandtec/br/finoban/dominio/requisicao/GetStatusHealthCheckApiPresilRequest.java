package com.bandtec.br.finoban.dominio.requisicao;

import com.bandtec.br.finoban.http.HttpRequestFinoban;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.util.HashMap;

public class GetStatusHealthCheckApiPresilRequest extends HttpRequestFinoban {
    public GetStatusHealthCheckApiPresilRequest() {
        setHeaders(new HashMap<>()
        {{
            put("Content-Type", new MediaType(MediaType.APPLICATION_JSON));
        }});
        setHttpMethod(HttpMethod.GET);
        setUriRelative("http://localhost:3333/admin/health-check");
    }
}
