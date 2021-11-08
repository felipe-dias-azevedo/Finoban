package com.bandtec.br.finoban.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TelaHome {

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
        browser.quit();

        assertTrue(browser.getCurrentUrl().equals("http://localhost:3000"));
        //assertTrue(browser.getPageSource().contains("Insira o valor do imóvel"));
        //n esta reconhendo o texto na pagina
    }

}
