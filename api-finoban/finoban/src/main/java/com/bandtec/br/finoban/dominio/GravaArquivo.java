package com.bandtec.br.finoban.dominio;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class GravaArquivo {

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
        // Aux
        Integer opt = 0;
        Scanner scan = new Scanner(System.in);
        Scanner scanString = new Scanner(System.in);
        Scanner scanString2 = new Scanner(System.in);

        // Arquivo
        String nomeArq = "docLayout.txt";
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

        gravaRegistro(nomeArq, header);

        // Menu
        do {
            System.out.print("" +
                    "1. Adicionar Região\n" +
                    "2. Adicionar Cliente\n" +
                    "3. Sair\n" +
                    "Opção desejada: ");
            opt = scan.nextInt();

            switch (opt) {
                case 1:
                    String descricao;
                    int valor;

                    System.out.print("Por favor, digite o nome da região: ");
                    descricao = scanString.nextLine();
                    System.out.print("Por favor, digite o valor médio dos imóveis da Região: ");
                    valor = scan.nextInt();

                    corpo = "02";
                    corpo += String.format("%-17s", descricao);
                    corpo += String.format("%010d", valor);
                    contRegDados++;
                    gravaRegistro(nomeArq, corpo);
                    break;
                case 2:
                    String cnpj;
                    String nome;
                    String email;
                    String senha;
                    String cep;
                    Integer numero;
                    String dataNascimento;
                    String bairro;

                    System.out.print("Por favor, digite o CNPJ do cliente: ");
                    cnpj = scanString2.next();
                    System.out.print("Por favor, digite o nome do cliente: ");
                    nome = scanString.nextLine();
                    System.out.print("Por favor, digite o email do cliente: ");
                    email = scanString2.next();
                    System.out.print("Por favor, digite a senha do cliente: ");
                    senha = scanString2.next();
                    System.out.print("Por favor, digite o cep do cliente: ");
                    cep = scanString2.next();
                    System.out.print("Por favor, digite o número residencial do cliente: ");
                    numero = scan.nextInt();
                    System.out.print("Por favor, digite a data de nascimento do cliente(Formato: yyyy-mm-dd): ");
                    dataNascimento = scanString2.next();
                    System.out.print("Por favor, digite o bairro do cliente: ");
                    bairro = scanString.nextLine();

                    corpo = "03";
                    corpo += String.format("%14s", cnpj);
                    corpo += String.format("%90s", nome);
                    corpo += String.format("%40s", email);
                    corpo += String.format("%50s", senha);
                    corpo += String.format("%8s", cep);
                    corpo += String.format("%05d", numero);
                    corpo += String.format("%12s", dataNascimento);
                    corpo += String.format("%17s", bairro);
                    contRegDados++;
                    gravaRegistro(nomeArq, corpo);
                    break;
                case 3:
                    trailer += "01";
                    trailer += String.format("%08d", contRegDados);
                    gravaRegistro(nomeArq,trailer);
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }

        } while (opt != 3);
    }

}
