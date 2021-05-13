package com.bandtec.br.finoban.resposta;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class ResponseGeneric<T> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<T> messages;

    public ResponseGeneric(T data, List<T> messages) {
        this.data = data;
        this.messages = messages;
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
