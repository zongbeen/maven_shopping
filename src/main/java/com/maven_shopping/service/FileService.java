package com.maven_shopping.service;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

@Service
@Log
public class FileService {
    public String uploadFile(String uploadPath, String originalFileName, byte[] fileData) throws Exception {
        UUID uuid = UUID.randomUUID(); //서로 다른 개체 구별(파일 중복 문제 해결)
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String savedFileName = uuid.toString() + extension; ///UUID로 받은 값과 원래 파일의 이름의 확장자를 조합해서 저장될 파일 이름 생성
        String fileUploadFullUrl = uploadPath + "/" + savedFileName;
        FileOutputStream fos = new FileOutputStream(fileUploadFullUrl);//생성자로 파일이 저장될 위치와 파일의 이름을 넘겨 파일에 쓸 파일 출력 스트림 생성
        fos.write(fileData);
        fos.close();
        return savedFileName;
    }
    public void deleteFile(String filePath) throws Exception {
        File deleteFile = new File(filePath);

        if(deleteFile.exists()) {
            deleteFile.delete();
            log.info("파일을 삭제하였습니다.");
        }else {
            log.info("파일이 존재하지 않습니다.");
        }
    }
}
