package com.bandtec.br.finoban.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Selenium {

    @Test
    public void givenQueOSiteEstáNoAr(){

        System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver.exe");
        WebDriver browser = new ChromeDriver();
        browser.navigate().to("http://localhost:3000/finoban");
        browser.quit();
    }

    //----------------------TELA LOGIN ----------------------

    @Test
    //GIVEN que sou um usuário
    //AND desejo realizar me login no site
    //When passar meu email errado
    //AND clicar para fazer login
    //THEN devo receber uma menssagem de email invalido
    public void login_ComEmailInvalido() {
        System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver.exe");
        WebDriver browser = new ChromeDriver();
        browser.navigate().to("http://localhost:3000/login");
        browser.findElement(By.id("email")).sendKeys("invalido");
        browser.findElement(By.id("password")).sendKeys("123123");
        browser.findElement(By.id("submit")).click();

        assertTrue(browser.getCurrentUrl().equals("http://localhost:3000/login"));
        assertTrue(browser.getPageSource().contains("Endereço de e-mail inválido!"));
        //Assert.assertThrows(NoSuchElementException.class, () -> browser.findElement(By.id("usuario-logado")));
        browser.quit();
    }

    @Test
    //GIVEN que sou um usuário
    //AND desejo realizar me login no site
    //When passar mnha senha errado
    //AND clicar para fazer login
    //THEN devo receber uma menssagem de senha invalida
    public void login_ComSenhaInvalida() {
        System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver.exe");
        WebDriver browser = new ChromeDriver();
        browser.navigate().to("http://localhost:3000/login");
        browser.findElement(By.id("email")).sendKeys("invalido");
        browser.findElement(By.id("password")).sendKeys("123123");
        browser.findElement(By.id("submit")).click();

        assertTrue(browser.getCurrentUrl().equals("http://localhost:3000/login"));
        assertTrue(browser.getPageSource().contains("Endereço de e-mail inválido!"));
        //Assert.assertThrows(NoSuchElementException.class, () -> browser.findElement(By.id("usuario-logado")));
        browser.quit();
    }

    @Test
    //GIVEN que sou um usuário
    //AND desejo realizar me login no site
    //When passar minhas informações
    //AND clicar para fazer login
    //THEN deverá me redirecionar para a página do HUB
    public void login_EmailESenhaValidos() {
        System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver.exe");
        WebDriver browser = new ChromeDriver();
        browser.navigate().to("http://localhost:3000/login");
        browser.findElement(By.id("email")).sendKeys("felipe@admin.com");
        browser.findElement(By.id("password")).sendKeys("123456");
        browser.findElement(By.id("submit")).click();

        assertFalse(browser.getCurrentUrl().equals("http://localhost:3000/login"));
       //assertEquals("fulano", browser.findElement(By.id("usuario-logado")).getText());
        browser.quit();
    }

    //----------------------TELA HOME ----------------------

    @Test
    //Given que sou um usuário
    //And desejo realizar uma simulação no site
    //When preencher todas as informações do forms
    //And clicar para fazer a simulação
    //Then deverá me redirecionar para a página do HUB
    public void home_UsuárioPreencheAsinformaçõesCompletasDoFormsPassandoValorDoImovel(){
    System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver.exe");
    WebDriver browser = new ChromeDriver();
    browser.navigate().to("http://localhost:3000");
    browser.findElement(By.id("sim")).click();

    browser.findElement(By.id("cpf")).sendKeys("52076914810");
    browser.findElement(By.id("renda")).sendKeys("5000");
    browser.findElement(By.id("valorImovel")).sendKeys("20000");
    browser.findElement(By.id("tempoFinanciamento")).sendKeys("15");
    browser.findElement(By.id("porcentagemRenda")).sendKeys("30");

    browser.findElement(By.id("bt-simular")).click();
    browser.quit();

    assertFalse(browser.getCurrentUrl().equals("http://localhost:3000/simulador"));
    }

    @Test
    //Given que sou um usuário
    //And desejo realizar uma simulação no site
    //When preencher todas as informações do forms escolhendo regiao
    //And clicar para fazer a simulação
    //Then deverá me redirecionar para a página do HUB
    public void home_UsuárioPreencheAsinformaçõesDoFormsEscolhendoUmaRegiao(){
        System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver.exe");
        WebDriver browser = new ChromeDriver();
        browser.navigate().to("http://localhost:3000");
        browser.findElement(By.id("sim")).click();

        browser.findElement(By.id("cpf")).sendKeys("52076914810");
        browser.findElement(By.id("renda")).sendKeys("5000");
        //browser.findElement(By.id("valorImovel")).sendKeys("");
        browser.findElement(By.id("tempoFinanciamento")).sendKeys("15");
        browser.findElement(By.id("porcentagemRenda")).sendKeys("30");

        browser.findElement(By.id("bt-simular")).click();
        browser.quit();

        assertTrue(browser.getCurrentUrl().equals("http://localhost:3000/simulador"));
    }

    @Test
    //Given que sou um usuário
    //And desejo realizar uma simulação no site
    //When preencher todas as informações do forms faltando o valor do imovel
    //And clicar para fazer a simulação
    //Then deverá me redirecionar para a página do HUB
    public void UsuárioPreencheAsinformaçõesDoFormsFaltandoValorDoImovel(){
        System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver.exe");
        WebDriver browser = new ChromeDriver();
        browser.navigate().to("http://localhost:3000");
        browser.findElement(By.id("sim")).click();

        browser.findElement(By.id("cpf")).sendKeys("52076914810");
        browser.findElement(By.id("renda")).sendKeys("5000");
        browser.findElement(By.id("valorImovel")).sendKeys("");
        browser.findElement(By.id("tempoFinanciamento")).sendKeys("15");
        browser.findElement(By.id("porcentagemRenda")).sendKeys("30");

        browser.findElement(By.id("bt-simular")).click();

        assertTrue(browser.getCurrentUrl().equals("http://localhost:3000"));
        assertTrue(browser.getPageSource().contains("Insira o valor do imóvel"));

        browser.quit();
    }


    


}
