package com.bandtec.br.finoban.selenium;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Footer {

    @Test
    //Given que sou um usuário
    //And desejo ir para o inico
    //When ao cliclar no footer navegacao -> inicio
    //Then deverá me redirecionar para a página Inicial
    public void usuarioClicaNoFooterNavegacaoInicio() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver.exe");
        WebDriver browser = new ChromeDriver();
        browser.navigate().to("http://localhost:3000/sobrenos");

        browser.findElement(By.id("nav-inicio")).click();
        Thread.sleep(1000);

        assertTrue(browser.getCurrentUrl().equals("http://localhost:3000/"));
        browser.quit();
    }

    @Test
    //Given que sou um usuário
    //And desejo ir para o inico
    //When ao cliclar no footer navegacao -> sobre nós
    //Then deverá me redirecionar para a página sobrenos
    public void usuarioClicaNoFooterNavegacaoSobrenos() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver.exe");
        WebDriver browser = new ChromeDriver();
        browser.navigate().to("http://localhost:3000");


        browser.findElement(By.id("nav-sobre-nos")).click();
        Thread.sleep(1000);

        assertTrue(browser.getCurrentUrl().equals("http://localhost:3000/sobrenos"));
        browser.quit();
    }

    @Test
    //Given que sou um usuário
    //And desejo ir para o inico
    //When ao cliclar no footer navegacao -> contato
    //Then deverá me redirecionar para a página sobrenos
    public void usuarioClicaNoFooterNavegacaoContato() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver.exe");
        WebDriver browser = new ChromeDriver();
        browser.navigate().to("http://localhost:3000");


        browser.findElement(By.id("nav-contato")).click();
        Thread.sleep(1000);

        assertTrue(browser.getCurrentUrl().equals("http://localhost:3000/sobrenos"));
        browser.quit();
    }

    @Test
    //Given que sou um usuário
    //And desejo ir para o inico
    //When ao cliclar no footer parceiros -> bandtec
    //Then deverá me redirecionar para a página da bandtec
    public void usuarioClicaNoFooterParceirosBandtec() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver.exe");
        WebDriver browser = new ChromeDriver();
        browser.navigate().to("http://localhost:3000");


        browser.findElement(By.id("bandtec")).click();
        Thread.sleep(1000);

        assertTrue(browser.getCurrentUrl().equals("http://www.digitalschool.com.br/faculdade/"));
        browser.quit();
    }

    @Test
    //Given que sou um usuário
    //And desejo ir para o inico
    //When ao cliclar no footer parceiros -> safra
    //Then deverá me redirecionar para a página do safra
    public void usuarioClicaNoFooterParceirosSafra() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver.exe");
        WebDriver browser = new ChromeDriver();
        browser.navigate().to("http://localhost:3000");

        browser.findElement(By.id("safra")).click();
        Thread.sleep(1000);

        assertTrue(browser.getCurrentUrl().equals("https://www.safra.com.br/"));
        browser.quit();
    }


}
