package com.arjuninamdar.congressionalchallenge.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


@Component
public class FileUtil {
    
    public String saveFile(MultipartFile file, int userId) throws Exception{
        String filename = file.getOriginalFilename();
        String extension = filename.substring(filename.lastIndexOf(".") + 1);

        boolean isValid = false;

        switch(extension) {
            case "jpg":
                isValid = true;
                break;
            case "png":
                isValid = true;
                break;
            case "jpeg":
                isValid = true;
                break;
            case "HEIC":
                isValid = true;
                break;
                
        }

        if(!isValid)
            throw new Exception("File not valid");
        

        String filePath = "/posts/user" + userId + "/post" + UUID.randomUUID().toString() + "." + extension;
        File convertFile = new File("../congressional-app" + filePath);
        if (!convertFile.getParentFile().exists())
            convertFile.getParentFile().mkdirs();
            
        convertFile.createNewFile();
        FileOutputStream fout = new FileOutputStream(convertFile);
        fout.write(file.getBytes());
        fout.close();

        return filePath; 

    }
}
