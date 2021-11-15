package com.bandtec.br.finoban.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MenuSuperior {

    //4teste com sucesso

    @Test
    //Given que sou um usuário
    //And desejo ir para tela  analise de negocios
    //When ao cliclar no menu no link "ANÁLISES DE NEGÓCIO"
    //Then deverá me redirecionar para a página de login de analise de negocio
    public void usuarioClicaMenuSuperiorBotaoAnliseDeNegocio(){
        System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver.exe");
        WebDriver browser = new ChromeDriver();
        browser.navigate().to("http://localhost:3000");

        browser.findElement(By.id("analise")).click();

        assertTrue(browser.getCurrentUrl().equals("http://localhost:3000/analise"));
        browser.quit();
    }

    @Test
    //Given que sou um usuário
    //And desejo ir para tela  sobre nós
    //When ao cliclar no menu no link"sobre nos"
    //Then deverá me redirecionar para a página Sobre nós
    public void usuarioClicaMenuSuperiorBotaoSobre(){
        System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver.exe");
        WebDriver browser = new ChromeDriver();
        browser.navigate().to("http://localhost:3000");

        browser.findElement(By.id("sobrenos")).click();

        assertTrue(browser.getCurrentUrl().equals("http://localhost:3000/sobrenos"));
        browser.quit();
    }

    @Test
    //Given que sou um usuário
    //And desejo ir para tela openBanking
    //When ao cliclar no menu no link "openBanking"
    //Then deverá me redirecionar para a página openBanking
    public void usuarioClicaMenuSuperiorBotaoOBK(){
        System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver.exe");
        WebDriver browser = new ChromeDriver();
        browser.navigate().to("http://localhost:3000");

        browser.findElement(By.id("openbanking")).click();

        assertTrue(browser.getCurrentUrl().equals("http://localhost:3000/openbanking"));
        browser.quit();
    }

    @Test
    //Given que sou um usuário
    //And desejo ir para tela testes Internos
    //When ao cliclar no menu no link "testes Internos"
    //Then deverá me redirecionar para a página testes Internos
    public void usuarioClicaMenuSuperiorBotaoTestesInternos(){
        System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver.exe");
        WebDriver browser = new ChromeDriver();
        browser.navigate().to("http://localhost:3000");

        browser.findElement(By.id("testes")).click();

        assertTrue(browser.getCurrentUrl().equals("http://localhost:3000/testes"));
        browser.quit();
    }

//    @Test
//    //Given que sou um usuário
//    //And desejo ir para tela de cadastro
//    //When ao cliclar no menu no botão de cadastro
//    //Then deverá me redirecionar para a página  de cadastro
//    public void usuarioClicaMenuSuperiorBotaoDeCadastro(){
//        System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver.exe");
//        WebDriver browser = new ChromeDriver();
//        browser.navigate().to("http://localhost:3000");
//
//        browser.findElement(By.id("bt-cadastro")).click();
//        // botão n reconhece id
//
//        assertTrue(browser.getCurrentUrl().equals("http://localhost:3000/cadastro"));
//        browser.quit();
//    }



}
