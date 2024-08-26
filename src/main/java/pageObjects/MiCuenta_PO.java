package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class MiCuenta_PO extends Base_PO {

    // Metodo Constructor
    public MiCuenta_PO() {
        super();
    }

    //Opcion Historial de Pedidos
    //Ver primer pedido de la lista
    private @FindBy(xpath = "(//a[@class='btn btn-info'])[1]")
    WebElement icon_verPedido;

    //Boton para reordenar producto de pedido
    private @FindBy(xpath = "//a[contains(@data-original-title,'Reorder')]")
    WebElement btn_reordenar;

    //Boton para devolver pedido
    private @FindBy(xpath = "//a[@data-original-title='Return']")
    WebElement btn_devolver;

    //Texto con estado Pendiente
    private @FindBy(xpath = "//td[contains(.,'Pending')]")
    WebElement text_estadoPendiente;

    //Boton Continuar
    private @FindBy(xpath = "//a[contains(.,'Continuar')]")
    WebElement btn_continuar;

    public void irADetalledePedidoHistorico() {
        waitForWebElementAndClick(icon_verPedido);

    }

    public void validarEstadoPendientePedido() {
        scrollElement(text_estadoPendiente);
        Assert.assertTrue(text_estadoPendiente.isDisplayed(), "Pendiente");
    }

}
