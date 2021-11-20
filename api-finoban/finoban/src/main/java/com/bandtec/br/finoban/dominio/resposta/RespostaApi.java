package com.bandtec.br.finoban.dominio.resposta;

import com.bandtec.br.finoban.dominio.Data;
import org.springframework.stereotype.Service;

@Service
public class RespostaApi {

    private boolean ok;
    private Integer status;
    private Data data;

    public RespostaApi(boolean ok, Integer status, Data data) {
        this.ok = ok;
        this.status = status;
        this.data = data;
    }

    public RespostaApi() {
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "{" +
                "ok=" + ok +
                ", status=" + status +
                ", data=" + data +
                '}';
    }
}
