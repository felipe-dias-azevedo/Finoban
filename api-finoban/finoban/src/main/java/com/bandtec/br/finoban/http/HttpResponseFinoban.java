package com.bandtec.br.finoban.http;

import java.time.Duration;

public class HttpResponseFinoban {

    private String contentResponse;
    private Duration durationRequest;

    public HttpResponseFinoban(String contentResponse, Duration durationRequest) {
        this.contentResponse = contentResponse;
        this.durationRequest = durationRequest;
    }

    public HttpResponseFinoban() { }

    public String getContentResponse() {
        return contentResponse;
    }

    public void setContentResponse(String contentResponse) {
        this.contentResponse = contentResponse;
    }

    public Duration getTimeRequestResponse() {
        return durationRequest;
    }

    public void setTimeRequestResponse(Duration timeRequestResponse) {
        this.durationRequest = timeRequestResponse;
    }

}
