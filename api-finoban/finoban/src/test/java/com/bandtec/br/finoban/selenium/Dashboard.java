package com.bandtec.br.finoban.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Dashboard {

    @Test
    public void Usuário_fez_uma_simulação_completa_() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver.exe");
        WebDriver browser = new ChromeDriver();
        browser.navigate().to("http://52.6.99.67/");
        browser.findElement(By.id("sim")).click();

        browser.findElement(By.id("cpf")).sendKeys("52076914810");
        browser.findElement(By.id("renda")).sendKeys("5000");
        browser.findElement(By.id("valorImovel")).sendKeys("20000");
        browser.findElement(By.id("tempoFinanciamento")).sendKeys("15");
        browser.findElement(By.id("porcentagemRenda")).sendKeys("30");

        browser.findElement(By.id("bt-simular")).click();

        Thread.sleep(7000);

        browser.findElement(By.className("btn-bank-card")).click();

        browser.findElement(By.id("bnt-hub-login")).click();
        Thread.sleep(1000);

        browser.findElement(By.id("email")).sendKeys("catarinacarneiro@bandtec.com.br");
        browser.findElement(By.id("password")).sendKeys("123456");
        browser.findElement(By.id("submit")).click();

        Thread.sleep(7000);
        browser.findElement(By.className("btn-contratar")).click();

        Thread.sleep(5000);
        browser.findElement(By.id("wpp")).click();

        Thread.sleep(5000);
        assertTrue(browser.getCurrentUrl().equals("https://api.whatsapp.com/send/?phone=551131758248&text&app_absent=0"));
        assertTrue(browser.getPageSource().contains("Banco Safra"));

        browser.quit();
    }



}
