package org.tombear.demo.guava.files;

import com.google.common.io.Closer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * <P>Descriptions</P>
 *
 * @author tombear on 2017-08-20 22:47.
 */
public class CloserClass {
    public static void main(String[] args) throws IOException {
        Closer closer = Closer.create();
        try {
            File destination = new File("src/main/resources/copy. txt");
            destination.deleteOnExit();

            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/sampleTextFileOne.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter(destination));
            closer.register(reader);
            closer.register(writer);

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
            }
        } catch (Throwable t) {
            throw closer.rethrow(t);
        } finally {

            closer.close();
        }

    }
}
