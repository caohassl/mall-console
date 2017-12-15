package com.cmr.controller;

import com.alibaba.fastjson.JSONObject;
import com.cmr.entities.ConsoleResult;
import com.cmr.entities.GoodsInfo;
import com.cmr.entities.vo.GoodsInfoVo;
import com.cmr.service.GoodsInfoService;
import com.cmr.util.ImageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/4.
 */
@Controller
@RequestMapping(value = "/goodsInfo")
@Slf4j
public class GoodsController {


    private static final String ROOT="upload/";
    @Resource
    GoodsInfoService goodsInfoServiceImpl;

    /**
     * 商品首页
     * @return
     */
    @RequestMapping(path = "/")
    public String goodsInfo(Model model){
        model.addAttribute("goodsList", JSONObject.toJSONString(goodsInfoServiceImpl.findAll()));
        return "good/goodsInfo";
    }

    /**
     * 添加商品
     * @return
     */
    @RequestMapping(path = "/add")
    @ResponseBody
    public ConsoleResult addGoods(GoodsInfoVo goodsInfoVo){
        goodsInfoServiceImpl.add(goodsInfoVo);
        return ConsoleResult.Success;
    }

    /**
     * 编辑商品
     * @return
     */
    @RequestMapping(path = "/update")
    @ResponseBody
    public ConsoleResult updateGoods(GoodsInfoVo goodsInfoVo){
        goodsInfoServiceImpl.updateGoods(goodsInfoVo);
        return ConsoleResult.Success;
    }


    /**
     * 删除商品
     * @return
     */
    @RequestMapping(path = "/delete")
    @ResponseBody
    public ConsoleResult deleteGoods(long id){
        goodsInfoServiceImpl.deleteById(id);
        return ConsoleResult.Success;
    }

    /**
     * 上架商品
     * @return
     */
    @RequestMapping(path = "/upGoods")
    @ResponseBody
    public ConsoleResult upGoods(long id){
        goodsInfoServiceImpl.upGoods(id);
        return ConsoleResult.Success;
    }

    /**
     * 下架商品
     * @return
     */
    @RequestMapping(path = "/downGoods")
    @ResponseBody
    public ConsoleResult downGoods(long id){
        goodsInfoServiceImpl.downGoods(id);
        return ConsoleResult.Success;
    }

    /**
     * 下架商品
     * @return
     */
    @RequestMapping(path = "/getGoodsById")
    @ResponseBody
    public ConsoleResult getGoodsById(long id){
        GoodsInfo goodsInfo=goodsInfoServiceImpl.getGoodsById(id);
        return new ConsoleResult(goodsInfo);
    }






    @RequestMapping(path = "/upLoadPic")
    @ResponseBody
    public ConsoleResult upLoadPic(MultipartFile uploadFile, String goodsId, String flag, String goodsName, HttpServletRequest request) throws IOException {

        // 获取图片原始文件后缀
        String suffix=uploadFile.getOriginalFilename().substring(uploadFile.getOriginalFilename().indexOf("."));

        String path=Paths.get(ROOT,goodsId+"/").toAbsolutePath().toString();
        String goodsNameSuffix=goodsName+"("+flag+")"+suffix;
        String url=path+"/"+goodsNameSuffix;

        File file=new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
//        uploadFile.transferTo(new File(url));
        if(new File(url).exists()){
            new File(url).delete();
        }
        // 上传图片
        ImageUtil.compress(uploadFile.getInputStream(),new File(url),1000,800);
        Map map=new HashMap();
        map.put("url","/"+ROOT+goodsId+"/"+goodsNameSuffix);
        map.put("flag",flag);
        return new ConsoleResult(map);
    }


}
