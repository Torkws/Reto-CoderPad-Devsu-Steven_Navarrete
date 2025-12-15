Feature: Añadir una nueva mascota a PetStore

  Background:
    * url baseUrl
    * def setupData = call read('backgroundPetStore.feature')
    * def nombreAleatorio = setupData.nombreAleatorio
    * def statusAleatorio = setupData.statusAleatorio
    * def idAleatorio = setupData.idAleatorio

    @test @crearMascota @happyPath @E01
  Scenario: Validar que permite añadir satisfactoriamente una nueva mascota
    Given path '/pet'
    And def requestBody = read('classpath:json/crearMascota.json')
    * set requestBody.name = nombreAleatorio
    * set requestBody.status = statusAleatorio
    * set requestBody.category.name = 'dog'
    * set requestBody.id = idAleatorio
    * print 'Request Body:', requestBody
    And request requestBody
    When method POST
    Then status 200
    * def JsonSchemaValidator = Java.type('utils.JsonSchemaValidator')
    * JsonSchemaValidator.validateOrFail(response, 'schemas/crearMascotaSchema.json')
    And match response.id == '#notnull'
    And match response.name == nombreAleatorio
    And match response.status == statusAleatorio


    @test @crearMascota @unhappyPath @E02
  Scenario: Validar que rechaza request con body vacío
    Given path '/pet'
    And request ''
    When method POST
    Then status 415
    And match response contains { message: '#string' }


