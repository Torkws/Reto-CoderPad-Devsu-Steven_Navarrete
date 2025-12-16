# Automatización API - Swagger PetStore

Reto de Steven Navarrete para Devsu - proyecto de automatización de pruebas API para la plataforma [Swagger PetStore](https://petstore.swagger.io/) utilizando Karate Framework.

##  Prerrequisitos

- Java 17
- Maven 3.6+

##  Escenarios de Prueba

### Feature: Añadir una nueva mascota a PetStore
- E01 - Crear mascota exitosamente (@crearMascota @happyPath)
- E02 - Validar rechazo con body vacío (@crearMascota @unhappyPath)

### Feature: Buscar mascota por ID en PetStore
- E03 - Búsqueda exitosa por ID (@busquedaMascota @happyPath)
- E04 - Validación de errores en búsqueda por ID (@busquedaMascota @unhappyPath)

### Feature: Modificar una mascota existente en PetStore
- E05 - Modificar nombre y estado de mascota (@modificarMascota @happyPath)

### Feature: Buscar mascotas por status en PetStore
- E06 - Búsqueda por status después de modificación (@busquedaPorStatus @happyPath)
- E07 - Búsqueda exitosa por diferentes status (@busquedaPorStatus @happyPath)

##  Ejecución de Pruebas

### Ejecutar todas las pruebas
```bash
mvn clean test "-Dkarate.options=--tags @test"
```

### Ejecutar por tags específicos

**Pruebas de creación de mascotas:**
```bash
mvn clean test "-Dkarate.options=--tags @crearMascota"
```

**Pruebas de búsqueda de mascotas:**
```bash
mvn clean test "-Dkarate.options=--tags @busquedaMascota"
```

**Pruebas de modificación de mascotas:**
```bash
mvn clean test "-Dkarate.options=--tags @modificarMascota"
```

**Pruebas de búsqueda por status:**
```bash
mvn clean test "-Dkarate.options=--tags @busquedaPorStatus"
```

**Pruebas Happy Path:**
```bash
mvn clean test "-Dkarate.options=--tags @happyPath"
```

**Pruebas Unhappy Path:**
```bash
mvn clean test "-Dkarate.options=--tags @unhappyPath"
```

## 🏷️ Tags Disponibles

| Tag | Descripción |
|-----|-------------|
| `@test` | Todas las pruebas |
| `@crearMascota` | Pruebas de creación de mascotas |
| `@busquedaMascota` | Pruebas de búsqueda por ID |
| `@modificarMascota` | Pruebas de modificación de mascotas |
| `@busquedaPorStatus` | Pruebas de búsqueda por status |
| `@happyPath` | Escenarios exitosos |
| `@unhappyPath` | Escenarios de validación de errores |
| `@E01` a `@E07` | Escenarios individuales |

## 📁 Estructura del Proyecto

```
AutoAPI-Opcion2/
├── src/
│   ├── main/
│   │   └── resources/
│   └── test/
│       ├── java/
│       │   ├── runnerTest.java          # Runner principal de pruebas
│       │   └── utils/
│       │       └── JsonSchemaValidator.java  # Validador de esquemas JSON
│       └── resources/
│           ├── karate-config.js         # Configuración global de Karate
│           ├── features/                # Archivos .feature de Karate
│           │   ├── añadirMascotaPetStore.feature
│           │   ├── backgroundPetStore.feature
│           │   ├── busquedaMascotaPetStore.feature
│           │   ├── busquedaPorStatusPetStore.feature
│           │   └── modificaMascotaPetStore.feature
│           ├── json/                    # Templates de request bodies
│           │   └── crearMascota.json
│           └── schemas/                 # Esquemas JSON para validación
│               ├── crearMascotaSchema.json
│               └── errorPetStore.json
├── target/
│   └── karate-reports/                  # Reportes HTML generados
└── pom.xml
```

##  Reportes

Después de ejecutar las pruebas, los reportes se generan automáticamente en:
- **HTML Report:** `target/karate-reports/karate-summary.html`
- **JSON Report:** `target/karate-reports/karate-summary-json.txt`
- **Timeline:** `target/karate-reports/karate-timeline.html`

Abrir el reporte principal:
```bash
# Windows
start target/karate-reports/karate-summary.html

# Linux/Mac
open target/karate-reports/karate-summary.html
```

## 🔧 Características Técnicas

- **Framework:** Karate 1.4.1
- **Validación de Esquemas:** JSON Schema validation personalizada
- **Gestión de Datos:** Background features para datos aleatorios
- **Headers:** Accept application/json para respuestas JSON
- **Assertions:** Validaciones de schema, campos específicos y arrays
- **Scenario Outline:** Tests parametrizados con Examples

##  API Base URL

Por defecto, las pruebas se ejecutan contra:
```
https://petstore.swagger.io/v2
```

La URL se configura en `karate-config.js` y puede ser modificada según el ambiente.

##  Validaciones Implementadas

- ✅ Validación de esquemas JSON con JsonSchemaValidator
- ✅ Validación de códigos de estado HTTP
- ✅ Validación de campos específicos en respuestas
- ✅ Validación de arrays y colecciones
- ✅ Manejo de datos aleatorios
- ✅ Reutilización de features con call read()

##  CI/CD

El proyecto incluye GitHub Actions workflow para ejecución automatizada:
- Trigger: Push a main/AutoAPIKarate o Pull Requests
- Java 17 con Maven
- Generación y publicación de reportes
- Retención de artefactos por 30 días

---
**Proyecto:** Reto Devsu - Automatización API  
**Autor:** Steven Navarrete  
**Framework:** Karate  
**Fecha:** Diciembre 2025
