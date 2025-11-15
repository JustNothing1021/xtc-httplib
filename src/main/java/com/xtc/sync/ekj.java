package com.xtc.sync;

// WARN: 这个是DeepSeek的代码
import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class ekj {
    
    public ekj() {
    }
    

    public static byte[] a(String str, String charsetName) {
        if (str == null || str.length() == 0) {
            return null;
        }
        
        ByteArrayOutputStream baos = null;
        GZIPOutputStream gzos = null;
        
        try {
            baos = new ByteArrayOutputStream();
            gzos = new GZIPOutputStream(baos);
            
            byte[] bytes = str.getBytes(charsetName);
            gzos.write(bytes);
            gzos.close();
            
            byte[] compressedData = baos.toByteArray();
            baos.close();
            
            return compressedData;
            
        } catch (IOException e) {
            e.printStackTrace();
            if (baos != null) {
                try {
                    byte[] partialData = baos.toByteArray();
                    baos.close();
                    if (gzos != null) {
                        gzos.close();
                    }
                    return partialData;
                } catch (IOException e2) {
                }
            }
            return null;
        } finally {
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                }
            }
            if (gzos != null) {
                try {
                    gzos.close();
                } catch (IOException e) {
                }
            }
        }
    }
    
    public static byte[] a(byte[] compressedData) {
        if (compressedData == null || compressedData.length == 0) {
            return null;
        }
        
        ByteArrayOutputStream baos = null;
        GZIPInputStream gzis = null;
        ByteArrayInputStream bais = null;
        
        try {
            baos = new ByteArrayOutputStream();
            bais = new ByteArrayInputStream(compressedData);
            gzis = new GZIPInputStream(bais);
            
            byte[] buffer = new byte[256];
            int bytesRead;
            
            while ((bytesRead = gzis.read(buffer)) >= 0) {
                baos.write(buffer, 0, bytesRead);
            }
            
            gzis.close();
            byte[] decompressedData = baos.toByteArray();
            baos.close();
            
            return decompressedData;
            
        } catch (IOException e) {
            e.printStackTrace();
            if (baos != null) {
                try {
                    byte[] partialData = baos.toByteArray();
                    baos.close();
                    if (gzis != null) {
                        gzis.close();
                    }
                    return partialData;
                } catch (IOException e2) {
                }
            }
            return null;
        } finally {
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                }
            }
            if (gzis != null) {
                try {
                    gzis.close();
                } catch (IOException e) {
                }
            }
            if (bais != null) {
                try {
                    bais.close();
                } catch (IOException e) {
                }
            }
        }
    }
    

    public static byte[] compressString(String str) {
        return a(str, "UTF-8");
    }

    public static String decompressToString(byte[] compressedData, String charsetName) {
        byte[] decompressed = a(compressedData);
        if (decompressed == null) {
            return null;
        }
        try {
            return new String(decompressed, charsetName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
    

    public static String decompressToString(byte[] compressedData) {
        return decompressToString(compressedData, "UTF-8");
    }
}