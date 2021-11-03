package com.bandtec.br.finoban.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.Test;

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

        //Assert.assertTrue(browser.getCurrentUrl().equals("http://localhost:3000/login"));
        //Assert.assertTrue(browser.getPageSource().contains("Usuário e senha inválidos."));
        //Assert.assertThrows(NoSuchElementException.class, () -> browser.findElement(By.id("usuario-logado")));
        browser.quit();
    }

//    @Test
//    public void deveriaEfetuarLoginComDadosValidos() {
//        System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver.exe");
//        WebDriver browser = new ChromeDriver();
//        browser.navigate().to("http://localhost:3000/login");
//        browser.findElement(By.id("username")).sendKeys("felipe@admin.com");
//        browser.findElement(By.id("password")).sendKeys("123456");
//        browser.findElement(By.id("login-form")).submit();
//
//        Assert.assertFalse(browser.getCurrentUrl().equals("http://localhost:3000/login"));
//        Assert.assertEquals("fulano", browser.findElement(By.id("usuario-logado")).getText());
//        browser.quit();
//    }

}
