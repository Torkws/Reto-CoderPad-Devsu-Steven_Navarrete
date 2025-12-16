Feature: Modificar una mascota existente en PetStore

  Background:
    * url baseUrl
    * def setupData = call read('backgroundPetStore.feature')
    * def nombreAleatorio = setupData.nombreAleatorio
    * def statusAleatorio = setupData.statusAleatorio
    * def idAleatorio = setupData.idAleatorio

  @test @modificarMascota @happyPath @E05
  Scenario: Validar que permite modificar el nombre y estado de una mascota existente
    * print '>>> Crear una mascota inicial'
    Given path '/pet'
    And def requestBody = read('classpath:json/crearMascota.json')
    * set requestBody.name = nombreAleatorio
    * set requestBody.status = 'available'
    * set requestBody.category.name = 'dog'
    * set requestBody.id = idAleatorio
    And request requestBody
    And header Accept = 'application/json'
    When method POST
    Then status 200
    * def mascotaCreada = response
    * print 'Mascota creada:', mascotaCreada
    * print '>>> Modificar el nombre y estado a vendido'
    Given path '/pet'
    * def nuevoNombre = 'Sprunkies'
    * set mascotaCreada.name = nuevoNombre
    * set mascotaCreada.status = 'sold'
    * print 'Request Body modificado:', mascotaCreada
    And request mascotaCreada
    And header Accept = 'application/json'
    When method PUT
    Then status 200
    * def JsonSchemaValidator = Java.type('utils.JsonSchemaValidator')
    * JsonSchemaValidator.validateOrFail(response, 'schemas/crearMascotaSchema.json')
    And match response.id == idAleatorio
    And match response.name == nuevoNombre
    And match response.status == 'sold'
    * print 'Mascota modificada exitosamente'
