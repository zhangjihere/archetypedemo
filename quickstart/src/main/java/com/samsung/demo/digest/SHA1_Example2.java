package com.samsung.demo.digest;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by ji.zhang on 8/9/17.
 */
public class SHA1_Example2 {

    /**
     * @param args
     * @throws NoSuchAlgorithmException
     */
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        long start = System.currentTimeMillis();
//        System.out.println(sha1String("wxzzs") + " " + (System.currentTimeMillis() - start));
        System.out.println(sha1File("/tmp/cp4cc/0d1e48f8-b34d-4c9f-8f2a-dd907e2e8cd8.draw") + " " + (System.currentTimeMillis() - start));
    }

    static String sha1String(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte aResult : result) {
            sb.append(Integer.toString((aResult & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }

    static String sha1File(String file) throws NoSuchAlgorithmException {
        MessageDigest sha1 = MessageDigest.getInstance("SHA-256");
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] data = new byte[1024];
            int read = 0;
            while ((read = fis.read(data)) != -1) {
                sha1.update(data, 0, read);
            }
            byte[] hashBytes = sha1.digest();

            StringBuffer sb = new StringBuffer();
            for (byte hashByte : hashBytes) {
                String s = Integer.toString((hashByte & 0xff) , 16);
                sb.append(s);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
