package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.models.DocumentoLayout;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

import java.util.Date;

public class GravaArquivoController {

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

    public static void main(String[] args) {

        DocumentoLayout docLayout = new DocumentoLayout("Presil", "Maria José",
                600000, "São Paulo", 30);

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
        corpo += String.format("%-11s", docLayout.getNomeBanco());
        corpo += String.format("%-90s", docLayout.getNomeCliente());
        corpo += String.format("%12d", docLayout.getValorFinanciamento());
        corpo += String.format("%-20s", docLayout.getRegiao());
        corpo += String.format("%2d", docLayout.getTempoFinanciamento());
        contRegDados++;
        gravaRegistro(nomeArq,corpo);

        trailer += "01";
        trailer += String.format("%010d", contRegDados);
        gravaRegistro(nomeArq,trailer);
    }

}
