package com.bandtec.br.finoban.http;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.util.Map;

public class HttpRequestFinoban {

    private String uriRelative;
    private Map<String, MediaType> headers;
    private String payload;
    private HttpMethod httpMethod;

    public HttpRequestFinoban(String uriRelative, Map<String, MediaType> headers, String payload, HttpMethod httpMethod) {
        this.uriRelative = uriRelative;
        this.headers = headers;
        this.payload = payload;
        this.httpMethod = httpMethod;
    }

    public HttpRequestFinoban() { }

    public String getUriRelative() {
        return uriRelative;
    }

    public void setUriRelative(String uriRelative) {
        this.uriRelative = uriRelative;
    }

    public Map<String, MediaType> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, MediaType> headers) {
        this.headers = headers;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }
}
