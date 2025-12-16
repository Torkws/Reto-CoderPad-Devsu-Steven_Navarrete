@ignore
Feature: Background común para PetStore

Scenario: Setup datos aleatorios
  * url baseUrl
  * def Faker = Java.type('com.github.javafaker.Faker')
  * def faker = new Faker()
  * def statuses = ['available', 'pending', 'sold']
  * def nombreAleatorio = faker.dog().name()
  * def statusAleatorio = statuses[Math.floor(Math.random() * statuses.length)]
  * def idAleatorio = Math.floor(Math.random() * 1000000)