package com.bandtec.br.finoban.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RequestMapping("/api-finoban/download")
@RestController
public class DownloadController {

<<<<<<< HEAD
    @GetMapping(value = "/download-txt")
    public HttpEntity<byte[]> downloadTxt() throws IOException {
=======
    @GetMapping(value = "/txt")
    public HttpEntity<byte[]> download() throws IOException {
>>>>>>> 8ed70c4366f05cc60042fb143007c5f9fc9d1f6b

        byte[] arquivo = Files.readAllBytes( Paths.get("financiamento.txt") );

        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("Content-Disposition", "attachment;filename=\"financiamento.txt\"");

        HttpEntity<byte[]> entity = new HttpEntity<byte[]>( arquivo, httpHeaders);

        return entity;
    }

    @GetMapping(value = "/csv")
    public HttpEntity<byte[]> downloadCsv() throws IOException {

        byte[] arquivo = Files.readAllBytes( Paths.get("financiamento.csv") );

        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("Content-Disposition", "attachment;filename=\"financiamento.csv\"");

        HttpEntity<byte[]> entity = new HttpEntity<byte[]>( arquivo, httpHeaders);

        return entity;
    }

}
