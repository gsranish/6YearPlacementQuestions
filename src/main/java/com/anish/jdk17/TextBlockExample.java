package com.anish.jdk17;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.util.Properties;

public class TextBlockExample {

    public static void main(String[] args) {
        try {
            TemplateService service = new TemplateService(args);
            String payload = service.generatePayload();

            System.out.println("Generated JSON Payload:");
            System.out.println(payload);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}


class TemplateService {

    private String templateType;

    public TemplateService(String[] args) throws IOException {
        // Load from config.properties by default
        Properties config = new Properties();
        config.load(Files.newBufferedReader(Path.of("application.properties"), StandardCharsets.UTF_8));

        // Default template type
        templateType = config.getProperty("template.type", "employee");

        // Override with environment variable if present
        String envTemplateType = System.getenv("TEMPLATE_TYPE");
        if (envTemplateType != null && !envTemplateType.isEmpty()) {
            templateType = envTemplateType;
        }

        // Override with command-line argument if provided
        if (args.length > 0 && args[0] != null && !args[0].isBlank()) {
            templateType = args[0];
        }
    }

    public String generatePayload() {
        try {
            String templatePath = switch (templateType) {
                case "employee" -> "employee_template.json";
                case "manager" -> "manager_template.json";
                default -> throw new IllegalArgumentException("Unsupported template type: " + templateType);
            };

            String template = Files.readString(Path.of(templatePath), StandardCharsets.UTF_8);

            if (templateType.equals("employee")) {
                String name = "Anish";
                String role = "Software Engineer";
                return template.formatted(name, role);

            } else { // manager
                String name = "Ravi";
                int teamSize = 15;
                return template.formatted(name, teamSize);
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to read template file", e);
        }
    }
}
