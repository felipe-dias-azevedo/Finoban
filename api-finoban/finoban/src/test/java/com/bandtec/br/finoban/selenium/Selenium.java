package com.bandtec.br.finoban.selenium;

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
}
