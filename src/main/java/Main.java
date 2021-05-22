import utils.*;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static final LoadUtil loadUtil = new LoadUtil();
    private static final CompressUtil compressUtil = new CompressUtil();

    public static void main(String[] args) throws Exception {
        compressUtil.initializeCompression(); // should be run at the start of the application
        List<File> loadedFilesForCompression = loadUtil.loadFiles(args);
        System.out.println("Compressing files...");

        // compression
        Arrays.stream(args).forEach((file) -> {
            try {
                long startTime = System.currentTimeMillis();
                compressUtil.lzmaCompress(loadUtil.loadFile(file).getAbsoluteFile());
                long endTime = System.currentTimeMillis();
                System.out.println("Compression time of: " + file + " is " + (endTime - startTime) + "ms");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        loadedFilesForCompression.forEach((file) -> {

            try {
                String originalName = file.getName().replace(".txt", "");
                long startTime = System.currentTimeMillis();
                compressUtil.lzmaDecompress(new File("./" + originalName + "Compressed.lzma"));
                long endTime = System.currentTimeMillis();
                System.out.println("Decompression time of: " + file + " is " + (endTime - startTime) + "ms");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        //compressUtil.lzmaCompress(loadedFilesForCompression.get(0));

    }
}