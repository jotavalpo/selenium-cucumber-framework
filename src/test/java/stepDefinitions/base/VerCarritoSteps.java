package stepDefinitions.base;

import pageObjects.Base_PO;
import pageObjects.CarritoCompras_PO;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class VerCarritoSteps extends Base_PO{

    private CarritoCompras_PO carritoComprasPo;

    public VerCarritoSteps(CarritoCompras_PO carritoComprasPo) {
        this.carritoComprasPo = carritoComprasPo;
    }

    @When("Valido que producto este agregado")
    public void validarProductoEnCarro() {
        carritoComprasPo.buscarProductoVisible();
    }

    @When("Elimino producto de carrito")
    public void eliminarProducto() {
        carritoComprasPo.eliminarProducto();
    }

    @Then("Valido que producto ya no este agregado")
    public void validarNoProductoEnCarro() {
        carritoComprasPo.buscarProductoNoVisible();
    }

}

