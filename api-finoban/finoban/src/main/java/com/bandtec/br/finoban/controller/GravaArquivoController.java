package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.models.DocumentoLayout;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

import java.util.Date;


@RestController
@RequestMapping("/api-finoban/gravar")
public class GravaArquivoController {


    @PostMapping("/txt")
    public ResponseEntity postRegistro(@RequestBody DocumentoLayout documentoLayout) {
        try {
            gravarResgistrorApi(documentoLayout);
            return ResponseEntity.status(201).build();
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(ex.getMessage());
        }
    }

    public static void gravaRegistro (String nomeArq, String registro) {
        BufferedWriter saida = null;
        try {
            saida = new BufferedWriter(new FileWriter(nomeArq, true));
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }

        try {
            saida.append(registro + "\n");
            saida.close();

        } catch (IOException e) {
            System.err.printf("Erro ao gravar arquivo: %s.\n", e.getMessage());
        }
    }


    public static void gravarResgistrorApi(DocumentoLayout documentoLayout) {

        String nomeArq = "financiamento.txt";
        String header = "";
        String corpo = "";
        String trailer = "";
        int contRegDados = 0;

        Date dataDeHoje = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        header += "00FINANCIAMENTO";
        header += formatter.format(dataDeHoje);
        header += "01";

        gravaRegistro(nomeArq, header);

        corpo = "02 ";
        corpo += "Banco ";
        corpo += String.format("%-11s", documentoLayout.getNomeBanco());
        corpo += String.format("%-90s", documentoLayout.getNomeCliente());
        corpo += String.format("%12d", documentoLayout.getValorFinanciamento());
        corpo += String.format("%-20s", documentoLayout.getRegiao());
        corpo += String.format("%2d", documentoLayout.getTempoFinanciamento());
        contRegDados++;
        gravaRegistro(nomeArq,corpo);

        trailer += "01";
        trailer += String.format("%010d", contRegDados);
        gravaRegistro(nomeArq,trailer);
    }
}
