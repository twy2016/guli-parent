package com.twy.eduservice.feign;

import com.twy.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author gongpeng
 * @date 2020/12/8 13:02
 */
@Component
public class RemoteVodServiceFallBack implements RemoteVodService {

    /**
     * 删除阿里云视频
     *
     * @param videoId
     * @return
     */
    @Override
    public R removeVideo(String videoId) {
        System.out.println("请求超时");
        return R.error("请求超时");
    }

    /**
     * 批量删除阿里云视频
     *
     * @param videoIdList
     * @return
     */
    @Override
    public R removeVideoList(List videoIdList) {
        return R.error("请求超时");
    }
}
