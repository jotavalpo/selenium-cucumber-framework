package stepDefinitions.base;

import com.opencsv.exceptions.CsvException;
import pageObjects.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.Constants_Vars;
import utils.HelperMethods;

import java.io.IOException;
import java.util.List;

public class RealizarCompraSteps extends Base_PO{

    private Base_PO basePo;
    private Home_PO homePo;
    private Checkout_PO checkoutPo;
    private MiCuenta_PO miCuentaPo;
    private Login_PO loginPo;

    private List<String[]> credenciales;

    public RealizarCompraSteps(Base_PO basePo, MiCuenta_PO miCuentaPo,
                               Home_PO homePo, Checkout_PO checkoutPo,
                               Login_PO loginPo) {
        this.basePo = basePo;
        this.miCuentaPo = miCuentaPo;
        this.homePo = homePo;
        this.checkoutPo = checkoutPo;
        this.loginPo = loginPo;
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

    @When("El producto se agrega correctamente")
    public void validarProductoAgregado() {
        homePo.validarProductoAgregadoACarro();
    }

    @When("Ingreso al checkout")
    public void ingreso_al_checkout() {
        homePo.iralCheckout();
        checkoutPo.validarCheckoutDesplegado();
        HelperMethods.takeScreenshot(basePo.getDriver(), "Vista general de checkout");

    }
    @When("Inicio sesion usando las credenciales del archivo {string}")
    public void inicio_sesion_con_mi_cuenta(String filePath) throws IOException, CsvException {
        credenciales = HelperMethods.readCsv(filePath);
        for (String[] cred : credenciales) {
            String email = cred[0];
            String password = cred[1];
            checkoutPo.completarPasoUno(email, password);
        }
        HelperMethods.takeScreenshot(basePo.getDriver(), "Step 1 exitoso");
    }

    @When("Indico la direccion de facturacion")
    public void indico_la_direccion_de_facturacion() {
        checkoutPo.completarPasoDos();
        HelperMethods.takeScreenshot(basePo.getDriver(), "Step 2 exitoso");

    }

    @When("Indico la direccion de entrega")
    public void indico_la_direccion_de_entrega() {
        checkoutPo.completarPasoTres();
        HelperMethods.takeScreenshot(basePo.getDriver(), "Step 3 exitoso");
    }
    @When("Indico el metodo de envio")
    public void indico_el_metodo_de_envio() {
        checkoutPo.completarPasoCuatro();
        HelperMethods.takeScreenshot(basePo.getDriver(), "Step 4 exitoso");

    }
    @When("Indico el metodo de pago")
    public void indico_el_metodo_de_pago() {
        checkoutPo.completarPasoCinco();
        HelperMethods.takeScreenshot(basePo.getDriver(), "Step 5 exitoso");

    }

    @When("Finalizo el proceso de compra de manera exitosa")
    public void finalizo_el_proceso_de_compra_de_manera_exitosa() {
        checkoutPo.completarPasoSeis();
        checkoutPo.validarOrdenExitosa();
        HelperMethods.takeScreenshot(basePo.getDriver(), "Orden de compra exitosa");

    }
    @When("Voy al historial de compras")
    public void voy_al_historial_de_compras() {
        checkoutPo.irAHistorialDeOrdenes();

    }
    @Then("Valido que compra quede en estado pendiente")
    public void valido_que_compra_quede_en_estado_pendiente() {
        miCuentaPo.irADetalledePedidoHistorico();
        miCuentaPo.validarEstadoPendientePedido();
        HelperMethods.takeScreenshot(basePo.getDriver(), "Orden de compra en estado correcto");
        loginPo.logout();

    }

}
