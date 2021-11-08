package com.bandtec.br.finoban.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TelaLogin {


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

}
