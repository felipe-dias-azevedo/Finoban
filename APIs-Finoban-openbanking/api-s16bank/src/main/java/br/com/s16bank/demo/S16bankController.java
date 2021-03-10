package br.com.s16bank.demo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api-s16bank")
public class S16bankController {

    @GetMapping("/{valorImovel}")
    public S16bank listar(@PathVariable Double valorImovel){
        S16bank bancoS16bank = new S16bank("S16Bank", 0.01,
                0.0009, 75.0, valorImovel);
        return bancoS16bank;
    }

}
