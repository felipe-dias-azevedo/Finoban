package com.bandtec.br.finoban.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.util.Assert;

public class GestaoOperacoesMatematicasSteps {

    private int numero1;
    private int numero2;
    private int resultado;

    @Given("Que tenho um número {int}")
    public void queTenhoUmNumero(int numero) {
        this.numero1 = numero;
    }

    @When("Outro número {int}")
    public void andOutroNumero(int numero) {
        this.numero2 = numero;
    }

    @When("Somar os dois")
    public void whenSomarOsDois() {
        this.resultado = this.numero1 + this.numero2;
    }

    @When("Subtrair os dois")
    public void whenSubtrairOsDois() {
        this.resultado = this.numero1 - this.numero2;
    }

    @Then("Deverá retornar {int}")
    public void thenDeveraRetornar(int resultado) {
        Assert.isTrue(this.resultado == resultado);
    }


}
