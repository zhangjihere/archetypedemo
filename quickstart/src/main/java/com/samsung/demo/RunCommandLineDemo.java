package com.samsung.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * this demo show how to run CLI command in JAVA
 *
 * Created by ji.zhang on 8/2/17.
 */
public class RunCommandLineDemo {

    public static void main(String[] args) {
        String directory = System.getenv("HOME");
        String command = "ls -lrt";
        System.out.println(String.format("Java run CLI, directory: %s,\ncommand: %s\n", directory, command));

        new RunCommandLineDemo().runCLI(directory, command);
    }

    /**
     * run CLI command based on directory.
     *
     * @param directory CLI working directory
     * @param command   such as "ping","ls -lrt" or "./server"
     */
    private void runCLI(String directory, String command) {
        String splitPattern = " +|\t+";
        ProcessBuilder pb = new ProcessBuilder(command.split(splitPattern)).directory(new File(directory));

//        pb.environment().forEach((k, v) -> System.out.println(k + ": " + v));
        try {
            Process p = pb.start();
            startCliPrintListener(p);
            Runtime.getRuntime().addShutdownHook(new Thread(p::destroy));//add hook for kill CLI process
            if (p.waitFor() != 0) {
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

    /**
     * prepare and start CLI print Listener
     *
     * @param p a CLI process
     */
    private void startCliPrintListener(Process p) {
        Thread cliPrint = new Thread(new CliPrintTask(p.getInputStream()), "listenCliPrint");
        Thread cliErrPrint = new Thread(new CliPrintTask(p.getErrorStream()), "listenCliErrPrint");
        cliPrint.start();
        cliErrPrint.start();
    }

    /**
     * this task is used for listening the CLI print content.
     */
    class CliPrintTask implements Runnable {

        private InputStream inputStream;

        private CliPrintTask(InputStream inputStream) {
            this.inputStream = inputStream;
        }

        public void run() {
            new BufferedReader(new InputStreamReader(inputStream)).lines().forEach(System.out::println);
        }
    }

}
