package com.samsung.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

/**
 * this demo show how to run CLI command in JAVA
 *
 * Created by ji.zhang on 8/2/17.
 */
public class RunCommandLineDemo {

    public static void main(String[] args) {
        String directory = System.getenv("HOME");
        String command = "find ./ -type d";
        System.out.println(String.format("Java run CLI, directory: %s,\ncommand: %s\n", directory, command));

        runCLI(directory, "stdout.cli", command);
    }

    /**
     * run CLI command based on directory.
     *
     * @param directory CLI working directory
     * @param logFile   log file is in directory param
     * @param command   Such as "ping","ls -lrt" or "./server"
     */
    public static void runCLI(String directory, String logFile, String command) {
        String splitPattern = " +|\t+";
        ProcessBuilder pb = new ProcessBuilder(command.trim().split(splitPattern)).directory(new File(directory));
        ProcessBuilder.Redirect redirectFile = redirectLog2File(directory, logFile);
        pb.redirectOutput(redirectFile);
        pb.redirectError(redirectFile);
        try {
            Process p = pb.start();
//            startCliPrintListener(p);
            Runtime.getRuntime().addShutdownHook(new Thread(p::destroy));//add hook for kill CLI process
            if (p.waitFor() != 0) { // Here, it will block to wait process finished.
                System.err.println("running command do not exit normal. exitValue: " + p.exitValue());
                System.err.println("ready to shutdown JVM, clear process");
            } else {
                System.out.println("running command exit normal. exitValue: " + p.exitValue());
                System.out.println("ready to shutdown JVM, clear process");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static ProcessBuilder.Redirect redirectLog2File(String directory, String logFile) {
        Path path = Paths.get(directory + "/" + logFile);
        if (!Files.exists(path)) {
            try {
                Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rw-r--r--");
                FileAttribute<Set<PosixFilePermission>> fileAttrs = PosixFilePermissions.asFileAttribute(perms);
                Files.createFile(path, fileAttrs);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ProcessBuilder.Redirect.appendTo(path.toFile());
    }

    /**
     * prepare and start CLI print Listener
     *
     * @param p a CLI process
     */
    @Deprecated
    private static void startCliPrintListener(Process p) {
        new Thread(() -> new BufferedReader(new InputStreamReader(p.getInputStream())).lines().forEach(System.out::println), "listenCliPrint").start();
        new Thread(() -> new BufferedReader(new InputStreamReader(p.getErrorStream())).lines().forEach(System.out::println), "listenCliErrPrint").start();
    }

}
