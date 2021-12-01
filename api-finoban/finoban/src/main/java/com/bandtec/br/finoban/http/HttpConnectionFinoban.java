package com.bandtec.br.finoban.http;

import com.google.gson.Gson;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpMethod;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class HttpConnectionFinoban {

    private HttpClient httpClient;

    public HttpConnectionFinoban() {
        this.httpClient = HttpClient.newBuilder().build();
    }

    public HttpResponseFinoban doRequest(HttpRequestFinoban httpRequestFinoban) throws Exception {

        Instant start = Instant.now();
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

                var httpClientRequest = HttpClients.createDefault();
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

                var responseBody = httpClientRequest.execute(httpGet, responseHandler);
                Instant end = Instant.now();
                return new HttpResponseFinoban(responseBody.toString(), Duration.between(start, end));

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

        Instant end = Instant.now();
        return new HttpResponseFinoban(httpResponse.body().toString(), Duration.between(start, end));
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }
}
