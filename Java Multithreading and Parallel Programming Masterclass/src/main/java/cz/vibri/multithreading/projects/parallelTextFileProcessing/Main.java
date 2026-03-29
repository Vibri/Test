package cz.vibri.multithreading.projects.parallelTextFileProcessing;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.concurrent.Callable;

/**
 * === REQUIREMENTS ===
 * 1. Periodically scans the ./src/main/resources directory and watches for new files
 * 2. for each file found into this directory, a new thread should take care of its processing
 * 3. Processing = each line of the file will be hashed using a hashing algorithm
 * 4. The program will create new files, marked _output suffix,and they will placed into ./src/main/output
 * 5. Throw an exception if a line is empty
 *
 */
public class Main {

    public static void main(String[] args) {
        new Thread(new Watcher()).start();
    }
}

class ExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        System.out.println("Exception handled: " + throwable.getMessage());
    }
}

class Watcher implements Runnable {

    @Override
    public void run() {
        File inputDirectory = new File("./src/main/resources");


        while(true) {

            if (inputDirectory.listFiles().length != 0) {
                Arrays.stream(inputDirectory.listFiles()).forEach(
                        file -> {
                            Thread t = new Thread(new FileProcessor(file));
                            t.setUncaughtExceptionHandler(new ExceptionHandler());
                            t.start();
                        }
                );
            }

            sleep();
        }
    }

    private void sleep() {
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class FileProcessor implements Runnable {

    private final File file;
    private static final String OUTPUT_PATH = "./src/main/output/";

    FileProcessor(File file) {
        this.file = file;
    }

    @Override
    public void run() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_PATH + file.getName() + "_output"));

            Files.lines(Path.of(file.getCanonicalPath()))
                    .map(this::hash)
                    .map(line -> line + System.lineSeparator())
                    .forEach(el -> {
                        try {
                            writer.write(el);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " processed file: " + file );
    }

    private String hash(String input) {
        if (input.equals("")) {
            throw new RuntimeException("The line is empty, hashing cannot be done");
        }
        final MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA3-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
        final byte[] hashbytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(hashbytes);
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2*hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}