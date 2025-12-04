package org.example;

import java.io.IOException;

/**
 * The {@code Main} class is the entry point for the WC (Word Count) application.
 *
 * <p>This application supports two execution modes:
 *
 * <ul>
 *     <li><b>File mode:</b> {@code wc -l file.txt}</li>
 *     <li><b>Pipe mode:</b> {@code "Hello World" | wc -w}</li>
 * </ul>
 *
 * <p>The application automatically determines the execution mode
 * based on the presence of the pipe operator {@code |}.
 *
 * @author Mohammed
 */
public class Main {

    /**
     * Application entry point.
     *
     * @param args command-line arguments
     * @throws IOException if an I/O error occurs during processing
     */
    public static void main(String[] args) throws IOException {
        validateArgs(args);


        if ("|".equals(args[1])) {
            StandardInputProcessor.executeWcFromStandardInput(args);
        }

        else {
            FileInputProcessor.executeWcCommand(args);
        }
    }

    /**
     * Validates the minimum required command-line arguments.
     *
     * @param args command-line arguments
     * @throws IllegalArgumentException if arguments are missing or invalid
     */
    private static void validateArgs(String[] args) {
        if (args == null || args.length < 3) {
            throw new IllegalArgumentException(
                    "Invalid arguments.\n" +
                            "File mode: wc -w file.txt\n" +
                            "Pipe mode: \"Hello World\" | wc -w"
            );
        }
    }
}
