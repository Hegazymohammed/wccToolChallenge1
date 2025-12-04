package org.example;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * The {@code StandardInputProcessor} class provides support for executing
 * {@code wc} (word count) operations on data received from standard input
 * (i.e., piped input).
 *
 * <p>This class simulates behavior such as:
 * <pre>
 * echo "Hello World" | wc -w
 * </pre>
 *
 * <p>Supported operations:
 * <ul>
 *     <li>{@code -c} → count input size in bytes</li>
 *     <li>{@code -l} → count number of lines</li>
 *     <li>{@code -w} → count number of words</li>
 *     <li>{@code -m} → count number of characters</li>
 * </ul>
 *
 * <p>This class is intended to be used as a utility class.
 * All methods are static.
 *
 * @author Mohammed
 */
public class StandardInputProcessor {

    /**
     * Validates that the standard input follows the correct pipe syntax:
     *
     * <pre>
     * &lt;input&gt; | wc &lt;option&gt;
     * </pre>
     *
     * Example:
     * <pre>
     * "Hello World" | wc -w
     * </pre>
     *
     * @param parts the parsed command input
     * @throws IllegalArgumentException if the pipe sequence is invalid
     */
    public static void validatePipeSequence(String[] parts) {
        if (parts.length < 4) {
            throw new IllegalArgumentException("Invalid pipe format. Example: <input> | wc -w");
        }

        if (!"|".equals(parts[1])) {
            throw new IllegalArgumentException("Missing pipe operator '|'");
        }

        if (!"wc".equalsIgnoreCase(parts[2])) {
            throw new IllegalArgumentException("Unknown command. Only 'wc' is supported.");
        }
    }

    /**
     * Executes a {@code wc} operation using standard input.
     *
     * <p>This method assumes the following format:
     * <pre>
     * parts[0] → standard input text
     * parts[1] → |
     * parts[2] → wc
     * parts[3] → wc option (-c, -l, -w, -m)
     * </pre>
     *
     * @param parts the parsed standard input command
     * @throws IOException if an unexpected I/O error occurs
     * @throws IllegalArgumentException if the option is invalid
     */
    public static void executeWcFromStandardInput(String[] parts) throws IOException {
        validatePipeSequence(parts);

        String input = parts[0];
        String option = parts[3];

        switch (option) {
            case "-c" -> System.out.println(countBytes(input));
            case "-l" -> System.out.println(countLines(input));
            case "-w" -> System.out.println(countWords(input));
            case "-m" -> System.out.println(countCharacters(input));
            default -> throw new IllegalArgumentException("Invalid option: " + option);
        }
    }

    /* ================= CORE OPERATIONS ================= */

    /**
     * Counts the number of bytes in the standard input using UTF-8 encoding.
     *
     * @param input the standard input text
     * @return total size in bytes
     */
    private static long countBytes(String input) {
        return input.getBytes(StandardCharsets.UTF_8).length;
    }

    /**
     * Counts the number of lines in the standard input.
     *
     * @param input the standard input text
     * @return total number of lines
     */
    private static long countLines(String input) {
        return Arrays.stream(input.split("\n")).count();
    }

    /**
     * Counts the number of words in the standard input.
     *
     * <p>Words are separated using whitespace.</p>
     *
     * @param input the standard input text
     * @return total number of words
     */
    private static long countWords(String input) {
        return Arrays.stream(input.trim().split("\\s+"))
                .filter(word -> !word.isBlank())
                .count();
    }

    /**
     * Counts the total number of characters in the standard input.
     *
     * @param input the standard input text
     * @return total number of characters
     */
    private static long countCharacters(String input) {
        return input.length();
    }
}
