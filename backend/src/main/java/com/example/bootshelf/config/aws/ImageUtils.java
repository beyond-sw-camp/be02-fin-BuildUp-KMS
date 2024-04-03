package com.example.bootshelf.config.aws;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class ImageUtils {
    public static String makeImagePath(String originalName){
        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        String folderPath = str;
        String uuid = UUID.randomUUID().toString();

        return folderPath + "/" + uuid + "_" + originalName;
    }
}
