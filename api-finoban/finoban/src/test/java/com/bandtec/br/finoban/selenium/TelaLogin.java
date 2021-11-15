package com.bandtec.br.finoban.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class TelaLogin {

// TDS COM sucesso

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
    //When passar meu email nao cadastrado
    //AND clicar para fazer login
    //THEN devo receber uma menssagem de email não encontrado
    public void login_ComEmailNaoCadastrado() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver.exe");
        WebDriver browser = new ChromeDriver();
        browser.navigate().to("http://localhost:3000/login");

        browser.findElement(By.id("email")).sendKeys("invalido@gmail.com");
        browser.findElement(By.id("password")).sendKeys("12312873");
        browser.findElement(By.id("submit")).click();

        Thread.sleep(2000);

        assertTrue(browser.getCurrentUrl().equals("http://localhost:3000/login"));
        assertTrue(browser.getPageSource().contains("email não encontrado"));

        browser.quit();
    }

    @Test
    //GIVEN que sou um usuário
    //AND desejo realizar me login no site
    //When passar senha vazia
    //AND clicar para fazer login
    //THEN devo receber uma menssagem de Insira uma senha
    public void login_ComSenhaVazia() {
        System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver.exe");
        WebDriver browser = new ChromeDriver();
        browser.navigate().to("http://localhost:3000/login");
        browser.findElement(By.id("email")).sendKeys("invalido@gmail.com");
        browser.findElement(By.id("password")).sendKeys("");
        browser.findElement(By.id("submit")).click();

        assertTrue(browser.getCurrentUrl().equals("http://localhost:3000/login"));
        assertTrue(browser.getPageSource().contains("Insira uma senha"));

        browser.quit();
    }

    @Test
    //GIVEN que sou um usuário
    //AND desejo realizar me login no site
    //When passar curta
    //AND clicar para fazer login
    //THEN devo receber uma menssagem de Insira uma senha com no mínimo 6 caracteres
    public void login_ComSenhaCurta() {
        System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver.exe");
        WebDriver browser = new ChromeDriver();
        browser.navigate().to("http://localhost:3000/login");
        browser.findElement(By.id("email")).sendKeys("invalido@gmail.com");
        browser.findElement(By.id("password")).sendKeys("1234");
        browser.findElement(By.id("submit")).click();

        assertTrue(browser.getCurrentUrl().equals("http://localhost:3000/login"));
        assertTrue(browser.getPageSource().contains("Insira uma senha com no mínimo 6 caracteres"));

        browser.quit();
    }

    @Test
    //GIVEN que sou um usuário
    //AND desejo realizar me login no site
    //When passar minhas informações
    //AND clicar para fazer login
    //THEN deverá me redirecionar para a página da Dashboard
    public void login_EmailESenhaValidos() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver.exe");
        WebDriver browser = new ChromeDriver();
        browser.navigate().to("http://localhost:3000/login");
        browser.findElement(By.id("email")).sendKeys("felipe@admin.com");
        browser.findElement(By.id("password")).sendKeys("123456");
        browser.findElement(By.id("submit")).click();

        Thread.sleep(10000);

        assertFalse(browser.getCurrentUrl().equals("http://localhost:3000/login"));

        browser.quit();
    }

}
