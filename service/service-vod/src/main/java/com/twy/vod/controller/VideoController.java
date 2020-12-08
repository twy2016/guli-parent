package com.twy.vod.controller;

import com.twy.commonutils.R;
import com.twy.vod.service.VideoService;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author gongpeng
 * @date 2020/12/7 23:06
 */
@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/eduvod/video")
public class VideoController {
    private final VideoService videoService;

    @PostMapping("/upload")
    public R uploadVideo(@RequestParam("file") MultipartFile file) {
        String videoId = videoService.uploadVideo(file);
        return R.ok(videoId);
    }

    @DeleteMapping("/{videoId}")
    public R removeVideo(@PathVariable String videoId){
        videoService.removeVideo(videoId);
        return R.ok();
    }

    /**
     * 批量删除视频
     * @param videoIdList
     * @return
     */
    @DeleteMapping
    public R removeVideoList(@RequestParam("videoIdList") List<String> videoIdList){
        videoService.removeVideoList(videoIdList);
        return R.ok();
    }

}
