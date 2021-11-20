package com.bandtec.br.finoban.dominio;

public class StatusHealthCheckApisExternas {

    private String statusApi16Bank;
    private String statusApiCifra;
    private String statusApiPresil;

    public StatusHealthCheckApisExternas(String statusApi16Bank, String statusApiCifra, String statusApiPresil) {
        this.statusApi16Bank = statusApi16Bank;
        this.statusApiCifra = statusApiCifra;
        this.statusApiPresil = statusApiPresil;
    }

    public StatusHealthCheckApisExternas() { }

    public String getStatusApi16Bank() {
        return statusApi16Bank;
    }

    public void setStatusApi16Bank(String statusApi16Bank) {
        this.statusApi16Bank = statusApi16Bank;
    }

    public String getStatusApiCifra() {
        return statusApiCifra;
    }

    public void setStatusApiCifra(String statusApiCifra) {
        this.statusApiCifra = statusApiCifra;
    }

    public String getStatusApiPresil() {
        return statusApiPresil;
    }

    public void setStatusApiPresil(String statusApiPresil) {
        this.statusApiPresil = statusApiPresil;
    }
}
