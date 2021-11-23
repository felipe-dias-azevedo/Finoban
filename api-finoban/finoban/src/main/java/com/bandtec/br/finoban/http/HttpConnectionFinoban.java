package com.bandtec.br.finoban.http;

import com.bandtec.br.finoban.dominio.resposta.RespostaApi;
import com.bandtec.br.finoban.dominio.resposta.SingleResponse;
import com.bandtec.br.finoban.dominio.resposta.StatusHealthCkeck;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpMethod;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class HttpConnectionFinoban<T> {

    private HttpClient httpClient;
    private Gson json;
    private T content;

    public HttpConnectionFinoban() {
        this.httpClient = HttpClient.newBuilder().build();
        this.json = new Gson();
    }

    public T doRequest(HttpRequestFinoban httpRequestFinoban) throws Exception {

        HttpRequest httpRequest = null;
        HttpResponse<?> httpResponse = null;

        if (httpRequestFinoban.getHeaders() != null) {
            var chave = new String();
            var valor = new String();
            for (var header : httpRequestFinoban.getHeaders().entrySet()) {
                chave = header.getKey();
                valor = header.getValue().toString();
            }

            var uri = URI.create(httpRequestFinoban.getUriRelative());

            if (httpRequestFinoban.getHttpMethod() == HttpMethod.GET) {
                var httpClient = HttpClients.createDefault();
                var httpGet = new HttpGet(httpRequestFinoban.getUriRelative());
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
                return (T) this.json.fromJson(responseBody.toString(), StatusHealthCkeck.class);

            } else if (httpRequestFinoban.getHttpMethod() == HttpMethod.POST) {
                httpRequest = HttpRequest.newBuilder()
                        .uri(uri)
                        .header(chave, valor)
                        .POST(HttpRequest.BodyPublishers.ofString(httpRequestFinoban.getPayload()))
                        .build();
                httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            }
            else {
                throw new NotImplementedException();
            }
        }

        var fooType = new TypeToken<T>() {}.getType();
        return this.json.fromJson(httpResponse.body().toString(), fooType);
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
