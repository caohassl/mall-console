package com.cmr.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.file.Paths;

/**
 * Created by Administrator on 2017/12/4.
 */
@Controller
@Slf4j
public class FileController {


    private static final String ROOT="upload/";

    private ResourceLoader resourceLoader;

    @Autowired
    public FileController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    //显示图片的方法关键 匹配路径像 localhost:8080/b7c76eb3-5a67-4d41-ae5c-1642af3f8746.png
    @RequestMapping(method = RequestMethod.GET, value = "/{root}/{dir}/{goodsId}/{goodsName:.+\\.jpg}")
    @ResponseBody
    public ResponseEntity<?> getFile(@PathVariable String root,@PathVariable String dir,@PathVariable String goodsId,@PathVariable String goodsName) {

        try {
            return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(root,dir,goodsId,goodsName).toString()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


}
