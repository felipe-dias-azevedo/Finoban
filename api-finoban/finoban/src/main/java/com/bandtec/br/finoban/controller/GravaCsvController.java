package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.models.DocumentoLayout;
import com.bandtec.br.finoban.models.ListaObj;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.FormatterClosedException;

public class GravaCsvController {

    public static void gravaLista(ListaObj<DocumentoLayout> lista, String nomeArquivo) {
        FileWriter arq = null;
        Formatter saida = null;
        boolean deuRuim = false;
        nomeArquivo += ".csv";

        try {
            arq = new FileWriter(nomeArquivo, true);
            saida = new Formatter(arq);
        }
        catch (IOException erro) {
            System.err.println("Erro ao abrir arquivo");
            System.exit(1);
        }

        try {
            for (int i=0; i< lista.getTamanho(); i++) {
                DocumentoLayout docLayout = lista.getElemento(i);
                saida.format("%s;%s;%d;%s;%d%n", docLayout.getNomeBanco(),
                        docLayout.getNomeCliente(), docLayout.getValorFinanciamento(), docLayout.getRegiao(),
                        docLayout.getTempoFinanciamento());
            }
        }
        catch (FormatterClosedException erro) {
            System.err.println("Erro ao gravar no arquivo");
            deuRuim= true;
        }
        finally {
            saida.close();
            try {
                arq.close();
            }
            catch (IOException erro) {
                System.err.println("Erro ao fechar arquivo.");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);
            }
        }
    }

    public static void main(String[] args) {
        ListaObj<DocumentoLayout> lista = new ListaObj<DocumentoLayout>(5);
        lista.adiciona (new DocumentoLayout("Presil", "Maria José",
                600000, "São Paulo", 30));
        lista.adiciona (new DocumentoLayout("S16Bank", "Ricardo Silva",
                400000, "Lapa", 20));
        lista.adiciona (new DocumentoLayout("Sifra", "Rogério Nunes",
                300000, "Jardins", 10));
        lista.adiciona (new DocumentoLayout("Presil", "Ricardo Perez",
                70000, "Tatuapé", 05));

        gravaLista(lista,"target\\classes\\static\\financiamento");

    }

}
