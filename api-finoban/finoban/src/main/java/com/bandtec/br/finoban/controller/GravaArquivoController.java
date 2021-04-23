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
            // o argumento true é para indicar que o arquivo não será sobrescrito e sim
            // gravado com append (no final do arquivo)
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

        String nomeArq = "Financiamento.txt";
        String header = "";
        String corpo = "";
        String trailer = "";
        int contRegDados = 0;

        // Monta o registro header
        Date dataDeHoje = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        header += "00FINANCIAMENTO";
        header += formatter.format(dataDeHoje);
        header += "01";

        // Grava o registro header
        gravaRegistro(nomeArq, header);

        // Monta o corpo

        // 1o registro de dados
        corpo = "02 ";
        corpo += "Banco ";
        // %-5s : para strings, usamos s
        //        - alinha para a esquerda, campo de 5 caracteres, completa
        //        espaços com brancos à direita
        corpo += String.format("%-11s", docLayout.getNomeBanco());
        corpo += String.format("%-90s", docLayout.getNomeCliente());  // aluno.getNome()
        corpo += String.format("%12d", docLayout.getValorFinanciamento());
        // %05.2f : para número com casas decimais, usamos f
        //          alinha para a direita, porque não tem o -
        //          completa com zeros à esquerda
        //          formata com 2 casas decimais, separado por vírgula
        corpo += String.format("%-20s", docLayout.getRegiao());
        // %03d : para número inteiro, usamos d
        //        alinha para a direita e completa com zeros à esquerda
        corpo += String.format("%2d", docLayout.getTempoFinanciamento());
        // incrementa o contador de registros de dados
        contRegDados++;
        // chama o método para gravar um registro
        gravaRegistro(nomeArq,corpo);

        // monta o trailer
        trailer += "01";
        trailer += String.format("%010d", contRegDados);
        gravaRegistro(nomeArq,trailer);
    }

}
