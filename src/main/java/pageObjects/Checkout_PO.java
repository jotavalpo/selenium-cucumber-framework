package pageObjects;

import utils.Constants_Vars;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class Checkout_PO extends Base_PO {

    // Metodo Constructor
    public Checkout_PO() {
        super();
    }

    //Boton para iniciar Checkout
    private @FindBy(xpath = "//a[@class='btn btn-primary']")
    WebElement btn_iniciarCheckout;

    //Texto a validar en checkout desplegado
    private @FindBy(xpath = "//h1[contains(.,'Checkout')]")
    WebElement text_checkout;

    //Paso 1 Checkout Options
    //Checkbox Register Account
    private @FindBy(xpath = "//input[@checked='checked']")
    WebElement chk_registerAccount;

    //Checkbox Guest Checkout
    private @FindBy(xpath = "//input[@value='guest']")
    WebElement chk_guestAccount;

    //E-mail Address
    private @FindBy(xpath = "//input[@id='input-email']")
    WebElement input_User_Email;

    //Input User Password
    private @FindBy(xpath = "//input[@id='input-password']")
    WebElement input_User_Pwd;

    //Boton Iniciar Sesion
    private @FindBy(xpath = "//input[@value='Login']")
    WebElement btn_Iniciar_Sesion;

    //Paso 2 Billing Details
    //Checkbox Usar la direccion de pago registrada
    private @FindBy(xpath = "(//input[@value='existing'])[1]")
    WebElement chk_existingAddress;

    //Checkbox Usar una nueva direccion de pago
    private @FindBy(xpath = "(//input[contains(@value,'new')])[1]")
    WebElement chk_newAddress;

    //Input First Name
    private @FindBy(xpath = "//input[@name='firstname']")
    WebElement input_firstName;

    //Input Last Name
    private @FindBy(xpath = "//input[@name='lastname']")
    WebElement input_lastName;

    //Input Company
    private @FindBy(xpath = "//input[@name='company']")
    WebElement input_company;

    //Input Address 1
    private @FindBy(xpath = "//input[@name='address_1']")
    WebElement input_addressOne;

    //Input Address 2
    private @FindBy(xpath = "//input[@name='address_2']")
    WebElement input_addressTwo;

    //Input City
    private @FindBy(xpath = "//input[@name='city']")
    WebElement input_city;

    //Input Postal Code
    private @FindBy(xpath = "//input[@name='postcode']")
    WebElement input_postalCode;

    //Botón Continue Paso 2
    private @FindBy(xpath = "(//input[@value='Continue'])[1]")
    WebElement btn_continueTwo;

    //Step 3 Delivery Details
    //Checkbox Usar la direccion para entrega registrada
    private @FindBy(xpath = "(//input[@value='existing'])[2]")
    WebElement chk_addressDefault;

    //Checkbox Usar una nueva direccion para entrega
    private @FindBy(xpath = "(//input[@value='new'])[2]")
    WebElement chk_addressCustom;

    //Botón Continue Paso 3
    private @FindBy(xpath = "(//input[@value='Continue'])[2]")
    WebElement btn_continueThree;

    //Step 4 Delivery Method
    //Check Flat Rate
    private @FindBy(xpath = "(//input[@checked='checked'])[3]")
    WebElement chk_flatShipping;

    //Label Flat Shipping Rate
    private @FindBy(xpath = "//label[contains(.,'Flat Shipping Rate - $5.00')]")
    WebElement lbl_shippingRate;

    //Input Comentarios Orden Paso 4
    private @FindBy(xpath = "//textarea[@name='comment']")
    WebElement input_commentsOrder1;

    //Boton Continue Paso 4
    private @FindBy(xpath = "(//input[@value='Continue'])[3]")
    WebElement btn_continueFour;

    //Step 5 Payment Method
    //Check Bank Transfer
    private @FindBy(xpath = "(//input[@checked='checked'])[4]")
    WebElement chk_bankTransfer;

    //Check Cash On Devivery
    private @FindBy(xpath = "(//input[@type='radio'])[7]")
    WebElement chk_cashOnDelivery;

    //Input Comentarios Orden Paso 5
    private @FindBy(xpath = "(//textarea[@name='comment'])[2]")
    WebElement input_commentsOrder2;

    //Check Aceptar Terminos y Condiciones
    private @FindBy(xpath = "//input[@name='agree']")
    WebElement chk_termsAndConditions;

    //Boton Continue Paso 5
    private @FindBy(xpath = "(//input[@value='Continue'])[4]")
    WebElement btn_continueFive;

    //Step 6 Confirm Order

    //Texto con monto total de compra para iMac y Ipod Classic mas envio
    private @FindBy(xpath = "//td[@class='text-right'][contains(.,'$205.00')]")
    WebElement lbl_montoTotal;

    //Boton Confirmar Orden
    private @FindBy(xpath = "//input[@value='Confirm Order']")
    WebElement btn_confirmOrder;

    //Success Checkout
    //Texto orden exitosa
    private @FindBy(xpath = "//h1[contains(.,'Your order has been placed!')]")
    WebElement lbl_successOrder;

    //Link a mi Cuenta desde pagina compra exitosa
    private @FindBy(xpath = "//a[contains(.,'my account')]")
    WebElement link_myAccount;

    //Link historial de pedidos desde pagina compra exitosa
    private @FindBy(xpath = "//a[contains(.,'history')]")
    WebElement link_history;

    //Link descargar comprobante de pedido desde pagina compra exitosa
    private @FindBy(xpath = "//a[contains(.,'downloads')]")
    WebElement link_downloads;

    //Link contacto desde pagina compra exitosa
    private @FindBy(xpath = "(//font[contains(.,'propietario de la tienda')])[2]")
    WebElement link_contact;

    //Boton Continue para finalizar compra
    private @FindBy(xpath = "(//font[contains(.,'Continuar')])[2]")
    WebElement link_continueFinal;

    public void iniciarCheckout() {
        waitForWebElementAndClick(btn_iniciarCheckout);
    }

    public void validarCheckoutDesplegado() {
        waitForPageLoad();
        waitForElementVisible(text_checkout, 3);
    }

    public void completarPasoUno(String email, String password) {
        waitForElementVisible(chk_registerAccount, 3);
        chk_registerAccount.click();
        waitForElementVisibleAndContinue(input_User_Email, 3);
        input_User_Email.clear();
        sendKeys(input_User_Email, email);
        waitForElementVisibleAndContinue(input_User_Pwd, 3);
        input_User_Pwd.clear();
        sendKeys(input_User_Pwd, password);
        waitForWebElementAndClick(btn_Iniciar_Sesion);

    }

    public void completarPasoDos() {
        waitForWebElementAndClick(chk_existingAddress);
        waitForWebElementAndClick(btn_continueTwo);

    }

    public void completarPasoTres() {
        waitForWebElementAndClick(chk_addressDefault);
        waitForWebElementAndClick(btn_continueThree);

    }

    public void completarPasoCuatro() {
        waitForWebElementAndClick(chk_flatShipping);
        input_commentsOrder1.clear();
        sendKeys(input_commentsOrder1,"Prueba comentario entrega");
        waitForWebElementAndClick(btn_continueFour);

    }

    public void completarPasoCinco() {
        waitForWebElementAndClick(chk_cashOnDelivery);
        input_commentsOrder2.clear();
        sendKeys(input_commentsOrder2,"Prueba comentario pago");
        waitForWebElementAndClick(chk_termsAndConditions);
        waitForWebElementAndClick(btn_continueFive);

    }

    public void completarPasoSeis() {
        waitForWebElementAndClick(btn_confirmOrder);

    }

    public void validarOrdenExitosa() {
        Assert.assertTrue(lbl_successOrder.isDisplayed(), "Your order has been placed!");

    }

    public void irAHistorialDeOrdenes() {
        waitForWebElementAndClick(link_history);
    }

}