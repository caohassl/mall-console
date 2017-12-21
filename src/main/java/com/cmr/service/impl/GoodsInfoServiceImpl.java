package com.cmr.service.impl;

import com.cmr.dao.GoodsMapper;
import com.cmr.entities.GoodsInfo;
import com.cmr.entities.vo.GoodsInfoVo;
import com.cmr.service.GoodsInfoService;
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
import java.util.UUID;

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
    public void add(GoodsInfoVo goodsInfoVo) {
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

    @Override
    public synchronized String generateGoodsId() {

        return "CMR_"+UUID.randomUUID().toString().toUpperCase().replaceAll("-","");
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
        System.out.println("CMR_"+UUID.randomUUID().toString().toUpperCase().replaceAll("-",""));
    }
}
