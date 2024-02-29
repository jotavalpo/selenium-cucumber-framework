@producto
Feature: Ver carrito de compras
  Quiero ver el carrito de compras y ejecutar distintas acciones en él

  @producto
  Scenario: Ver carrito, validar contenido y eliminarlo
    Given Estoy en el home
    When Busco el producto "iPhone"
    And Selecciono el resultado
    And Agrego producto al carrito
    And Entro al carrito de compras desde el home y veo producto
    And Valido que producto este agregado
    And Elimino producto de carrito
    Then Valido que producto ya no este agregado