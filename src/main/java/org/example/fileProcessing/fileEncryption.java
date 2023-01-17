package org.example.fileProcessing;

import javax.crypto.*;
import java.io.*;
import java.security.*;

public class fileEncryption {
    private static String type = "AES";
    public static void encryptFile(String srcFile, String destFile, String privateKey) throws GeneralSecurityException, IOException {
        Key key = getKey(privateKey);
        Cipher cipher = Cipher.getInstance(type + "/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(mkdirFiles(destFile));

            crypt(fis, fos, cipher);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ShortBufferException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        } finally {
            if (fis != null) {
                fis.close();
            }
            if (fos != null) {
                fos.close();
            }
        }
    }


    public static void decryptFile(String srcFile, String destFile, String privateKey) throws GeneralSecurityException, IOException {
        Key key = getKey(privateKey);
        Cipher cipher = Cipher.getInstance(type + "/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);

        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(mkdirFiles(destFile));

            crypt(fis, fos, cipher);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                fis.close();
            }
            if (fos != null) {
                fos.close();
            }
        }
    }


    private static File mkdirFiles(String filePath) throws IOException {
        File file = new File(filePath);
//        if (!file.getParentFile().exists()) {
//            file.getParentFile().mkdirs();
//        }
        file.createNewFile();

        return file;
    }


    private static Key getKey(String secret) throws GeneralSecurityException  {
        KeyGenerator kgen = KeyGenerator.getInstance(type);
        kgen.init(128, new SecureRandom(secret.getBytes()));
        SecretKey secretKey = kgen.generateKey();
        return secretKey;
    }



    private static void crypt(InputStream in, OutputStream out, Cipher cipher) throws IOException, GeneralSecurityException, IllegalBlockSizeException, BadPaddingException {
        int blockSize = cipher.getBlockSize() * 1000;
        int outputSize = cipher.getOutputSize(blockSize);

        byte[] inBytes = new byte[blockSize];
        byte[] outBytes = new byte[outputSize];

        int inLength = 0;
        boolean more = true;
        while (more) {
            inLength = in.read(inBytes);
            if (inLength == blockSize) {
                int outLength = cipher.update(inBytes, 0, blockSize, outBytes);
                out.write(outBytes, 0, outLength);
            } else {
                more = false;
            }
        }
        if (inLength > 0)
            outBytes = cipher.doFinal(inBytes, 0, inLength);
        else
            outBytes = cipher.doFinal();
        out.write(outBytes);
    }
}
