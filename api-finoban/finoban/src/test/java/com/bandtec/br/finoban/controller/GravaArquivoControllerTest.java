package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.builder.DocumentoLayoutBuilder;
import com.bandtec.br.finoban.models.DocumentoLayout;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GravaArquivoControllerTest {

    @Autowired
    GravaArquivoController gravaArquivoController;

    @Test
    @DisplayName("/POST - Gravação de um arquivo txt - STATUS 201")
    void postRegistro() {
        DocumentoLayoutBuilder documentoLayoutBuilder = new DocumentoLayoutBuilder();
        DocumentoLayout documentoLayout = documentoLayoutBuilder.criarDocumentoLayout().getDocumentoLayout();
        ResponseEntity resposta = gravaArquivoController.postRegistro(documentoLayout);
        assertEquals(201, resposta.getStatusCodeValue());
    }
}