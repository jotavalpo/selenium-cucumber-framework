package stepDefinitions.base;

import pageObjects.Base_PO;
import pageObjects.Home_PO;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.Constants_Vars;

public class BuscarProductoYAgregarloACarritoSteps extends Base_PO{

    private Home_PO homePo;

    public BuscarProductoYAgregarloACarritoSteps(Home_PO homePo) {
        this.homePo = homePo;
    }

    @Given("Estoy en el home")
    public void estoy_en_la_pagina_de_inicio() {
        homePo.navigateToUrl(Constants_Vars.URL_TEST);

    }

    @When("Busco el producto {string}")
    public void buscoProducto(String producto) {
        homePo.buscarProducto(producto);
    }

    @When("Selecciono el resultado")
    public void seleccionarResultado() {
        homePo.seleccionarProducto();
    }

    @When("Agrego producto al carrito")
    public void agregarProductoACarro() {
        homePo.agregarProductoACarro();
    }

    @When("Entro al carrito de compras desde el home y veo producto")
    public void entrarACarrito() {
        homePo.entrarACarrito();
    }

    @Then("El producto se agrega correctamente")
    public void validarProductoAgregado() {
        homePo.validarProductoAgregadoACarro();
    }

}
