package org.soumava.java.hsbc;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class ReverseASCIIContents {

    public static void main(String[] args) {
        System.out.println("Read an ascii input file, reverse the contents and write the reversed contents to an output file.");
        String inputFileName = args[0];
        String outputFileName = args[1];

        try {
            ReverseASCIIContents reverseASCIIContents = new ReverseASCIIContents();
            reverseASCIIContents.reverseContent(inputFileName, outputFileName);
        } catch (Exception e) {
            System.err.println("Error while processing: " + e);
        }
    }

    private boolean isPureAscii(String data) {
        return StandardCharsets.US_ASCII.newEncoder().canEncode(data);
    }

    public void reverseContent(String inputFileName, String outputFileName) throws IOException, RuntimeException {
        System.out.println("Read data from: " + inputFileName);
        List<String> outputString = new LinkedList<>();
        try (Stream<String> linesStream = Files.lines(Paths.get(inputFileName))) {
            linesStream.forEach(line -> {
                System.out.println("Actual line: " + line);
                if (isPureAscii(line)) {
                    String reverseLine = new StringBuilder(line).reverse().toString();
                    System.out.println("Reversed Line: " + reverseLine);
                    outputString.add(reverseLine);
                } else {
                    System.err.println("Incorrect non ASCII characters in line: ");
                    System.exit(1);
                }
            });
        }
        System.out.println("Write the reverse data to: " + outputFileName);
        outputString.forEach(line -> {
            try {
                Files.writeString(Paths.get(outputFileName), line + System.lineSeparator(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}