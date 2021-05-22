import lombok.AllArgsConstructor;
import utils.LoadUtil;

import java.io.File;
import java.util.List;

public class Main {
    private static final LoadUtil loadUtil = new LoadUtil();

    public static void main(String[] args) throws Exception {
        List<File> loadedFilesForCompression = loadUtil.loadFiles(args);
        loadedFilesForCompression.forEach((file) -> {
            System.out.println(file.getAbsolutePath());
        });
    }
}
