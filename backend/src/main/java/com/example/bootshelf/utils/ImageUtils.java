package com.example.bootshelf.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class ImageUtils {
    public static String makeBoardImagePath(String originalName){
        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        String folderPath = str;
        String type = "board";
        String uuid = UUID.randomUUID().toString();

        return type + "/" + folderPath + "/" + uuid + "_" + originalName;
    }
}
