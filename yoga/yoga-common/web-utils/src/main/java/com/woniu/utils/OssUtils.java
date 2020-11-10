package com.woniu.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class OssUtils {
    //上传 返回url
    public static String upLoad(MultipartFile file) throws IOException {
        UUID uuid = UUID.randomUUID();
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "oss-cn-chengdu.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "LTAI4GHChG9gRjpN8mekxdLZ";
        String accessKeySecret = "z9jh9QmvRSyZzCfaNpspMHD23vpOrn";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        String bucketName = "woniutest";
        String key = uuid+".jpg";
        // 创建PutObjectRequest对象。
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, new ByteArrayInputStream(file.getBytes()));
        // 上传文件。
        ossClient.putObject(putObjectRequest);
        String url = bucketName + "." + endpoint + "/"+key;
        return url;
    }
}
