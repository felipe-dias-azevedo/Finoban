package com.bandtec.br.finoban.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DownloadControllerTest {

    @Autowired
    DownloadController controller;

    @Test
    @DisplayName("Download de um arquivo csv - TAMANHO 195bytes")
    void downloadTxt() throws IOException {
        HttpEntity<byte[]> resposta = controller.downloadTxt();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "attachment;filename=\"financiamento.txt\"");
        assertTrue(resposta.getHeaders().equals(httpHeaders));
    }

    @Test
    @DisplayName("Download de um arquivo txt - TAMANHO 189bytes")
    void downloadCsv() throws IOException {
        HttpEntity<byte[]> resposta = controller.downloadCsv();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "attachment;filename=\"financiamento.csv\"");
        assertTrue(resposta.getHeaders().equals(httpHeaders));
    }
}