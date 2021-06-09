package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.entidades.Regiao;
import com.bandtec.br.finoban.models.DocumentoLayout;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExportaArquivoControllerTest {

    @Autowired
    ExportaArquivoController controller;

    @Test
    void postArquivo() throws IOException {
        File file = new File("documento-layout.txt");
        FileInputStream input = new FileInputStream(file);
        byte[] byteArray = IOUtils.toByteArray(input);
        MultipartFile multipartFile = new MockMultipartFile("documento-layout.txt",
                file.getName(), "text/plain", byteArray);
        ResponseEntity<DocumentoLayout> resposta = controller.postArquivo(multipartFile);
        assertEquals(201, resposta.getStatusCodeValue());
        assertEquals("Banco Cifra", resposta.getBody().getNomeBanco());
    }
}