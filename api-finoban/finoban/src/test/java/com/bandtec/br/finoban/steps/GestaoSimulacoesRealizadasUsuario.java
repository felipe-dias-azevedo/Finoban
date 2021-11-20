package com.bandtec.br.finoban.steps;

import com.bandtec.br.finoban.builder.UsuarioBuilder;
import com.bandtec.br.finoban.dominio.Data;
import com.bandtec.br.finoban.dominio.entidades.Regiao;
import com.bandtec.br.finoban.dominio.entidades.Usuario;
import com.bandtec.br.finoban.dominio.requisicao.BancoRequisicaoModel;
import com.bandtec.br.finoban.dominio.resposta.RespostaApi;
import com.bandtec.br.finoban.dominio.resposta.SingleResponse;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import org.springframework.context.annotation.Scope;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Scope(scopeName = "Gestão de Simulaçoes realizadas pelo usuário")
public class GestaoSimulacoesRealizadasUsuario {

    private Usuario usuario;
    private List<Regiao> regiaoList;
    private BancoRequisicaoModel bancoRequisicaoModel;
    private SingleResponse<List<RespostaApi>> respostaApiFinanciamento;
    private static final String ENDPOINT_REGIOES = "http://localhost:8080/api-finoban/regioes";
    private static final String ENDPOINT_FINANCIAMENTO = "http://localhost:8080/api-finoban/financiamento";

    private CloseableHttpClient httpClient;
    private Gson gson;

    public GestaoSimulacoesRealizadasUsuario() {
        this.httpClient = HttpClients.createDefault();
        this.gson = new Gson();
    }

    @Given("que sou um usuario")
    public void queSouUmSuaurio() {
        this.usuario = new UsuarioBuilder().criarUsuario().getUsuario();
    }

    @And("desejo realizar uma simulacao")
    public void desejoRealizarUmaSimulacao() {
//        System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver.exe");
//        WebDriver browser = new ChromeDriver();
//        browser.navigate().to("http://localhost:3000");
//        browser.findElement(By.id("sim")).click();
//
//        browser.findElement(By.id("cpf")).sendKeys("52076914810");
//        browser.findElement(By.id("renda")).sendKeys("5000");
//        //browser.findElement(By.id("valorImovel")).sendKeys("");
//        browser.findElement(By.id("tempoFinanciamento")).sendKeys("15");
//        browser.findElement(By.id("porcentagemRenda")).sendKeys("30");
//
//        browser.findElement(By.id("bt-simular")).click();
//        browser.quit();
//
//        assertTrue(browser.getCurrentUrl().equals("http://localhost:3000/simulador"));
    }

    @When("escolher que nao conheco o valor do imovel")
    public void escolherQueNaoConhecoValorImovel() throws IOException {
         var httpGet = new HttpGet(ENDPOINT_REGIOES);
         ResponseHandler responseHandler = response -> {
             if (response.getStatusLine().getStatusCode() == 200) {
                 var entity = response.getEntity();
                 return entity != null ? EntityUtils.toString(entity) : null;
             } else {
                 throw new ClientProtocolException("Unexpected response status: " + response
                         .getStatusLine()
                         .getStatusCode());
             }
         };
        var responseBody = (String) this.httpClient.execute(httpGet, responseHandler);
        var responseSingleResponse = this.gson.fromJson(responseBody, SingleResponse.class);

        this.regiaoList = (List<Regiao>) responseSingleResponse.getData();
    }

    @Then("devera retornar todas as regioes cadastradas no banco")
    public void deveraRetornarTodasRegioesCadastradasNoBanco() {
        assertEquals(this.regiaoList.size(), 5);
    }

    @When("inserir os dados")
    public void inserirOsDados(DataTable user) {
        this.bancoRequisicaoModel = this.toModel(user.asMaps());
    }

    @And("realizar a simulacao")
    public void realizarASimulacao() throws IOException, InterruptedException {
        var httpClient = HttpClient.newBuilder().build();
        var bodyRequest = this.gson.toJson(this.bancoRequisicaoModel);
        var request = HttpRequest.newBuilder()
                .uri(URI.create(ENDPOINT_FINANCIAMENTO))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(bodyRequest))
                .build();

        HttpResponse<?> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        var fooType = new TypeToken<SingleResponse<List<RespostaApi>>>() {}.getType();
        this.respostaApiFinanciamento = this.gson.fromJson(response.body().toString(), fooType);
    }

    @Then("devera retornar os dados da simulacao")
    public void deveraRetornarOsDadosDaSimulacao() {
        var listaRespostaApi = this.respostaApiFinanciamento.getData();
        var listaConferir = new ArrayList<RespostaApi>();
        listaConferir.add(new RespostaApi(false, 404, new Data(1.59, 1.5, 1.7, 1.9, 7.0)));
        listaConferir.add(new RespostaApi(false, 404, new Data(1.59, 1.5, 1.7, 1.9, 7.0)));
        listaConferir.add(new RespostaApi(false, 404, new Data(1.59, 1.5, 1.7, 1.9, 7.0)));

        assertTrue(listaRespostaApi.get(0).getStatus().equals(listaConferir.get(0).getStatus()));
        assertTrue(listaRespostaApi.get(0).getData().getTaxaTotal().equals(listaConferir.get(0).getData().getTaxaTotal()));
        assertTrue(listaRespostaApi.get(1).getStatus().equals(listaConferir.get(1).getStatus()));
        assertTrue(listaRespostaApi.get(1).getData().getTaxaTotal().equals(listaConferir.get(1).getData().getTaxaTotal()));
        assertTrue(listaRespostaApi.get(2).getStatus().equals(listaConferir.get(2).getStatus()));
        assertTrue(listaRespostaApi.get(2).getData().getTaxaTotal().equals(listaConferir.get(2).getData().getTaxaTotal()));
    }


    private BancoRequisicaoModel toModel(List<Map<String, String>> data) {
        var renda = Integer.parseInt(data.get(0).get("renda"));
        var valorImovel = Integer.parseInt(data.get(0).get("valorImovel"));
        var porcentagemRenda = Integer.parseInt(data.get(0).get("porcentagemRenda"));
        var tempoFinanciamento = Integer.parseInt(data.get(0).get("tempoFinanciamento"));
        return new BancoRequisicaoModel(this.usuario.getCpf(), renda, valorImovel, tempoFinanciamento);
    }



}
