package com.bandtec.br.finoban.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Selenium {

    @Test
    public void Teste1DeFuncionamento(){

        System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver.exe");
        WebDriver browser = new ChromeDriver();
        browser.navigate().to("http://localhost:8080/finoban");
        browser.quit();
    }

    @Test
    public void naoDeveriaLogarComDadosInvalidos() {
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
    public void deveriaEfetuarLoginComDadosValidos() {
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

    @Test
    public void UsuárioPreencheAsinformaçõesCompletasDoForms(){
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

    assertFalse(browser.getCurrentUrl().equals("http://localhost:3000/login"));

    browser.quit();

    }

    //falso --- dando erro
    @Test
    public void UsuárioPreencheAsinformaçõesDoFormsFaltandoOValorDoImovel(){
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
