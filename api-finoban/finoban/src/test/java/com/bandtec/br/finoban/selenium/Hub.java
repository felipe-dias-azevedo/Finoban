package com.bandtec.br.finoban.selenium;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Hub {

    //Devolução das Taxas

    //Given que sou um usuário
    //When fiz uma simulação
    //And estou na tela do hub
    //Then deverá aparecer a taxa do banco Cifra
    @Test
    public void Usuário_fez_uma_simulação_banco_cifra_deve_retornar_a_taxa_MOCADO() throws InterruptedException {
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

        Thread.sleep(10000);

        assertTrue(browser.getCurrentUrl().equals("http://localhost:3000/simulador"));
        assertTrue(browser.getPageSource().contains("simulador"));
        assertTrue(browser.getPageSource().contains("Banco Cifra"));
        assertTrue(browser.getPageSource().contains("6.69%"));
        browser.quit();
    }

    //Given que sou um usuário
    //When fiz uma simulação
    //And estou na tela do hub
    //Then deverá aparecer a taxa do banco do Presil
    @Test
    public void Usuário_fez_uma_simulação_banco_Presil_deve_retornar_a_taxa_MOCADO() throws InterruptedException {
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

        Thread.sleep(10000);

        assertTrue(browser.getCurrentUrl().equals("http://localhost:3000/simulador"));
        assertTrue(browser.getPageSource().contains("simulador"));
        assertTrue(browser.getPageSource().contains("Banco do Presil"));
        assertTrue(browser.getPageSource().contains("6.69%"));
        browser.quit();
    }

    //Given que sou um usuário
    //When fiz uma simulação
    //And estou na tela do hub
    //Then deverá aparecer a taxa do S16 bank
    @Test
    public void Usuário_fez_uma_simulação_banco_S16bank_deve_retornar_a_taxa_MOCADO() throws InterruptedException {
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

        Thread.sleep(10000);

        assertTrue(browser.getCurrentUrl().equals("http://localhost:3000/simulador"));
        assertTrue(browser.getPageSource().contains("simulador"));
        assertTrue(browser.getPageSource().contains("S16 Bank"));
        assertTrue(browser.getPageSource().contains("6.69%"));
        browser.quit();
    }

    //Redireciona para Dashboard



}
