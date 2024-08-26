package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import utils.Constants_Vars;

import java.util.Random;

public class Login_PO extends Base_PO {

    // Metodo Constructor
    public Login_PO() {
        super();
    }

    //Boton My Account
    private @FindBy(xpath = "//span[contains(.,'My Account')]")
    WebElement btn_MyAccount;

    //Crear Cuenta
    //Boton Register
    private @FindBy(xpath = "(//a[contains(.,'Register')])[1]")
    WebElement btn_Register;

    //Etiqueta Account (para validar que estoy en pagina de crear cuenta)
    private @FindBy(xpath = "//h1[contains(.,'Account')]")
    WebElement lbl_account;

    //Formulario para indicar datos personales
    //Input First Name
    private @FindBy(xpath = "//input[@id='input-firstname']")
    WebElement input_firstName;

    //Input Last Name
    private @FindBy(xpath = "//input[@id='input-lastname']")
    WebElement input_lastName;

    //Input E-Mail
    private @FindBy(xpath = "//input[@id='input-email']")
    WebElement input_email;

    //Input Telephone
    private @FindBy(xpath = "//input[@id='input-telephone']")
    WebElement input_phone;

    //Formulario para indicar contraseña
    //Input Password
    private @FindBy(xpath = "//input[@id='input-password']")
    WebElement input_password;

    //Input Password Confirm
    private @FindBy(xpath = "//input[@id='input-confirm']")
    WebElement input_passwordConfirm;

    //Checkbox Suscripcion a boletin
    //Opcion Yes
    private @FindBy(xpath = "(//input[@type='radio'])[2]")
    WebElement chk_yes;

    //Opcion No
    private @FindBy(xpath = "(//input[@type='radio'])[3]")
    WebElement chk_no;

    //Checkbox Aceptar terminos y condiciones
    private @FindBy(xpath = "//input[@name='agree']")
    WebElement chk_aceptarTerminos;

    //Boton Continue (para crear cuenta)
    private @FindBy(xpath = "//input[@value='Continue']")
    WebElement btn_continue;

    //Registro exitoso
    //Texto que valida que cuenta se creo exitosamente
    private @FindBy(xpath = "//p[contains(.,'Congratulations! Your new account has been successfully created!')]")
    WebElement txt_successCreateAccount;

    //Iniciar sesion
    //Boton Login
    private @FindBy(xpath = "(//a[contains(.,'Login')])[1]")
    WebElement btn_Login;

    //E-mail Address
    private @FindBy(xpath = "//input[@id='input-email']")
    WebElement input_User_Email;

    //Input User Password
    private @FindBy(xpath = "//input[@id='input-password']")
    WebElement input_User_Pwd;

    //Boton Iniciar Sesion
    private @FindBy(xpath = "//input[@value='Login']")
    WebElement btn_Iniciar_Sesion;

    //Texto Post Login
    private @FindBy(xpath = "//h1[contains(.,'Checkout')]")
    WebElement lbl_Checkout;

    //Botón Logout
    private @FindBy(xpath = "(//a[contains(.,'Logout')])[1]")
    WebElement btn_LogOut;

    public void goToLoginPage () {
        navigateToUrl(Constants_Vars.URL_TEST);
    }

    public void irACrearCuenta() {
        waitForWebElementAndClick(btn_MyAccount);
        waitForElementClickeable(btn_Register, 3);
        btn_Register.click();
    }

    public String generateRandomEmail() {
        Random random = new Random();
        int randomNumber = random.nextInt(10000);
        return "user" + randomNumber + "@test.com";
    }

    public void completarDatosPersonales() {
        waitForElementVisible(lbl_account, 3);
        Assert.assertTrue(lbl_account.isDisplayed(), "Account");

        String randomEmail = generateRandomEmail();
        input_firstName.clear();
        sendKeys(input_firstName, Constants_Vars.REGISTER_FIRST_NAME);
        input_lastName.clear();
        sendKeys(input_lastName, Constants_Vars.REGISTER_LAST_NAME);
        input_email.clear();
        sendKeys(input_email, randomEmail);
        input_phone.clear();
        sendKeys(input_phone, Constants_Vars.REGISTER_PHONE);
    }

    public void crearPassword() {
        input_password.clear();
        sendKeys(input_password, Constants_Vars.REGISTER_PASSWORD);
        input_passwordConfirm.clear();
        sendKeys(input_passwordConfirm, Constants_Vars.REGISTER_PASSWORD);

    }

    public void suscribirseABoletin() {
        waitForWebElementAndClick(chk_no);

    }

    public void aceptarTerminosyCrearCuenta() {
        waitForWebElementAndClick(chk_aceptarTerminos);
        waitForWebElementAndClick(btn_continue);

    }

    public void validarCreacionExitosaDeCuenta() {
        waitForElementVisible(txt_successCreateAccount, 5);
        String msg = txt_successCreateAccount.getText();
        Assert.assertEquals(msg, "Congratulations! Your new account has been successfully created!");


    }

    public void login() {
        sendKeys(input_User_Email, Constants_Vars.USER_EMAIL);
        sendKeys(input_User_Pwd, Constants_Vars.USER_PASSWORD);
        waitForWebElementAndClick(btn_Iniciar_Sesion);

    }

    public void validarInicioSesion() {
        Assert.assertTrue(lbl_Checkout.isDisplayed(), "Checkout");
    }

    public void logout() {
        waitForWebElementAndClick(btn_MyAccount);
        waitForElementClickeable(btn_LogOut, 5);
        btn_LogOut.click();

    }

}

