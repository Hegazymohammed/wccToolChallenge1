package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * The {@code FileInputProcessor} class provides a simplified implementation
 * of the Unix {@code wc} (word count) command.
 *
 * <p>It supports the following operations:
 * <ul>
 *     <li>{@code -c} → count file size in bytes</li>
 *     <li>{@code -l} → count number of lines</li>
 *     <li>{@code -w} → count number of words</li>
 *     <li>{@code -m} → count number of characters</li>
 * </ul>
 *
 * <p>This class is designed to be executed using command-line style arguments.
 *
 * <pre>
 * Example usage:
 * wc -l file.txt
 * wc -w file.txt
 * wc -c file.txt
 * wc -m file.txt
 * </pre>
 *
 * <p>All methods are static and the class is intended to be used as a utility class.
 *
 * @author Mohammed
 */
public class FileInputProcessor {

    /**
     * Executes a {@code wc}-like command based on the provided command-line arguments.
     *
     * <p>The expected argument format is:
     * <pre>
     * args[0] → command name (e.g., wc)
     * args[1] → option (-c, -l, -w, -m)
     * args[2] → file path
     * </pre>
     *
     * @param args command-line arguments
     * @throws IOException if an I/O error occurs while reading the file
     * @throws IllegalArgumentException if the arguments are invalid or unsupported
     */
    public static void executeWcCommand(String[] args) throws IOException {
        if (args.length < 3) {
            throw new IllegalArgumentException("Usage: wc <option> <file>");
        }

        String option = args[1];
        String filePath = args[2];

        Path path = validateAndGetPath(filePath);

        switch (option) {
            case "-c" -> System.out.println(getFileSize(path));
            case "-l" -> System.out.println(countLines(path));
            case "-w" -> System.out.println(countWords(path));
            case "-m" -> System.out.println(countCharacters(path));
            default -> throw new IllegalArgumentException("Invalid option: " + option);
        }
    }

    /* ================= CORE OPERATIONS ================= */

    /**
     * Returns the size of the file in bytes.
     *
     * @param path the path of the file
     * @return file size in bytes
     * @throws IOException if the file cannot be accessed
     */
    private static long getFileSize(Path path) throws IOException {
        return Files.size(path);
    }

    /**
     * Counts the number of lines in the file.
     *
     * @param path the path of the file
     * @return total number of lines
     * @throws IOException if an I/O error occurs while reading the file
     */
    private static long countLines(Path path) throws IOException {
        try (Stream<String> lines = Files.lines(path)) {
            return lines.count();
        }
    }

    /**
     * Counts the number of words in the file.
     *
     * <p>Words are determined by splitting each line using whitespace.
     *
     * @param path the path of the file
     * @return total number of words
     * @throws IOException if an I/O error occurs while reading the file
     */
    private static long countWords(Path path) throws IOException {
        try (Stream<String> lines = Files.lines(path)) {
            return lines
                    .flatMap(line -> Stream.of(line.trim().split("\\s+")))
                    .filter(word -> !word.isEmpty())
                    .count();
        }
    }

    /**
     * Counts the total number of characters in the file.
     *
     * <p>This includes line separator characters.
     *
     * @param path the path of the file
     * @return total number of characters
     * @throws IOException if an I/O error occurs while reading the file
     */
    private static long countCharacters(Path path) throws IOException {
        try (Stream<String> lines = Files.lines(path)) {
            return lines
                    .mapToLong(line -> line.length() + System.lineSeparator().length())
                    .sum();
        }
    }



    /**
     * Validates that the given file path exists and is a regular file.
     *
     * @param filePath the file path as a string
     * @return a valid {@link Path} object
     * @throws IllegalArgumentException if the file does not exist or is not a regular file
     */
    private static Path validateAndGetPath(String filePath) {
        Path path = Paths.get(filePath);

        if (!Files.exists(path)) {
            throw new IllegalArgumentException("File does not exist: " + filePath);
        }

        if (!Files.isRegularFile(path)) {
            throw new IllegalArgumentException("Not a valid file: " + filePath);
        }

        return path;
    }
}
