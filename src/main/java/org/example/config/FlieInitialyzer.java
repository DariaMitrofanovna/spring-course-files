package org.example.config;

import java.io.*;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;

@Component
public class FlieInitialyzer {

    public FlieInitialyzer() {
        File mbFile = Paths.get("./mb_file.txt").toFile();
        File gbFile = Paths.get("./gb_file.txt").toFile();
        File fileForMinio = Paths.get("./minio_file.txt").toFile();
        RandomAccessFile mbRaf;
        RandomAccessFile gbRaf;
        try (FileOutputStream fos = new FileOutputStream(fileForMinio)) {
            byte[] bytes = "Ehi!".getBytes();
            fos.write(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            mbRaf = new RandomAccessFile(mbFile, "rw");
            gbRaf = new RandomAccessFile(gbFile, "rw");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            mbRaf.setLength(1024 * 1024 * 5);
            gbRaf.setLength(1024 * 1024 * 1024);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            mbRaf.close();
            gbRaf.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}