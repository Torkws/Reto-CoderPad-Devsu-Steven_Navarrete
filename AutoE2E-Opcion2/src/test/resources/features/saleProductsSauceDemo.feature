Feature: Funcionalidad de compra y gestión del carrito de productos en Sauce Demo

  Background:
    Given que el usuario navega a la página de inicio de sesión de Sauce Demo


  @test @cart @E01 @happyPath
  Scenario: Validar que permita agregar multiples productos al carrito correctamente
    Given el usuario inicia sesión con el usuario standard_user y contraseña secret_sauce
    And el usuario debería ser redirigido a la página de productos
    When agrega los siguientes productos al carrito:
      | producto               |
      | Sauce Labs Backpack    |
      | Sauce Labs Bolt T-Shirt|
      | Sauce Labs Onesie      |
    Then debería visualizarse los productos en el carrito

  @test @checkout @E02 @happyPath
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


  @test @E2E @E03 @happyPath
  Scenario Outline: Realiza compra exitosa de productos en SauceDemo
    Given el usuario inicia sesión con el usuario standard_user y contraseña secret_sauce
    And agrega los siguientes productos al carrito:
      | producto               |
      | Sauce Labs Backpack    |
    And se completa el proceso de llenado de formulario con los inputs <firstName>, <lastName> y <postalCode>
    When se procesa la confirmación de la compra
    Then debería visualizar la confirmación de la compra
    And debería ver el mensaje de compra exitosa Thank you for your order!
    Examples:
      | firstName | lastName | postalCode |
      | random    | random   | random     |


    @test @checkout @E04 @unhappyPath
    Scenario Outline: Validar que mensajes de error al completar el proceso de checkout con campos obligatorios invalidos
      Given el usuario inicia sesión con el usuario standard_user y contraseña secret_sauce
      And agrega los siguientes productos al carrito:
        | producto            |
        | Sauce Labs Backpack |
      When se completa el proceso de llenado de formulario con los inputs <firstName>, <lastName> y <postalCode>
      Then debería mostrar un mensaje de error en el formulario de checkout: <messageErrorExpected>
      Examples:
        | firstName | lastName | postalCode | messageErrorExpected           |
        |           | random   | random     | Error: First Name is required  |
        | random    |          | random     | Error: Last Name is required   |
        | random    | random   |            | Error: Postal Code is required |