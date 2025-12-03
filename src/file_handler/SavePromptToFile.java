package file_handler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SavePromptToFile {
    // Generate date-time folder name like 2025-12-03_04-17-55
    private static final String dateTime = LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));

    public static void savePromptToFile(String path, String prompt) {
        try {
            Path filePath = Path.of(path);
            Path parent = filePath.getParent();

            // Create folders if missing
            if (parent != null && !Files.exists(parent)) {
                Files.createDirectories(parent);
            }

            // Write file (overwrites existing)
            Files.writeString(filePath, prompt, StandardCharsets.UTF_8);

        } catch (IOException e) {
            throw new RuntimeException("Failed to save prompt to file: " + path, e);
        }
    }

    public static String savePromptOrganized(
            String domainName,
            String promptType,
            String id,
            String prompt
    ) {


        // Build relative path
        String fullPath = String.format(
                "output/prompts/%s/%s/%s/%s_%s.txt",
                dateTime,
                domainName,
                promptType,
                domainName,
                id
        );

        // Save using existing function
        savePromptToFile(fullPath, prompt);

        // Return path for logging or reference
        return fullPath;
    }
}
