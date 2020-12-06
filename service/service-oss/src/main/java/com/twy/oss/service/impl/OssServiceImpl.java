package com.twy.oss.service.impl;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.IdUtil;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.OSSObject;
import com.twy.oss.service.OssService;
import com.twy.oss.util.Constant;
import com.twy.servicebase.exception.GuliException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author gongpeng
 * @date 2020/11/22 16:35
 */
@Service
public class OssServiceImpl implements OssService {

    @Override
    public String uploadFile(MultipartFile file) {
        OSS client = new OSSClientBuilder().build(Constant.endpoint, Constant.accessKeyId, Constant.accessKeySecret);
        String uploadUrl = null;
        try {
            InputStream inputStream = file.getInputStream();
            String original = file.getOriginalFilename();
            String fileName = IdUtil.simpleUUID();
            String fileType = original.substring(original.lastIndexOf("."));
            String newName = fileName + fileType;
            client.putObject(Constant.bucketName, newName, inputStream);
            uploadUrl = "http://" + Constant.bucketName + "." + Constant.endpoint + "/" + newName;
        } catch (Exception e) {
            e.printStackTrace();
            throw new GuliException("文件上失败");
        } finally {
            client.shutdown();
        }
        return uploadUrl;
    }

}
