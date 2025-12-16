Feature: Buscar mascota por ID en PetStore

  Background:
    * url baseUrl
    * def setupData = call read('backgroundPetStore.feature')
    * def idAleatorio = setupData.idAleatorio
    * def nombreAleatorio = setupData.nombreAleatorio
    * def statusAleatorio = setupData.statusAleatorio
    

  

  @test @busquedaMascota @happyPath @E03
  Scenario: Validar búsqueda exitosa de mascota con un ID nuevo
    Given path '/pet'
    And def requestBody = read('classpath:json/crearMascota.json')
    * set requestBody.name = nombreAleatorio
    * set requestBody.status = statusAleatorio
    * set requestBody.category.name = 'dog'
    * set requestBody.id = idAleatorio
    And request requestBody
    When method POST
    Then status 200
    * def idNuevo = response.id
    * print 'Mascota creada con ID:', idNuevo
    Given path '/pet/' + idNuevo
    And header Accept = 'application/json'
    When method GET
    Then status 200
    * def JsonSchemaValidator = Java.type('utils.JsonSchemaValidator')
    * JsonSchemaValidator.validateOrFail(response, 'schemas/crearMascotaSchema.json')
    And match response.id == idNuevo
    And match response.name == nombreAleatorio
    And match response.status == statusAleatorio


  @test @busquedaMascota @unhappyPath @E04
  Scenario Outline: Validar casos de error al buscar mascota por ID
    Given path '/pet/' + <petId>
    And header Accept = 'application/json'
    When method GET
    Then status <expectedStatus>
    * def JsonSchemaValidator = Java.type('utils.JsonSchemaValidator')
    * JsonSchemaValidator.validateOrFail(response, 'schemas/errorPetStore.json')
    And match response.code == 1
    And match response.type == 'error'
    And match response.message == 'Pet not found'
    Examples:
      | petId               | expectedStatus | 
      | 999999999999        | 404           | 
      | 0                   | 404           | 
      | -1                  | 404           | 
