package com.twy.eduservice.service;

import com.twy.eduservice.dto.EduCourseDTO;
import com.twy.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author gongpeng
 * @since 2020-12-01
 */
public interface EduCourseService extends IService<EduCourse> {

    /**
     * 添加课程信息
     *
     * @param eduCourseDTO
     * @return
     */
    String addCourse(EduCourseDTO eduCourseDTO);
}
