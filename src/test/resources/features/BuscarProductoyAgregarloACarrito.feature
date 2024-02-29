@producto
Feature: Buscar producto y agregarlo al carrito
  Quiero buscar un Iphone en sitio y agregarlo a carrito de compras

  @producto
  Scenario: Buscar y agregar producto exitosamente
    Given Estoy en el home
    When Busco el producto "iPhone"
    And Selecciono el resultado
    And Agrego producto al carrito
    Then El producto se agrega correctamente