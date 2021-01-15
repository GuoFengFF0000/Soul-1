package com.qf.utils;

import com.google.gson.Gson;
import com.qf.pojo.resp.BaseResp;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Component
public class UploadPic {

    //@Value("${qiniu.ak}")
    private String ak="AWpCqQEY9qvOMbmguZsIJaA5T6EoGdC5Ywim4oKU";

    //@Value("${qiniu.sk}")
    private String sk="BU7fgwT675Ka7MJX1B2SSxy3rrdp9fNSyX8_ZyC-";

    //@Value("${qiniu.bucket}")
    private String bucket="soul2007";

    //@Value("${qiniu.url}")
    private String url="http://qmg8d7vbk.hb-bkt.clouddn.com/";


    //字节数组方式上传
    public BaseResp upload(MultipartFile multipartFile){

        BaseResp baseResponse = new BaseResp();
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region1());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传

        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        try {
            byte[] bytes = multipartFile.getBytes();
            Auth auth = Auth.create(ak, sk);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(bytes, key, upToken);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);

                baseResponse.setCode(200);
                baseResponse.setData(url+putRet.key);
                return baseResponse;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (UnsupportedEncodingException ex) {
            //ignore
        }catch (IOException e){
            e.printStackTrace();
        }
        baseResponse.setCode(201);
        baseResponse.setMessage("上传失败");
        return baseResponse;
    }
}
