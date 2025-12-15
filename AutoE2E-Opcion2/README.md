# Automatización E2E - Sauce Demo

Reto de Steven Navarrete para Devsu - proyecto de automatización de pruebas E2E  para la plataforma [Sauce Demo](https://www.saucedemo.com/) utilizando Serenity BDD, Cucumber y Selenium WebDriver.

## 📋 Prerrequisitos

- Java 17 
- Maven 3.6+
- Navegadores: Chrome, Firefox o Edge

## 🧪 Escenarios de Prueba

### Feature: Funcionalidad de Login
- E01 - Inicio de sesión exitoso (@login @happyPath)
- E02 - Validación de errores de login (@login @unhappyPath)

### Feature: Funcionalidad de Compra y Gestión del Carrito
- E01 - Agregar múltiples productos al carrito (@cart @happyPath)
- E02 - Completar proceso de checkout (@checkout @happyPath)
- E03 - Compra exitosa E2E (@E2E @happyPath)
- E04 - Validación de campos obligatorios en checkout (@checkout @unhappyPath)

## 🚀 Ejecución de Pruebas

### Ejecutar todas las pruebas
```bash
mvn clean verify -Denvironment=chrome "-Dcucumber.filter.tags=@test"
```

### Ejecutar por tags específicos

**Pruebas de Login:**
```bash
mvn clean verify -Denvironment=chrome "-Dcucumber.filter.tags=@login"
```

### Ejecutar en diferentes navegadores

**Firefox:**
```bash
mvn clean verify -Denvironment=firefox "-Dcucumber.filter.tags=@checkout"
```

**Edge:**
```bash
mvn clean verify -Denvironment=edge "-Dcucumber.filter.tags=@checkout"
```


## 🏷️ Tags Disponibles

| Tag | Descripción |
|-----|-------------|
| `@test` | Todas las pruebas |
| `@login` | Pruebas de autenticación |
| `@cart` | Pruebas de carrito de compras |
| `@checkout` | Pruebas de proceso de checkout |
| `@E2E` | Pruebas end-to-end completas |
| `@happyPath` | Escenarios exitosos |
| `@unhappyPath` | Escenarios de validación de errores |
| `@E01`, `@E02`, `@E03`, `@E04` | Escenarios individuales |


## 📁 Estructura del Proyecto

```
AutoE2E-Opcion2/
├── src/
│   ├── main/
│   │   └── java/
│   └── test/
│       ├── java/devsu/         # Step definitions y código de pruebas
│       └── resources/
│           ├── features/       # Archivos .feature de Cucumber
│           └── serenity.conf   # Configuración de Serenity
├── target/
│   └── site/serenity/          # Reportes generados
└── pom.xml
```

