package com.bandtec.br.finoban.dominio.resposta;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class SingleResponse<T> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<T> messages;

    public SingleResponse(T data, List<T> messages) {
        this.data = data;
        this.messages = messages;
    }

    public SingleResponse(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<T> getMessages() {
        return messages;
    }

    public void setMessages(List<T> messages) {
        this.messages = messages;
    }
}
