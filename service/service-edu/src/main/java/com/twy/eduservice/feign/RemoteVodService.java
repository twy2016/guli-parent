package com.twy.eduservice.feign;

import com.twy.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author gongpeng
 * @date 2020/12/8 13:02
 */
@FeignClient(contextId = "vodService", value = "service-vod",fallback = RemoteVodServiceFallBack.class)
public interface RemoteVodService {

    /**
     * 删除阿里云视频
     *
     * @param videoId
     * @return
     */
    @DeleteMapping("/eduvod/video/{videoId}")
    R removeVideo(@PathVariable String videoId);

    /**
     * 批量删除阿里云视频
     *
     * @param videoIdList
     * @return
     */
    @DeleteMapping("/eduvod/video")
    R removeVideoList(@RequestParam("videoIdList") List<String> videoIdList);
}
