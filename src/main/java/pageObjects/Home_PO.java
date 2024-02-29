package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Home_PO extends Base_PO {

    // Metodo Constructor
    public Home_PO() {
        super();
    }

    //Input buscador Home
    private @FindBy(xpath = "//input[@placeholder='Search']")
    WebElement input_search;

    //Botón buscar
    private @FindBy(xpath = "//i[@class='fa fa-search']")
    WebElement btn_buscar;

    //Producto a seleccionar
    private @FindBy(xpath = "//img[@title='iPhone']")
    WebElement link_iphone;

    //Botón Add to Cart
    private @FindBy(xpath = "//button[@id='button-cart']")
    WebElement btn_addToCart;

    //Alerta producto agregado a carrito
    private @FindBy(xpath = "//div[contains(@class,'alert alert-success alert-dismissible')]")
    WebElement alert_sucess;

    //Botón carrito desde Home
    private @FindBy(xpath = "//span[@id='cart-total']")
    WebElement btn_cartItems;

    //Link View Cart
    private @FindBy(xpath = "//strong[contains(.,'View Cart')]")
    WebElement link_viewCart;

    public void buscarProducto(String producto) {
        waitForWebElementAndClick(input_search);
        sendKeys(input_search, producto);
        btn_buscar.click();

    }

    public void seleccionarProducto() {
        waitForWebElementAndClick(link_iphone);

    }

    public void agregarProductoACarro() {
        waitForWebElementAndClick(btn_addToCart);

    }

    public void validarProductoAgregadoACarro() {
        waitForElementVisibleAndContinue(alert_sucess, 10);

    }

    public void entrarACarrito() {
        waitForWebElementAndClick(btn_cartItems);
        waitForWebElementAndClick(link_viewCart);

    }

}


