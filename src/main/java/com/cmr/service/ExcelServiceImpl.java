package com.cmr.service;

import com.cmr.util.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.tools.ant.util.FileUtils;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by icourt1 on 2018/3/9.
 */
@Service
@Slf4j
public class ExcelServiceImpl {

    @Autowired
    Environment environment;

    @Autowired
    ServletContext servletContext;

    @PostConstruct
    private void init() throws IOException {
        InputStream inputStream = ExcelServiceImpl.class.getResourceAsStream("/files/law-regu-mapping.xlsx");
        File file = new File("/Users/icourt1/Caomr/idea_workplace/mall-console/mall-console/src/main/resources/files/law-regu-mapping.txt");
        if(!file.exists()){
            file.createNewFile();
        }
        ExcelUtil excelUtil = new ExcelUtil(inputStream,"1");
        Workbook workbook = excelUtil.getWorkbook();
        Sheet sheet = workbook.getSheetAt(0);
        int rowNum = sheet.getLastRowNum();
        //从零行开始？
        StringBuffer lineStr = new StringBuffer();

        for(int i = 0;i<rowNum ;i++){
           Row row = sheet.getRow(i);
           //从第二行开始
           for(int j=2 ;j< row.getLastCellNum();j++){
               //redisClient.hset(kwdic, "合同法", "中华人民共和国合同法");
               if(null != row.getCell(j) && StringUtils.isNotEmpty(row.getCell(j).getStringCellValue())){
                   lineStr.append("redisClient.hset(kwdic,").append("\""+row.getCell(j).getStringCellValue()+"\",").append("\""+row.getCell(1).getStringCellValue()+"\"),"+"\n");
               }

           }
        }
        FileUtil.writeAsString(file,lineStr.toString());
        log.info("完成");
    }
}
