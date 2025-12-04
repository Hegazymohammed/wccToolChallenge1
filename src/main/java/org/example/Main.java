package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {


        Scanner pathInput=new Scanner(System.in);
        String text =pathInput.nextLine();

        String [] parts=validateInput(text);
        String options=parts[1];

        switch (options) {
            case  ( "-c"):
                Long length = getFileSize(parts[2]);
                System.out.println(length);
                break;
             case  ( "-l"):
                Long numberOfLine = countNumberOfLinesInFile(parts[2]);
                System.out.println(numberOfLine);
                break;
            case  ( "-w") :
                Long numberOfLines = countNumberOfLinesInFile(parts[2]);
                System.out.println(numberOfLines);
                break;
            case  ( "-m"):
                long totalChars = countNumberOfCharacterInFile(parts[2]);
                System.out.println(totalChars);
                break;


        }



    }


    public static String[] validateInput(String input){
         if(Objects.isNull(input)||input.isEmpty())
             throw new RuntimeException("Please enter the file that you want to process");
         String[] parts=input.split(" ");
        if(parts.length<1)
            throw new RuntimeException("Please enter your choice and the file that you want to process");
        else if(parts.length<2)
            throw new RuntimeException("Please enter the file that you want to process");
        if(!parts[0].equals("ccwc"))
            throw new RuntimeException("The commend is not correct Enter valid commend");
        return parts;
    }


    public static String trim(String input){
         return input.replaceAll(" ","");
    }
    public static Integer countWords(String input){
        if(input.isEmpty())
            return 0;
       return  input.length();
    }


    public static Long getFileSize(String filePath) throws IOException {
        Path path =getPath(filePath);
        return Files.size(path);

    }

    public static  Long countNumberOfLinesInFile(String filePath) throws IOException {
        Path path =getPath(filePath);
        return Files.lines(path).count();
    }

    public  static Path getPath(String filePath) {
        Path path= Paths.get(filePath);
    if (!Files.exists(path)) {
            throw new RuntimeException("File does not exist: " + filePath);
        }
    return path;
    }

    public static Long countNumberOfWordsInFile(String filePath) throws IOException {
        Path path =getPath(filePath);
       return Files.readAllLines(path).stream().map(Main::trim).count();
    }

    public static long countNumberOfCharacterInFile(String filePath) throws IOException {
        Path path =getPath(filePath);
        long[] totals = new long[2]; // totals[0] = char sum, totals[1] = line count
        try (java.util.stream.Stream<String> stream = Files.lines(path)) {
            stream.forEach(line -> {
                totals[0] += line.length(); // or use line.codePointCount(0, line.length()) for Unicode code points
                totals[1] += 1;
            });
        }
        long lineSepLen = System.lineSeparator().length();
        long newlines = Math.max(0, totals[1] - 1) * lineSepLen;
        long totalChars = totals[0] + newlines;
        return totalChars;
    }

}