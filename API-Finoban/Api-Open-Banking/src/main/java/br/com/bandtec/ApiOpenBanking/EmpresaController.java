package br.com.bandtec.ApiOpenBanking;

import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmpresaController {



    @GetMapping("/listar/{valorImovel}")
    public List<TaxavelResponse> listar(@PathVariable Double valorImovel){

        RestTemplate client = new RestTemplate();
        Map<String, Double> vars = new HashMap<String, Double>();
        vars.put("listar/", valorImovel);


//        String req = client.postForObject("http://localhost:9000/empresas/{listar}/{valoImovel}",
//                                         String.class, valorImovel);
        //        List<br.com.finoban.demo.EmpresaResponse> res = gson.fromJson(req, br.com.finoban.demo.ListaEmpresa.class);
//        EmpresaResponse[] res = gson.fromJson(req, EmpresaResponse[].class);
//        List<EmpresaResponse> empresa = Arrays.asList(res);

//        ResponseEntity<TaxavelResponse> req = client.getForObject("http://localhost:9000/listar/" + valorImovel,
//                TaxavelResponse.class);
//        Gson gson = new Gson();
        String uri = "http://localhost:9000/empresas/listar/" + valorImovel;
        ResponseEntity<TaxavelResponse[]> response = client.getForEntity(uri, TaxavelResponse[].class);
        TaxavelResponse[] taxaveis = response.getBody();
        List<TaxavelResponse> listTaxaveis = Arrays.asList(taxaveis);

        return listTaxaveis;
    }
}
