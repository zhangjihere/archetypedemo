package com.samsung.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * this demo show how to run CLI command in JAVA
 * Created by ji.zhang on 8/2/17.
 */
public class RunCommandLineDemo {

    public static void main(String[] args) {
        String directory = "/home/zhangji/Downloads/ice-demos-master/cpp98/Ice/hello/";
        String command = "./server";
        System.out.println(String.format("Java run CLI, directory: %s,\ncommand: %s\n", directory, command));
        RunCommandLineDemo cliDemo = new RunCommandLineDemo();
        cliDemo.runCLI(directory, command);
    }

    /**
     * run CLI command based on directory.
     * @param directory CLI working directory
     * @param command such as "ping","localhost" or "./server"
     */
    private void runCLI(String directory, String... command) {
        ProcessBuilder pb = new ProcessBuilder(command).directory(new File(directory));
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

        public CliPrintTask(InputStream inputStream) {
            this.inputStream = inputStream;
        }

        public void run() {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
                String line = br.readLine();
                while (line != null) {
                    System.out.println("output: " + line);
                    line = br.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
