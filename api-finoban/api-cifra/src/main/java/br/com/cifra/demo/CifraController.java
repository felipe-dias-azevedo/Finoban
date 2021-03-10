package br.com.cifra.demo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api-cifra")
public class CifraController {

    @GetMapping("/{valorImovel}")
    public Cifra listar(@PathVariable Double valorImovel){
        Cifra bancoCifra = new Cifra("Cifra", 0.014,
                0.0005, 25.0, valorImovel);
        return bancoCifra;
    }

}
