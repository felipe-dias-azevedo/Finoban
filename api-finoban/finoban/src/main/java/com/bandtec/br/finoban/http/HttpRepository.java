package com.bandtec.br.finoban.http;

import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

public class HttpRepository<T> {

    private HttpConnectionFinoban<T> httpConnection;

    public HttpRepository() {
        this.httpConnection = new HttpConnectionFinoban<T>();
    }

    public T doRequest(HttpRequestFinoban httpRequest) throws Exception {
        return this.httpConnection.doRequest(httpRequest);
    }

    public HttpConnectionFinoban getHttpConnection() {
        return httpConnection;
    }

    public void setHttpConnection(HttpConnectionFinoban httpConnection) {
        this.httpConnection = httpConnection;
    }
}
