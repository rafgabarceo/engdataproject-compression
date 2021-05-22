import utils.*;

import java.io.File;
import java.util.List;

public class Main {
    private static final LoadUtil loadUtil = new LoadUtil();
    private static final CompressUtil compressUtil = new CompressUtil();

    public static void main(String[] args) throws Exception {
        compressUtil.initializeCompression(); // should be run at the start of the application
        List<File> loadedFilesForCompression = loadUtil.loadFiles(args);
        compressUtil.lzmaCompress(loadedFilesForCompression.get(0));
        compressUtil.lzmaDecompress(new File("./compressed.lzma"));
    }
}
