package com.bandtec.br.finoban.resposta;

import com.bandtec.br.finoban.models.Data;

public class RespostaApi {

    private boolean ok;
    private Integer status;
    private Data data;

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
}
