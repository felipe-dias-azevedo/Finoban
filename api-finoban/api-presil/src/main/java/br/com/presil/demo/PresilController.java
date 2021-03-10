package br.com.presil.demo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api-presil")
public class PresilController {

    @GetMapping("/{valorImovel}")
    public Presil listar(@PathVariable Double valorImovel){
        Presil bancoPresil = new Presil("Banco do Presil", 0.012,
                0.0007, 50.0, valorImovel);
        return bancoPresil;
    }


}
