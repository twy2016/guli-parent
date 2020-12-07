package com.twy.eduservice.service;

import com.twy.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.twy.eduservice.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author gongpeng
 * @since 2020-12-07
 */
public interface EduChapterService extends IService<EduChapter> {

    /**
     * 根据课程Id获取课程章节
     *
     * @param courseId
     * @return
     */
    List<ChapterVo> getChapterListByCourseId(String courseId);

    boolean removeChapterById(String id);
}
