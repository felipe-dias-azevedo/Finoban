package com.bandtec.br.finoban.dominio.requisicao;

import com.bandtec.br.finoban.http.HttpRequestFinoban;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.util.HashMap;

public class GetAllRegioesFinobanRequest extends HttpRequestFinoban {
    public GetAllRegioesFinobanRequest() {
        setHttpMethod(HttpMethod.GET);
        setUriRelative("http://localhost:8080/api-finoban/regioes");
        setHeaders(new HashMap<>()
        {{
            put("Content-Type", new MediaType(MediaType.APPLICATION_JSON));
        }});
    }
}
