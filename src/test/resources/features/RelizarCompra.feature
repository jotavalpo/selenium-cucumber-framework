@compra
Feature: Realizar compra
  Quiero buscar un producto en el sitio, agregarlo a carrito de compras y luego comprarlo

  @compraExitosa
  Scenario: Compra exitosa
    Given Estoy en el home
    When Busco el producto "iPhone"
    And Selecciono el resultado
    And Agrego producto al carrito
    And El producto se agrega correctamente
    And Ingreso al checkout
    And Inicio sesion usando las credenciales del archivo "src/test/resources/credenciales.csv"
    And Indico la direccion de facturacion
    And Indico la direccion de entrega
    And Indico el metodo de envio
    And Indico el metodo de pago
    And Finalizo el proceso de compra de manera exitosa
    And Voy al historial de compras
    Then Valido que compra quede en estado pendiente