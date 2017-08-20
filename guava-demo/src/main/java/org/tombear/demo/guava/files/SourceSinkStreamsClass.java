package org.tombear.demo.guava.files;

import com.google.common.base.Charsets;
import com.google.common.io.BaseEncoding;
import com.google.common.io.ByteSink;
import com.google.common.io.ByteSource;
import com.google.common.io.ByteStreams;
import com.google.common.io.CharSink;
import com.google.common.io.CharStreams;
import com.google.common.io.Files;

import org.hamcrest.core.Is;
import org.junit.Assert;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

/**
 * <P>Descriptions</P>
 *
 * @author tombear on 2017-08-20 22:22.
 */
public class SourceSinkStreamsClass {

    /**
     * Byte，Char's Source 和 Sink
     * 包文件直接转码为BASE64文件
     *
     * @throws IOException 异常
     */
    public void BaseEncodingDemo() throws IOException {
        File file = new File("src/main/resources/sample.pdf");
        File encodedFile = new File("src/main/resources/encoded.txt");
        encodedFile.deleteOnExit();

        BaseEncoding baseEncoding = BaseEncoding.base64();

        ByteSource byteSource = Files.asByteSource(file);
        CharSink charSink = Files.asCharSink(encodedFile, Charsets.UTF_8);
        ByteSink byteSink = baseEncoding.encodingSink(charSink);
        byteSource.copyTo(byteSink);

        String encodedBytes = baseEncoding.encode(byteSource.read());
        Assert.assertThat(encodedBytes, Is.is(Files.toString(encodedFile, Charsets.UTF_8)));
    }

    /**
     * Limiting the size of InputStreams
     */
    public void StreamsDemo() throws IOException {
        File binaryFile = new File("src/main/resources/sample.pdf");
        ByteSource byteSource = Files.asByteSource(binaryFile);
        InputStream inputStream = byteSource.openBufferedStream();
        InputStream limitedInputStream = ByteStreams.limit(inputStream, 10);

        Assert.assertThat(limitedInputStream.available(), Is.is(10));
        Assert.assertThat(inputStream.available(), Is.is(218882));

        CharStreams.copy(new FileReader(""), new FileWriter(""));
    }
}
