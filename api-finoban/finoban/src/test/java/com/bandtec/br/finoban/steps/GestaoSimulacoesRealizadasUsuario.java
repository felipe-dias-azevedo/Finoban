package com.bandtec.br.finoban.steps;

import com.bandtec.br.finoban.builder.UsuarioBuilder;
import com.bandtec.br.finoban.dominio.entidades.Regiao;
import com.bandtec.br.finoban.dominio.entidades.Usuario;
import com.bandtec.br.finoban.dominio.resposta.SingleResponse;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.opencsv.bean.concurrent.SingleLineReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.context.annotation.Scope;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Scope(scopeName = "Gestão de Simulaçoes realizadas pelo usuário")
public class GestaoSimulacoesRealizadasUsuario {

    private Usuario usuario;
    private List<Regiao> regiaoList;
    private static final String ENDPOINT_REGIOES = "http://localhost:8080/api-finoban/regioes";

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
         try (var httpClient = HttpClients.createDefault()) {
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
            var responseBody = httpClient.execute(httpGet, responseHandler);
            SingleResponse responseSingleResponse = new Gson()
                    .fromJson((JsonElement) responseBody, SingleResponse.class);
            var listaRegioes = (List<Regiao>) responseSingleResponse.getData();
            listaRegioes.forEach(System.out::println);
         }


    }


}
