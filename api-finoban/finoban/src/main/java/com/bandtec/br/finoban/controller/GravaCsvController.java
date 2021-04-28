package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.models.DocumentoLayout;
import com.bandtec.br.finoban.models.ListaObj;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.FormatterClosedException;

@RestController
@RequestMapping("/api-finoban/gravar")
public class GravaCsvController {

    @PostMapping("/csv")
    public ResponseEntity postRegistro(@RequestBody DocumentoLayout documentoLayout) {
        ListaObj<DocumentoLayout> lista = new ListaObj<DocumentoLayout>(1);
        lista.adiciona(documentoLayout);
        gravaLista(lista,"target\\classes\\static\\financiamento");
        return ResponseEntity.status(201).build();
    }


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
}
