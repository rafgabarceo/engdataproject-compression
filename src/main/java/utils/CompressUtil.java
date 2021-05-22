package utils;

import com.nixxcode.jvmbrotli.common.BrotliLoader;
import com.nixxcode.jvmbrotli.dec.BrotliInputStream;
import com.nixxcode.jvmbrotli.enc.BrotliOutputStream;
import com.nixxcode.jvmbrotli.enc.Encoder;
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
                    .replace("compressed.lzma", "") + "decompressedFileLZMA.txt"));
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

    public void brotliCompress(File file) throws IOException {
        FileInputStream fin = new FileInputStream(file.getPath());
        FileOutputStream fout = new FileOutputStream(file.getPath().replace(".txt", "") + ".br");

        Encoder.Parameters params = new Encoder.Parameters().setQuality(4);

        BrotliOutputStream brotliOutputStream = new BrotliOutputStream(fout, params);

        int read = fin.read();
        while(read > -1){
            brotliOutputStream.write(read);
            read = fin.read();
        }

        brotliOutputStream.close();
        fout.close();
    }

    public void brotliDecompress(File file) throws IOException {
        FileInputStream fin = new FileInputStream(file.getPath());
        FileOutputStream fout = new FileOutputStream(file.getName().replace(".br", "") + "decompressedFileBrotli.txt");

        BrotliInputStream brotliInputStream = new BrotliInputStream(fin);

        int read = brotliInputStream.read();
        while(read > -1){
            fout.write(read);
            read = brotliInputStream.read();
        }
        brotliInputStream.close();
        fout.close();
    }
}
