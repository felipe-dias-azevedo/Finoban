package com.bandtec.br.finoban.selenium;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Cadastro {

    @Test
    public void cadastroCpfInvalidoRetornaMenssagemDeCpfInvalido() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver.exe");
        WebDriver browser = new ChromeDriver();
        browser.navigate().to("http://localhost:3000/cadastro");

        browser.findElement(By.id("nome_completo_cadastro")).sendKeys("Jose paulo");
        browser.findElement(By.id("cpf")).sendKeys("123456");

        browser.findElement(By.id("bnt-cadastro")).click();

        Thread.sleep(1000);

        assertTrue(browser.getPageSource().contains("Insira um CPF válido"));
        browser.quit();
    }

    @Test
    public void cadastroSenhaInvalidaRetornaMenssagemDeInsiraUmasenha() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver.exe");
        WebDriver browser = new ChromeDriver();
        browser.navigate().to("http://localhost:3000/cadastro");


        browser.findElement(By.id("nome_completo_cadastro")).sendKeys("Jose paulo");
        browser.findElement(By.id("cpf")).sendKeys("52076914811");
        browser.findElement(By.name("senha")).sendKeys("1234567");
        browser.findElement(By.name("confirmarSenha")).sendKeys("12");

        browser.findElement(By.id("bnt-cadastro")).click();

        Thread.sleep(1000);

        assertTrue(browser.getPageSource().contains("Insira uma senha"));
        browser.quit();
    }

    @Test
    public void cadastroUsuarioClicaBotaoDeCadastroComTudoNull() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver.exe");
        WebDriver browser = new ChromeDriver();
        browser.navigate().to("http://localhost:3000/cadastro");

        browser.findElement(By.id("bnt-cadastro")).click();

        Thread.sleep(1000);

        assertTrue(browser.getPageSource().contains("Insira uma senha"));
        assertTrue(browser.getPageSource().contains("Insira um nome válido"));
        assertTrue(browser.getPageSource().contains("Insira um CPF"));
        assertTrue(browser.getPageSource().contains("Insira um endereço de e-mail válido"));

        browser.quit();
    }

    @Test
    public void usuarioPaginaDeCadastroPossuiLoginClicaPossuiLogin(){

        System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver.exe");
        WebDriver browser = new ChromeDriver();
        browser.navigate().to("http://localhost:3000/cadastro");

        browser.findElement(By.id("possui-conta-login")).click();

        assertTrue(browser.getCurrentUrl().equals("http://localhost:3000/login"));

        browser.quit();
    }




//    @Test
//    public void cadastroCpfInvalidoRetornaMenssagemDeCpfInvalido() {
//
//        System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver.exe");
//        WebDriver browser = new ChromeDriver();
//        browser.navigate().to("http://localhost:3000/cadastro");
//
//
//        browser.findElement(By.id("nome_completo_cadastro")).sendKeys("Jose paulo");
//        browser.findElement(By.id("cpf")).sendKeys("123456");
//        browser.findElement(By.id("nome_cadastro")).sendKeys("zee@gmail.com");
//        browser.findElement(By.id("login_cadastro")).sendKeys("123456");
//        browser.findElement(By.name("confirmarSenha")).sendKeys("123456");
//        browser.findElement(By.id("cep_cadastro")).sendKeys("09571450");
//        browser.findElement(By.id("numero_cadastro")).sendKeys("123456");
//        //data nasc n funfa
//
//        browser.findElement(By.id("bnt-cadastro")).click();
//
//
//    }


}