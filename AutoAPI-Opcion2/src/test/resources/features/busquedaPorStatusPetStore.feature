Feature: Buscar mascotas por status en PetStore

  Background:
    * url baseUrl
    * def setupData = call read('backgroundPetStore.feature')
    * def nombreAleatorio = setupData.nombreAleatorio
    * def idAleatorio = setupData.idAleatorio

  @test @busquedaPorStatus @happyPath @E06
  Scenario Outline: Validar búsqueda por status exitosa de mascota modificada previamente a sold
    * print '>>> Modificar una mascota a estado sold'
    * def resultadoModificar = call read('modificaMascotaPetStore.feature@modificarMascota')
    * def idMascotaModificada = resultadoModificar.idAleatorio
    * print 'ID de mascota modificada:', idMascotaModificada
    * print '>>> Buscar mascotas por status:', '<status>'
    Given path '/pet/findByStatus'
    And param status = '<status>'
    And header Accept = 'application/json'
    When method GET
    Then status 200
    And match response == '#array'
    And match response[*].status contains '<status>'
    * print 'Búsqueda exitosa, mascotas encontradas:', response.length
    * def mascotaEncontrada = response.find(x => x.id == idMascotaModificada)
    * print 'Mascota modificada encontrada en búsqueda:', mascotaEncontrada

    Examples:
      | status    |
      | sold      |
    


  @test @busquedaPorStatus @happyPath @E07
  Scenario Outline: Validar búsqueda exitosa de mascotas por status <status>
    * print '>>> Buscar mascotas por status:', '<status>'
    Given path '/pet/findByStatus'
    And param status = '<status>'
    And header Accept = 'application/json'
    When method GET
    Then status 200
    And match response == '#array'
    And match response[*].status contains '<status>'
    * print 'Búsqueda exitosa, mascotas encontradas:', response.length

    Examples:
      | status    |
      | available |
      | pending   |
      | sold      |