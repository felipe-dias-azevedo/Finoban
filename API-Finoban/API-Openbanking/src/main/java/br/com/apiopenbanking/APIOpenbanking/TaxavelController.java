package br.com.apiopenbanking.APIOpenbanking;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/empresas")
public class TaxavelController {

//    public TaxavelController() {
//        Taxavel bancoAltaRenda = new BancoAltaRenda("Sifra", 0.014,
//                0.0005, 25.0, 600000.0);
//        Taxavel bancoVarejo = new BancoVarejo("Banco do Presil", 0.012,
//                0.0007, 50.0, 600000.0);
//        Taxavel fintech = new Fintech("C16Bank", 0.01,
//                0.0009, 75.0, 600000.0);
//        listaEmpresas.add(bancoAltaRenda);
//        listaEmpresas.add(bancoVarejo);
//        listaEmpresas.add(fintech);
//
//    }


    int cont = 0;
    @GetMapping("/listar/{valorImovel}")
    public List<Taxavel> listar(@PathVariable Double valorImovel){

        List<Taxavel> listaEmpresas = new ArrayList<Taxavel>();
        Taxavel bancoAltaRenda = new BancoAltaRenda("Sifra", 0.014,
                0.0005, 25.0, valorImovel);
        Taxavel bancoVarejo = new BancoVarejo("Banco do Presil", 0.012,
                0.0007, 50.0, valorImovel);
        Taxavel fintech = new Fintech("C16Bank", 0.01,
                0.0009, 75.0, valorImovel);
        listaEmpresas.add(bancoAltaRenda);
        listaEmpresas.add(bancoVarejo);
        listaEmpresas.add(fintech);

        return listaEmpresas;
    }

//    @PostMapping("/criar-varejo")
//    public String inserirVarejo(@RequestBody BancoVarejo b){
//        Taxavel bancoVarejo = new BancoVarejo(b.getNome(), b.getTaxaJuros(), b.getMip(),
//                                                b.getTaxaAdministracao(), b.getValorImovel());
//        listaEmpresas.add(bancoVarejo);
//        return "Banco varejo adicionado com sucesso!";
//    }
//
//    @PostMapping("/criar-fintech")
//    public String inserirFintech(@RequestBody Fintech b){
//        Taxavel fintech = new Fintech(b.getNome(), b.getTaxaJuros(), b.getMip(),
//                b.getTaxaAdministracao(), b.getValorImovel());
//        listaEmpresas.add(fintech);
//        return "Fintech adicionada com sucesso!";
//    }


//    @GetMapping("/listar")
//        public List<Taxavel> listar() {
//
//        return listaEmpresas;
//    }


}
