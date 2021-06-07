package com.bandtec.br.finoban.builder;

import com.bandtec.br.finoban.models.DocumentoLayout;

public class DocumentoLayoutBuilder {
    private DocumentoLayout documentoLayout;

    public DocumentoLayoutBuilder() {
        this.documentoLayout = new DocumentoLayout();
    }

    public DocumentoLayoutBuilder criarDocumentoLayout() {
        this.documentoLayout.setNomeBanco("Banco Teste");
        this.documentoLayout.setNomeCliente("Cliente Teste");
        this.documentoLayout.setRegiao("Regiao Teste");
        this.documentoLayout.setTempoFinanciamento(15);
        this.documentoLayout.setValorFinanciamento(3_000_000);
        return this;
    }

    public DocumentoLayout getDocumentoLayout() {
        return documentoLayout;
    }
}
