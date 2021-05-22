package utils;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LoadUtil {
    public List<File> loadFiles(String[] filePaths) throws Exception {
        List<File> desiredFiles;
        try {
            desiredFiles = Arrays.stream(filePaths).map(File::new).collect(Collectors.toList());
            return desiredFiles;
        } catch (Exception e){
            throw new Exception("Error has occurred. Please check files.");
        }
    }
}
