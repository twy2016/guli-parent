package com.twy.eduservice.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.twy.eduservice.entity.EduVideo;
import com.twy.eduservice.mapper.EduVideoMapper;
import com.twy.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public boolean getCountByChapterId(String chapterId) {
        int count = this.count(Wrappers.<EduVideo>lambdaQuery().eq(EduVideo::getChapterId, chapterId));
        return count > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeVideoById(String id) {
        //删除视频资源 TODO

        boolean result = this.removeById(id);
        return result;
    }
}
