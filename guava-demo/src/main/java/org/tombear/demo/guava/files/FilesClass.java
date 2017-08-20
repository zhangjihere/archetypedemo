package org.tombear.demo.guava.files;

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * <P>Descriptions</P>
 *
 * @author tombear on 2017-08-20 21:58.
 */
public class FilesClass {

    public static void main(String[] args) throws IOException {
        HashFunction hashFunction = Hashing.sha1();
        HashCode wxzzs = hashFunction.hashString("wxzzs", Charsets.UTF_8);
        System.out.println(wxzzs);

    }

    public void someDemo() throws IOException {
        File original = new File("path/to/original");
        File copy = new File("path/to/copy");
        File newFile = new File("path/to/newFile");
        newFile.deleteOnExit();

        //copy file
        Files.copy(original, copy);
        //move file
        Files.move(original, newFile);
        //working with file as strings
        List<String> strings = Files.readLines(original, Charsets.UTF_8);
        //wich LineProcessor
        List<String> strings_process = Files.readLines(copy, Charsets.UTF_8, new ToListLineProcessor());
        //hashing a file
        HashCode hashCode_md5 = Files.hash(original, Hashing.md5());
        HashCode hashCode_sha256 = Files.hash(original, Hashing.sha256());
        //writing to a file, overwrite
        Files.write("some content", newFile, Charsets.UTF_8);
        //writing to a file, append
        Files.append("append content", newFile, Charsets.UTF_8);
    }

    //文件行处理器
    static class ToListLineProcessor implements LineProcessor<List<String>> {

        private static final Splitter splitter = Splitter.on(",");
        private List<String> bookTitles = Lists.newArrayList();
        private static final int TITLE_INDEX = 1;

        @Override
        public boolean processLine(String line) throws IOException {
            //获取文件每一行的由分隔符","分隔后的，第二个字段（索引为1）
            bookTitles.add(Iterables.get(splitter.split(line), TITLE_INDEX));
            return true;
        }

        @Override
        public List<String> getResult() {
            return bookTitles;
        }
    }
}