package stepDefinitions.base;

import pageObjects.*;
import utils.HelperMethods;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CrearCuentaSteps extends Base_PO {

    private Base_PO basePo;
    private Home_PO homePo;
    private Login_PO loginPo;

    public CrearCuentaSteps (Base_PO basePo, Home_PO homePo, Login_PO loginPo){
        this.basePo = basePo;
        this.homePo = homePo;
        this.loginPo = loginPo;
    }

    @When("Voy a My Account y hago clic en opcion Register")
    public void voy_a_my_account_y_hago_clic_en_opcion_register() {
        loginPo.irACrearCuenta();
        HelperMethods.takeScreenshot(basePo.getDriver(), "Registro en pagina My Account");

    }
    @When("Completo los datos personales")
    public void completo_los_datos_personales() {
        loginPo.completarDatosPersonales();
        HelperMethods.takeScreenshot(basePo.getDriver(), "Formulario con datos personales");

    }
    @When("Creo la password")
    public void creo_la_password() {
        loginPo.crearPassword();
        HelperMethods.takeScreenshot(basePo.getDriver(), "Formulario con datos contraseña");

    }
    @When("Indico si quiero suscribirme al boletin")
    public void indico_si_quiero_suscribirme_al_boletin() {
        loginPo.suscribirseABoletin();

    }
    @When("Acepto los terminos & condiciones y creo la cuenta")
    public void acepto_los_terminos_condiciones_y_creo_la_cuenta() {
        loginPo.aceptarTerminosyCrearCuenta();

    }
    @Then("Valido que la cuenta se haya creado exitosamente")
    public void valido_que_la_cuenta_se_haya_creado_exitosamente() {
        loginPo.validarCreacionExitosaDeCuenta();
        HelperMethods.takeScreenshot(basePo.getDriver(), "Creación de cuenta exitosa");

    }

}
