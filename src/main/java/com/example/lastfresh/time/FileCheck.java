package com.example.lastfresh.time;

import com.example.lastfresh.domain.dao.owner.OwnerProductDAO;
import com.example.lastfresh.domain.dao.product.AttachDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class FileCheck {

    private final OwnerProductDAO ownerProductDAO;


    /*
     *   0 * * * * * : 매 분 0초마다
     *   0/1 * * * * : 매 1초 간격
     *   0 0/1 * * * : 매 1분 간격
     *   0 0/5 * ? : 매 5분 간격
     *   0 0 0/1 * * : 매 1시간 간격
     *   0 0 0 * * ? : 매일 0시 마다
     *   0 0 0 1 * ? : 매월 1일 마다
     *   * 10-13 * * * * : 매 10, 11, 12, 13분에 동작한다.
     * */
    /* 마감기한이 지난 상품들 status -1로 변환 매 03시 마다*/
    @Scheduled(cron = "0 0 1 * * *")
    public void checkDate() throws Exception{
        log.info("-------------------실행--------------");
        ownerProductDAO.updateExpireProduct();
    }

//    @Scheduled(cron = "0 0 2 * * *")
//    public void checkFiles() throws Exception{
//        log.warn("File Check Task run..................");
//        log.warn("======================================");
//
//        List<ownerProductDAO> fileList = ownerProductDAO.getOldFiles();
//        //모델 객체에 있는 uploadPath만 가져오기 위해 map을 사용한다.
//        List<Path> fileListPaths = fileList.stream()
//                .map(file -> Paths.get("C:/upload", file.getUploadPath(), file.getUuid() + "_" + file.getFileName()))
//                .collect(Collectors.toList());
//
//        //RDB의 목록과 실제 경로의 목록을 비교하여 삭제 대상을 찾은 후 삭제
//        File directory = Paths.get("C:/upload", getUploadPathYesterDay()).toFile();
//        for(File file : directory.listFiles(file -> !fileListPaths.contains(file.toPath()))){
//            log.info(file.getPath() + " deleted");
//            file.delete();
//        }
//    }

    private String getUploadPathYesterDay(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Calendar yesterday = Calendar.getInstance();
        yesterday.add(Calendar.DATE, -1);
        return sdf.format(yesterday.getTime());
    }

}
