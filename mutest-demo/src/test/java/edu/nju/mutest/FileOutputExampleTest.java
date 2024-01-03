package edu.nju.mutest;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileOutputExampleTest {

    public static void main(String[] args) throws IOException {
        String srcPath = "S:\\File\\Study\\Software Testing\\fuzz-mut-demos-main\\mutest-demo\\src\\main\\java\\edu\\nju\\mutest\\example\\Calculator.java";
        File srcFile = new File(srcPath);
        CompilationUnit cu = StaticJavaParser.parse(srcFile);

        // Set the target file path
        String targetFilePath = "S:\\File\\Study\\Software Testing\\fuzz-mut-demos-main\\mutest-demo\\src\\test\\java\\edu\\nju\\mutest\\testOutput\\Output_1.java";

        // Convert CompilationUnit to String
        String content = cu.toString();

        // Write content to the target file
        try {
            boolean fileCreated = writeToFile(targetFilePath, content);
            if (fileCreated) {
                System.out.println("Content has been written to: " + targetFilePath);
            } else {
                System.err.println("Failed to create or write to the file.");
            }
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    private static boolean writeToFile(String filePath, String content) throws IOException {
        File targetFile = new File(filePath);

        // Create the file if it doesn't exist
        if (!targetFile.exists()) {
            boolean fileCreated = targetFile.createNewFile();
            if (!fileCreated) {
                return false;
            }
        }

        // Write the content to the file
        try (FileWriter writer = new FileWriter(targetFile)) {
            writer.write(content);
        }

        return true;
    }
}

