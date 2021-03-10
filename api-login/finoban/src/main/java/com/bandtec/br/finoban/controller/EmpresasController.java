package com.bandtec.br.finoban.controller;
import com.bandtec.br.finoban.demo.Taxavel;
import com.bandtec.br.finoban.models.BancoCifra;
import com.bandtec.br.finoban.models.BancoDoPresil;
import com.bandtec.br.finoban.models.BancoS16Bank;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api-finoban")
public class EmpresasController {

    @GetMapping("/{valorImovel}")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public List<Taxavel> listar(@PathVariable Double valorImovel){

        List<Taxavel> listaBancos = new ArrayList<>();
        BancoCifra cifra = new BancoCifra();
        cifra.getValorCet(valorImovel);
        BancoS16Bank s16bank = new BancoS16Bank();
        s16bank.getValorCet(valorImovel);
        BancoDoPresil bancoDoPresil = new BancoDoPresil();
        bancoDoPresil.getValorCet(valorImovel);

        listaBancos.add(cifra);
        listaBancos.add(s16bank);
        listaBancos.add(bancoDoPresil);

        return listaBancos;
    }
}
