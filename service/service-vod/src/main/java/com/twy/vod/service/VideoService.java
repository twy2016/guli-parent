package com.twy.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author gongpeng
 * @date 2020/12/7 22:38
 */
public interface VideoService {

    String uploadVideo(MultipartFile file);

    void removeVideo(String videoId);

    void removeVideoList(List<String> videoIdList);
}
