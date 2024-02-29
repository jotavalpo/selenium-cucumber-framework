package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CarritoCompras_PO extends Base_PO {

    // Metodo Constructor
    public CarritoCompras_PO() {
        super();
    }

    //Label de producto en carrito
    private @FindBy(xpath = "//td[contains(.,'product 11')]")
    WebElement label_modelo;

    //Botón eliminar producto de carro
    private @FindBy(xpath = "//i[@class='fa fa-times-circle']")
    WebElement btn_eliminar;

    public void buscarProductoVisible() {
        waitForElementVisible(label_modelo, 10);

    }

    public void eliminarProducto() {
        waitForWebElementAndClick(btn_eliminar);

    }

    public void buscarProductoNoVisible() {
        waitForElementNotVisible(label_modelo, 10);

    }

}


