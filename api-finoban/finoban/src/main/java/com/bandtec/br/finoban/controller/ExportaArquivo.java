package com.bandtec.br.finoban.controller;


import com.bandtec.br.finoban.models.DocumentoLayout;
import com.bandtec.br.finoban.repository.ArquivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


@RequestMapping("/api-finoban/exportar")
public class ExportaArquivo {

    @Autowired
    private ArquivoRepository repository;

    @PostMapping()
    public ResponseEntity postArquivo(@RequestParam MultipartFile arquivo) {

        BufferedReader valor = null;
        String dados;
        String tipoDoDado;

        if (!arquivo.getOriginalFilename().endsWith(".txt")) {
            return ResponseEntity.status(400).body("formato de arquivo invalido");
        } else {

            try {
                valor = new BufferedReader(new FileReader(arquivo.getOriginalFilename()));
            } catch (FileNotFoundException e) {
                return ResponseEntity.status(400).body("tente novamente, falha ao abrir o arquivo");
            }
            try {
                dados = valor.readLine();

                DocumentoLayout documentoLayout = new DocumentoLayout();
                while (dados == null) {

                    tipoDoDado = dados.substring(0, 2);
                    dados = dados.replaceAll("", "");
                    switch (tipoDoDado) {
                        case "02":
                            documentoLayout.setNomeBanco(dados.substring(3, 19));
                            documentoLayout.setNomeCliente(dados.substring(20, 109));
                            documentoLayout.setValorFinanciamento(Integer.valueOf(dados.substring(110, 121)));
                            documentoLayout.setRegiao(dados.substring(122, 141));
                            documentoLayout.setTempoFinanciamento(Integer.valueOf(dados.substring(142, 143)));
                            break;
                    }
                    dados = valor.readLine();
                }

                repository.save(documentoLayout);

                return ResponseEntity.status(200).body("enviado com sucesso");
            } catch (IOException e) {
                return ResponseEntity.status(404).body("Erro");
            }
        }
    }
}
