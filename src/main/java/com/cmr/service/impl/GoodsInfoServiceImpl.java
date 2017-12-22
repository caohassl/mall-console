package com.cmr.service.impl;

import com.cmr.dao.GoodsMapper;
import com.cmr.entities.GoodsInfo;
import com.cmr.entities.vo.GoodsInfoVo;
import com.cmr.service.GoodsInfoService;
import com.cmr.util.DateUtils;
import com.cmr.util.ImageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/12/6.
 */
@Slf4j
@Service
public class GoodsInfoServiceImpl implements GoodsInfoService {

    private static final String ROOTTEMP="upload/temp";
    private static final String ROOT="upload/pic";

    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public List<GoodsInfo> findAll() {
        return goodsMapper.findAll();
    }

    @Override
    public void deleteById(long id) {
        goodsMapper.deleteById(id);
    }

    @Override
    public void upGoods(long id) {
        goodsMapper.upGoods(id);
    }

    @Override
    public void downGoods(long id) {
        goodsMapper.downGoods(id);
    }

    @Override
    public synchronized void add(GoodsInfoVo goodsInfoVo) {
        GoodsInfo goodsInfo=new GoodsInfo();
        copyProperties(goodsInfoVo, goodsInfo);
        goodsMapper.addGoods(goodsInfo);
    }

    private void copyProperties(GoodsInfoVo goodsInfoVo,GoodsInfo goodsInfo) {
        BeanUtils.copyProperties(goodsInfoVo,goodsInfo);
        goodsInfo.setGoodsId(generateGoodsId());
        goodsInfo.setGoodsStatus("00");//初始状态为上架
        goodsInfo.setInsertTime(new Date());
        goodsInfo.setUpdateTime(new Date());
    }

    //22位
    public synchronized String generateGoodsId() {
        String maxGoodsId=goodsMapper.selectMaxGoodsId();
        int number=0;
        try {
             number = Integer.parseInt(maxGoodsId.substring(maxGoodsId.length() - 4));
        }catch(Exception e){
            number=0;
        }
        if(number>=9999){
            number=0;
        }
        String queue=String.format("%04d",number+1);
        String date=DateUtils.getDate(DateUtils.parsePatterns[1]);
        String goodsId="CMR_"+date +queue;
        return goodsId;
    }

    @Override
    public GoodsInfo getGoodsById(long id) {
        return goodsMapper.getGoodsById(id);
    }

    @Override
    public void updateGoods(GoodsInfoVo goodsInfoVo) {
        GoodsInfo goodsInfo=goodsMapper.getGoodsById(Long.parseLong(goodsInfoVo.getId()));
        updateGoods(goodsInfoVo,goodsInfo);
        goodsMapper.updateGoods(goodsInfo);

    }

    @Override
    public void savaUpLoadPic(String goodsId) throws IOException {

        String tempPath= Paths.get(ROOTTEMP,goodsId).toAbsolutePath().toString();
        String rootPath= Paths.get(ROOT,goodsId).toAbsolutePath().toString();
        File tempPathFile=new File(tempPath);
        File rootPathFile=new File(rootPath);
        if(!rootPathFile.exists()){
            rootPathFile.mkdirs();
        }
        if(tempPathFile.exists()){
            for(File file:tempPathFile.listFiles()){
                String rootPathAndName= rootPath+"/"+file.getName();
                File newFile=new File(rootPathAndName);
                if(newFile.exists()){
                    newFile.delete();
                }
                FileCopyUtils.copy(file,newFile);
                log.info("{}=========>上传成功",file.getName());

            }
        }
    }

    @Override
    public void upLoadPic(MultipartFile uploadFile, String path, String goodsNameAndSuffix) throws IOException {
        String url=path+"/"+goodsNameAndSuffix;


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
    }




    private void updateGoods(GoodsInfoVo source, GoodsInfo target) {
        target.setGoodsName(source.getGoodsName());
        target.setGoodsSpec(source.getGoodsSpec());
        target.setGoodsPrice(source.getGoodsPrice());
        target.setGoodsAmount(source.getGoodsAmount());
        target.setPromotionGoodsPrice(source.getPromotionGoodsPrice());
        target.setGoodsDesc(source.getGoodsDesc());
        target.setUpdateTime(new Date());
    }


    public static void main(String[] args) {
        String s="123131";
        System.out.println(String.format("%06d",Integer.parseInt(s.substring(s.length()-4))));
    }
}
