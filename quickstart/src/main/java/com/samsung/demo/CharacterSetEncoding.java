package com.samsung.demo;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * 字符集和字符编码的差异比较
 * Created by ji.zhang on 7/9/18.
 */
public class CharacterSetEncoding {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String s = "中泸ABΓ&=¥";
        pass(s);

        System.out.println("----------");
        String s2 = "中AΓ&Ж=¥¥♥";
        pass(s2);
    }

    private static void pass(String s) {
        for (int i = 0; i < s.length(); i++) {
            int codePoint = s.codePointAt(i);
            System.out.println(s.charAt(i) + " " + codePoint + " U+" + Integer.toHexString(codePoint));
        }
        trans_print(s, "ISO-8859-1");
        trans_print(s, "gb2312");
        trans_print(s, "gbk");
        trans_print(s, "gb18030");
        trans_print(s, "UTF-8");
        trans_print(s, "UTF-16");
    }

    private static void trans_print(String s, String charset) {
        try {
            byte[] b1 = s.getBytes(charset);
            System.out.println("\n" + charset + "\n" + Arrays.toString(b1));
            System.out.println(new String(b1, charset));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
