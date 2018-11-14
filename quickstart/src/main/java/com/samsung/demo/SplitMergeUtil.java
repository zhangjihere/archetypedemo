package com.samsung.demo;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;

/**
 * This util is used to split edng and merge dng <br/>
 * mt is abbreviated for metadata <br/>
 * mtback is abbreviated for metadata_back<br/>
 * eraw is abbreviated for encoded_raw<br/>
 *
 * <b>dng  = mt + raw+ mtback</b><br/>
 * <b>edng = 4bytes(mt size) + 4bytes(mtback size) + mt + mtback + eraw</b><br/>
 * Created by ji.zhang on 11/13/18.
 */
public class SplitMergeUtil {

    /**
     * Split edng file to mt, mtback and eraw files by interpreting head 8 bytes for mt amd mtback
     * size
     *
     * @param edngPath   edng file path
     * @param mtPath     target metadata file path
     * @param mtbackPath target metadata_back file path
     * @param erawPath   target encoded_raw file path
     */
    public static void splitEdng(String edngPath, String mtPath, String mtbackPath, String erawPath) {
        try (FileChannel edngChannel = new RandomAccessFile(edngPath, "r").getChannel()) {
            long edngSize = edngChannel.size();
            long mtSize = bytes2Int(edngChannel, 0);
            long mtbackSize = bytes2Int(edngChannel, 4);

            String[] filePaths = {mtPath, mtbackPath, erawPath};
            long[] fileSize = {mtSize, mtbackSize, (edngSize - mtSize - mtbackSize)};
            long[] positions = {8, 8 + mtSize, 8 + mtSize + mtbackSize};

            for (int i = 0; i < fileSize.length; i++) {
                FileChannel toChannel = new RandomAccessFile(filePaths[i], "rw").getChannel();
                edngChannel.position(positions[i]);
                toChannel.transferFrom(edngChannel, 0, fileSize[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Transform 4 bytes to Integer.
     * bytes' order is LITTLE_ENDIAN
     *
     * @param sourceChannel source file channel
     * @param position      start read position
     * @return size int
     */
    private static int bytes2Int(FileChannel sourceChannel, int position) throws IOException {
        byte[] sizeArr = new byte[4];
        ByteBuffer sizeBuffer = ByteBuffer.wrap(sizeArr);
        sizeBuffer.order(ByteOrder.LITTLE_ENDIAN);
        sourceChannel.read(sizeBuffer, position);
        byte[] bytes = sizeBuffer.array();
        int i0 = bytes[0] & 0xFF;
        int i1 = bytes[1] & 0xFF;
        int i2 = bytes[2] & 0xFF;
        int i3 = bytes[3] & 0xFF;
        return (i3 << 24) + (i2 << 16) + (i1 << 8) + i0;
    }

    /**
     * Merge mt, raw and mtback to dng file
     *
     * @param mtPath     metadata file path
     * @param rawPath    raw file path
     * @param mtbackPath metadata_back file path
     * @param dngPath    new dng file path
     */
    public static void merge2Dng(String mtPath, String rawPath, String mtbackPath, String dngPath) {
        try (FileChannel dngChannel = new RandomAccessFile(dngPath, "rw").getChannel()) {
            String[] filePaths = {mtPath, rawPath, mtbackPath};
            for (int i = 0, position = 0; i < filePaths.length; i++) {
                FileChannel fromChannel = new RandomAccessFile(filePaths[i], "r").getChannel();
                dngChannel.transferFrom(fromChannel, position, fromChannel.size());
                position += fromChannel.size();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String edngPath = "/home/zhangji/output_20180502_140640.edng";
        String mtPath = "/tmp/mt.data";
        String mtbackPath = "/tmp/mtback.data";

        String erawPath = "/tmp/eraw.data";
        splitEdng(edngPath, mtPath, mtbackPath, erawPath);

        String rawPath = "/tmp/eraw.data";
        String dngPath = "/tmp/dng.data";
        merge2Dng(mtPath, rawPath, mtbackPath, dngPath);
    }

}
