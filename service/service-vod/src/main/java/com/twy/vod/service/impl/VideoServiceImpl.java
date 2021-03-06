package com.twy.vod.service.impl;

import cn.hutool.core.util.StrUtil;
import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.*;
import com.twy.servicebase.exception.GuliException;
import com.twy.vod.service.VideoService;
import com.twy.vod.util.AliyunVodSDKUtils;
import com.twy.vod.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author gongpeng
 * @date 2020/12/7 22:38
 */
@Service
@Slf4j
public class VideoServiceImpl implements VideoService {

    @Override
    public String uploadVideo(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            String title = originalFilename.substring(0, originalFilename.lastIndexOf("."));
            UploadStreamRequest request = new UploadStreamRequest(
                    Constant.keyId,
                    Constant.keySecret,
                    title, originalFilename, inputStream);
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);

            //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。
            // 其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
            String videoId = response.getVideoId();
            if (!response.isSuccess()) {
                String errorMessage = "阿里云上传错误：" + "code：" + response.getCode() + ", message：" + response.getMessage();
                log.warn(errorMessage);
                if (StrUtil.isEmpty(videoId)) {
                    throw new GuliException(errorMessage);
                }
            }
            return videoId;
        } catch (IOException e) {
            throw new GuliException("guli vod 服务上传失败");
        }
    }

    @Override
    public void removeVideo(String videoId) {
        try {
            DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(
                    Constant.keyId,
                    Constant.keySecret);
            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(videoId);
            DeleteVideoResponse response = client.getAcsResponse(request);
            System.out.print("RequestId = " + response.getRequestId() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            throw new GuliException("视频删除失败");
        }
    }

    @Override
    public void removeVideoList(List<String> videoIdList) {
        try {
            DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(
                    Constant.keyId,
                    Constant.keySecret);
            DeleteVideoRequest request = new DeleteVideoRequest();
            //一次只能批量删20个
            String str = StrUtil.join(",", videoIdList);
            request.setVideoIds(str);
            DeleteVideoResponse response = client.getAcsResponse(request);
            System.out.print("RequestId = " + response.getRequestId() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            throw new GuliException("视频删除失败");
        }
    }
}
