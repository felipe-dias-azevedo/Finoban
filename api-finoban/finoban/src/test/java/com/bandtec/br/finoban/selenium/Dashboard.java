package com.bandtec.br.finoban.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Dashboard {

    @Test
    public void Usuário_fez_uma_simulação_banco_S16bank_deve_ir_para_Dashboard() throws InterruptedException {
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

        Thread.sleep(10000);

        browser.findElement(By.id("btn-bank-card")).click();

        Thread.sleep(5000);

        browser.findElement(By.id("bnt-hub-login")).click();

        browser.findElement(By.id("email")).sendKeys("felipe@admin.com");
        browser.findElement(By.id("password")).sendKeys("123456");
        browser.findElement(By.id("submit")).click();

        Thread.sleep(10000);

        browser.quit();

        




    }


}
