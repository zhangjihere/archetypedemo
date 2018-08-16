package com.samsung.demo.digest;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by ji.zhang on 8/16/18.
 */
public class SHA256Test {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        long start = System.currentTimeMillis();
        System.out.println(shaString("GET\n" +
                "/\n" +
                "Action=ListUsers&Version=2010-05-08\n" +
                "content-type:application/x-www-form-urlencoded; charset=utf-8\n" +
                "host:iam.amazonaws.com\n" +
                "x-amz-date:20150830T123600Z\n" +
                "\n" +
                "content-type;host;x-amz-date\n" +
                "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855\n","SHA-256") + " " + (System.currentTimeMillis() - start));
        System.out.println(shaFile(SHA256Test.class.getResource("/test").getPath(), "SHA-256") + " " + (System.currentTimeMillis() - start));
    }

    static String shaString(String input, String type) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance(type);
        byte[] result = mDigest.digest(input.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte aResult : result) {
            sb.append(Integer.toString((aResult & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }

    static String shaFile(String file, String type) throws NoSuchAlgorithmException {
        MessageDigest sha1 = MessageDigest.getInstance(type);
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] data = new byte[1024];
            int read = 0;
            while ((read = fis.read(data)) != -1) {
                sha1.update(data, 0, read);
            }
            byte[] hashBytes = sha1.digest();

            StringBuffer sb = new StringBuffer();
            for (byte hashByte : hashBytes) {
                sb.append(Integer.toString((hashByte & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
