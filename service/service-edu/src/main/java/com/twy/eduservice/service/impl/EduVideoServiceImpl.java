package com.twy.eduservice.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.twy.commonutils.R;
import com.twy.eduservice.entity.EduVideo;
import com.twy.eduservice.feign.RemoteVodService;
import com.twy.eduservice.mapper.EduVideoMapper;
import com.twy.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.twy.servicebase.exception.GuliException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author gongpeng
 * @since 2020-12-07
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Resource
    private RemoteVodService remoteVodService;

    @Override
    public boolean getCountByChapterId(String chapterId) {
        int count = this.count(Wrappers.<EduVideo>lambdaQuery().eq(EduVideo::getChapterId, chapterId));
        return count > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeVideoById(String id) {
        EduVideo video = baseMapper.selectById(id);
        String videoSourceId = video.getVideoSourceId();
        if (StrUtil.isNotEmpty(videoSourceId)) {
            R flag = remoteVodService.removeVideo(videoSourceId);
            if (!flag.getSuccess()) {
                throw new GuliException("删除视频失败");
            }
        }
        boolean result = this.removeById(id);
        return result;
    }

    @Override
    public boolean removeByCourseId(String id) {
        //根据课程id查询所有视频列表
        List<EduVideo> videoList = this.list(Wrappers.<EduVideo>lambdaQuery().eq(EduVideo::getCourseId, id));
        //得到所有视频列表的云端原始视频id
        List<String> videoSourceIdList = new ArrayList<>();
        for (int i = 0; i < videoList.size(); i++) {
            EduVideo video = videoList.get(i);
            String videoSourceId = video.getVideoSourceId();
            if (!StrUtil.isEmpty(videoSourceId)) {
                videoSourceIdList.add(videoSourceId);
            }
        }
        //调用vod服务删除远程视频
        if (videoSourceIdList.size() > 0) {
            remoteVodService.removeVideoList(videoSourceIdList);
        }
        //删除video表示的记录
        boolean result = this.remove(Wrappers.<EduVideo>lambdaQuery().eq(EduVideo::getCourseId, id));
        return result;
    }

}
