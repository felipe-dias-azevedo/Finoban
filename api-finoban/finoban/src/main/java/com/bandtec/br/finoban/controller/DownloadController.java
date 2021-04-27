package com.bandtec.br.finoban.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RequestMapping("/api-finoban")
@RestController
public class DownloadController {

    @GetMapping(value = "/download-txt")
    public HttpEntity<byte[]> download() throws IOException {

        byte[] arquivo = Files.readAllBytes( Paths.get("target\\classes\\static\\financiamento.txt") );

        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("Content-Disposition", "attachment;filename=\"financiamento.txt\"");

        HttpEntity<byte[]> entity = new HttpEntity<byte[]>( arquivo, httpHeaders);

        return entity;
    }

    @GetMapping(value = "/download-csv")
    public HttpEntity<byte[]> downloadCsv() throws IOException {

        byte[] arquivo = Files.readAllBytes( Paths.get("target\\classes\\static\\financiamento.csv") );

        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("Content-Disposition", "attachment;filename=\"financiamento.csv\"");

        HttpEntity<byte[]> entity = new HttpEntity<byte[]>( arquivo, httpHeaders);

        return entity;
    }

}
