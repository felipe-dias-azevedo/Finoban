package br.com.finoban.controller;

import br.com.finoban.demo.Empresas;
import br.com.finoban.mock.Constantes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api-finoban")
public class EmpresasController {

    @GetMapping("/{valorImovel}")
    public List<Empresas> listar(@PathVariable Double valorImovel){

        RestTemplate client = new RestTemplate();

        List<String> listUri = new ArrayList<>();
        listUri.add(Constantes.URI_S16BANK);
        listUri.add(Constantes.URI_CIFRA);
        listUri.add(Constantes.URI_PRESIL);

        List<Empresas> listEmpresas = new ArrayList<>();

        for (String uri: listUri) {
            ResponseEntity<Empresas> response = client.
                    getForEntity(uri + valorImovel, Empresas.class);
            Empresas empresa = response.getBody();
            listEmpresas.add(empresa);
        }

        return listEmpresas;
    }
}
