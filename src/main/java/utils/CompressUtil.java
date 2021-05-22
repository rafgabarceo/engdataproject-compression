package utils;

import com.nixxcode.jvmbrotli.common.BrotliLoader;
import org.apache.commons.compress.compressors.lzma.LZMACompressorInputStream;
import org.apache.commons.compress.compressors.lzma.LZMACompressorOutputStream;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CompressUtil {


    public void initializeCompression(){
        BrotliLoader.isBrotliAvailable();
    }

    public void lzmaCompress(File file) throws Exception {
        try{
            InputStream in = Files.newInputStream(Path.of(file.getPath()));
            OutputStream fout = Files.newOutputStream(Path.of(file.getName().toLowerCase().replace(".txt", "") + "Compressed.lzma"));
            BufferedOutputStream out = new BufferedOutputStream(fout);
            LZMACompressorOutputStream lzOut = new LZMACompressorOutputStream(out);
            final byte[] buffer = new byte[1024];
            int n = 0;
            while(-1 != (n = in.read(buffer))){
                lzOut.write(buffer, 0, n);
            }
            lzOut.close();
            in.close();
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public void lzmaDecompress(File file) throws Exception {
        try {
            InputStream fin = Files.newInputStream(Path.of(file.getPath()));
            BufferedInputStream in = new BufferedInputStream(fin);
            OutputStream out = Files.newOutputStream(Paths.get(file.getName()
                    .toLowerCase()
                    .replace("compressed.lzma", "") + "decompressedFile.txt"));
            LZMACompressorInputStream lzmaIn = new LZMACompressorInputStream(in);
            final byte[] buffer = new byte[1024];
            int n = 0;
            while (-1 != (n = lzmaIn.read(buffer))){
                out.write(buffer, 0, n);
            }
            out.close();
            lzmaIn.close();
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public boolean brotliCompress(File file){
        return false;
    }

    public boolean brotliDecompress(File file){
        return false;
    }


}
