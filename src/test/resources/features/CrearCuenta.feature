@cuenta
Feature: Crear cuenta
  Quiero crear una cuenta de usuario

  @crearCuentaExitosamente
  Scenario: Ver carrito, validar contenido y eliminarlo
    Given Estoy en el home
    When Voy a My Account y hago clic en opcion Register
    And Completo los datos personales
    And Creo la password
    And Indico si quiero suscribirme al boletin
    And Acepto los terminos & condiciones y creo la cuenta
    Then Valido que la cuenta se haya creado exitosamente