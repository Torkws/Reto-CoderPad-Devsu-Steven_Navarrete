Feature: Funcionalidad de Login para E-commerce de Sauce Demo

  Background:
    Given que el usuario navega a la página de inicio de sesión de Sauce Demo

  @test @login @E01 @happyPath
  Scenario Outline: Inicio de sesión exitoso con credenciales válidas
    When el usuario inicia sesión con el usuario <user> y contraseña <pass>
    Then el usuario debería ser redirigido a la página de productos
    And el inventario de productos debería ser visible
    Examples:
      | user                    | pass         |
      | standard_user           | secret_sauce |
      | problem_user            | secret_sauce |
      | performance_glitch_user | secret_sauce |
      | locked_out_user         | secret_sauce |


