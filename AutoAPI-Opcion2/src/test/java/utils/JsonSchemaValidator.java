package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;

import java.io.InputStream;
import java.util.Set;

public class JsonSchemaValidator {

    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Valida un JSON contra un JSON Schema
     * @param jsonData JSON como String o Map
     * @param schemaPath Ruta del schema en classpath (ej: "schemas/mascotaSchema.json")
     * @return true si es válido, false si no
     */
    public static boolean validate(Object jsonData, String schemaPath) {
        try {
            // Convertir el JSON a JsonNode
            JsonNode jsonNode = mapper.valueToTree(jsonData);

            // Cargar el schema desde classpath
            InputStream schemaStream = JsonSchemaValidator.class.getClassLoader()
                    .getResourceAsStream(schemaPath);
            
            if (schemaStream == null) {
                String mensajeError = String.format(
                    "ERROR: No se pudo encontrar el archivo del schema JSON en la ruta: '%s'%n" +
                    "   Verifica que el archivo existe en la carpeta resources/schemas/", 
                    schemaPath
                );
                throw new RuntimeException(mensajeError);
            }

            JsonNode schemaNode = mapper.readTree(schemaStream);

            // Crear el validador
            JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7);
            JsonSchema schema = factory.getSchema(schemaNode);

            // Validar
            Set<ValidationMessage> errors = schema.validate(jsonNode);

            if (!errors.isEmpty()) {
                System.out.println("\n╔════════════════════════════════════════════════════════════╗");
                System.out.println("║  ERRORES DE VALIDACIÓN DEL SCHEMA JSON                    ║");
                System.out.println("╚════════════════════════════════════════════════════════════╝");
                System.out.println("Schema utilizado: " + schemaPath);
                System.out.println("Total de errores encontrados: " + errors.size());
                System.out.println("\nDetalle de los errores:\n");
                
                int contador = 1;
                for (ValidationMessage error : errors) {
                    System.out.println(String.format("  %d) %s", contador++, error.getMessage()));
                }
                System.out.println("\n═══════════════════════════════════════════════════════════\n");
                return false;
            }

            return true;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            String mensajeError = String.format(
                "ERROR CRÍTICO al validar el schema JSON:%n" +
                "   Tipo de error: %s%n" +
                "   Mensaje: %s%n" +
                "   Schema: %s",
                e.getClass().getSimpleName(),
                e.getMessage(),
                schemaPath
            );
            System.err.println(mensajeError);
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Valida y lanza excepción si falla
     * @param jsonData JSON como String o Map
     * @param schemaPath Ruta del schema en classpath
     */
    public static void validateOrFail(Object jsonData, String schemaPath) {
        try {
            JsonNode jsonNode = mapper.valueToTree(jsonData);
            InputStream schemaStream = JsonSchemaValidator.class.getClassLoader()
                    .getResourceAsStream(schemaPath);
            
            if (schemaStream == null) {
                String mensajeError = String.format(
                    "ERROR: No se encontró el archivo del schema '%s'%n" +
                    "Verifica que el archivo existe en resources/%s", 
                    schemaPath, schemaPath
                );
                throw new RuntimeException(mensajeError);
            }

            JsonNode schemaNode = mapper.readTree(schemaStream);
            JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7);
            JsonSchema schema = factory.getSchema(schemaNode);

            Set<ValidationMessage> errors = schema.validate(jsonNode);

            if (!errors.isEmpty()) {
                StringBuilder errorMsg = new StringBuilder();
                errorMsg.append("\n╔════════════════════════════════════════════════════════════╗\n");
                errorMsg.append("║  LA VALIDACIÓN DEL SCHEMA JSON FALLÓ                      ║\n");
                errorMsg.append("╚════════════════════════════════════════════════════════════╝\n");
                errorMsg.append(String.format("Schema: %s%n", schemaPath));
                errorMsg.append(String.format("Errores encontrados: %d%n%n", errors.size()));
                
                int contador = 1;
                for (ValidationMessage error : errors) {
                    errorMsg.append(String.format("  %d) %s%n", contador++, error.getMessage()));
                }
                errorMsg.append("═══════════════════════════════════════════════════════════\n");
                throw new AssertionError(errorMsg.toString());
            }
        } catch (AssertionError e) {
            throw e;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            String mensajeError = String.format(
                "ERROR CRÍTICO durante la validación del schema:%n" +
                "   Schema: %s%n" +
                "   Causa: %s",
                schemaPath,
                e.getMessage()
            );
            throw new RuntimeException(mensajeError, e);
        }
    }
}
