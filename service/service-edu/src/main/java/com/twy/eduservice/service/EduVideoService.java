package com.twy.eduservice.service;

import com.twy.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author gongpeng
 * @since 2020-12-07
 */
public interface EduVideoService extends IService<EduVideo> {

    boolean getCountByChapterId(String chapterId);

    boolean removeVideoById(String id);

    boolean removeByCourseId(String id);
}
