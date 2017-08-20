package org.tombear.demo.guava.oddsends;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

/**
 * <P>Descriptions</P>
 *
 * @author tombear on 2017-08-20 23:06.
 */
public class HashFunctionClass {
    public void hashFunctionDemo() {
        HashFunction adler32 = Hashing.adler32();
        HashFunction crc32 = Hashing.crc32();
        HashFunction gfh = Hashing.goodFastHash(128);
        HashFunction murmur3_32 = Hashing.murmur3_32();
        HashFunction murmur3_128 = Hashing.murmur3_128();

        HashFunction md5 = Hashing.md5();
        HashFunction sha1 = Hashing.sha1();
        HashFunction sha256 = Hashing.sha256();
        HashFunction sha512 = Hashing.sha512();
    }
}
