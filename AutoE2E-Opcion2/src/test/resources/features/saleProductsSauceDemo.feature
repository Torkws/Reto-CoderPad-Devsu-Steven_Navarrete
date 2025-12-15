Feature: Funcionalidad de compra y gestión del carrito de productos en Sauce Demo

  Background:
    Given que el usuario navega a la página de inicio de sesión de Sauce Demo

#  @test @E2E @E01 @happyPath
#  Scenario: Compra exitosa de productos en SauceDemo
#    Given el usuario se autentica con credenciales válidas
#    And se encuentra en la página de productos
#    When agrega dos productos al carrito
#    And visualiza el carrito de compras
#    And completa el formulario de checkout con datos válidos
#    And finaliza la compra
#    Then debería visualizar la confirmación de la orden
#    And debería ver el mensaje "THANK YOU FOR YOUR ORDER"

  @test @cart
  Scenario: Validar que permita agregar productos al carrito correctamente
    Given el usuario inicia sesión con el usuario standard_user y contraseña secret_sauce
    And el usuario debería ser redirigido a la página de productos
    When agrega los siguientes productos al carrito:
      | producto               |
      | Sauce Labs Backpack    |
      | Sauce Labs Bolt T-Shirt|
      | Sauce Labs Onesie      |
    Then debería visualizarse los productos en el carrito

  @test @checkout
  Scenario Outline: Validar que permita completar el proceso de checkout correctamente
    Given el usuario inicia sesión con el usuario standard_user y contraseña secret_sauce
    And agrega los siguientes productos al carrito:
      | producto               |
      | Sauce Labs Backpack    |
      | Sauce Labs Bolt T-Shirt|
    When se completa el proceso de llenado de formulario con los inputs <firstName>, <lastName> y <postalCode>
    Then debería permitir realizar la confirmación de la orden
    Examples:
      | firstName | lastName | postalCode |
      | random    | random   | random     |
