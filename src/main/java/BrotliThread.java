import lombok.AllArgsConstructor;
import utils.CompressUtil;
import utils.LoadUtil;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public class BrotliThread extends Thread {
    private final String[] args;
    private final List<File> files;
    private final CompressUtil compressUtil = new CompressUtil();
    private final LoadUtil loadUtil = new LoadUtil();

    public void run(){
        try {
            Arrays.stream(args).forEach((file) -> {
                try {
                    long startTime = System.currentTimeMillis();
                    compressUtil.brotliCompress(loadUtil.loadFile(file).getAbsoluteFile());
                    long endTime = System.currentTimeMillis();
                    System.out.println("Compression time with Brotli is: " + file + " is " + (endTime - startTime) + "ms");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            files.forEach((file) -> {
                try {
                    String originalName = file.getName().replace(".txt", "");
                    long startTime = System.currentTimeMillis();
                    compressUtil.brotliDecompress(new File("./" + originalName + ".br"));
                    long endTime = System.currentTimeMillis();
                    System.out.println("Decompression time with Brotli is: " + file + " is " + (endTime - startTime) + "ms");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e){
            System.out.println("Exception caught with info: " + e.getMessage());
        }
    }

}
