package com.bandtec.br.finoban.views.charts;

public class AvaliacaoSite {

    private Integer gostou = 0;
    private Integer naoGostou = 0;
    private Integer semFeedback = 0;

    public AvaliacaoSite() {
        this.gostou = 0;
        this.naoGostou = 0;
        this.semFeedback = 0;
    }

    public Integer getGostou() {
        return gostou;
    }

    public void setGostou(Integer gostou) {
        this.gostou = gostou;
    }

    public Integer getNaoGostou() {
        return naoGostou;
    }

    public void setNaoGostou(Integer naoGostou) {
        this.naoGostou = naoGostou;
    }

    public Integer getSemFeedback() {
        return semFeedback;
    }

    public void setSemFeedback(Integer semFeedback) {
        this.semFeedback = semFeedback;
    }
}
