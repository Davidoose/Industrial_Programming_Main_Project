package org.example.fileProcessing;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ArchivingFiles {
    public static void archiveFilesZip(String fileName, String zipFileName) throws IOException {
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream("D:\\Industrial_Programming_Main_Project\\" + zipFileName));
        ZipEntry entry = new ZipEntry(fileName);
        out.putNextEntry(entry);
        FileInputStream fis = new FileInputStream("D:\\Industrial_Programming_Main_Project\\" + fileName);
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        out.write(buffer);
        out.closeEntry();
        out.close();
    }

    public static void reArchiveFilesZip(String fileName) throws IOException {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream("D:\\Industrial_Programming_Main_Project\\" + fileName))) {
            ZipEntry entry;
            String name;
            long size;
            while ((entry = zin.getNextEntry()) != null) {

                name = entry.getName();
                FileOutputStream fout = new FileOutputStream("D:\\Industrial_Programming_Main_Project\\new" + name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (Exception ex) {

            System.out.println(ex.getMessage());
        }
    }

    public static void archiveFilesJar(String fileName, String JarfileName) throws IOException {
        JarOutputStream out = new JarOutputStream(new FileOutputStream("D:\\Industrial_Programming_Main_Project\\" + JarfileName));
        JarEntry entry = new JarEntry(fileName);
        out.putNextEntry(entry);
        FileInputStream fis = new FileInputStream("D:\\Industrial_Programming_Main_Project\\" + fileName);
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        out.write(buffer);
        out.closeEntry();
        out.close();
    }
}