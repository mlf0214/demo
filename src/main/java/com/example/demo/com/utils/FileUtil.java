package com.example.demo.com.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileUtil {
    public static String saveFile(String filename, String filepath, byte[] data) {
        File file = null;
        if (data != null) {
            String s = filepath + filename;
            file = new File(s);
            if (file.exists()) {
                file.delete();
            }
            try {
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(data, 0, data.length);
                fos.flush();
                fos.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        String path = file.getPath();
        String s = System.getProperty("user.dir") + "\\src\\main\\resources\\static";
        path = path.replace(s, "");
        return path;
    }

    public static void copy(File srcFile, File destFile) {
//        设置流
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(srcFile);
            os = new FileOutputStream(destFile);
//            操作分段读取
            byte[] flush = new byte[1024 * 2];
            int len = -1;
            while ((len = is.read(flush)) != -1) {
                os.write(flush, 0, len);
            }
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((os != null)) {
                try {
                    os.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }


    }


    public static List<File> getAllFile(String dirFilePath) {
        if (StringUtils.isBlank(dirFilePath))
            return null;
        return getAllFile(new File(dirFilePath));
    }

    public static List<File> getAllFile(File dirFile) {
        if (Objects.isNull(dirFile) || !dirFile.exists() || dirFile.isFile())
            return null;
        File[] chilFiles = dirFile.listFiles();
        if (Objects.isNull(chilFiles) || chilFiles.length == 0)
            return null;

        List<File> files = new ArrayList<>();
        for (File childFile :
                chilFiles) {
//            如果是文件直接添加到结果集合
            if (childFile.isFile()) {
                files.add(childFile);
            } else {
                files.addAll(List.of(chilFiles));
                List<File> cFiles = getAllFile(childFile);
                if (Objects.isNull(cFiles) || cFiles.isEmpty()) continue;
                files.addAll(cFiles);
            }
        }
        return files;

    }
}
