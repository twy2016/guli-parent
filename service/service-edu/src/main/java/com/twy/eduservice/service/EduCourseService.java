package com.twy.eduservice.service;

import com.twy.eduservice.dto.EduCourseDTO;
import com.twy.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.twy.eduservice.vo.CoursePublishVo;

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

    /**
     * 根据Id获取课程详情
     * @param id
     * @return
     */
    EduCourseDTO getCourseInfoById(String id);

    /**
     * 修改课程信息
     *
     * @param eduCourseDTO
     * @return
     */
    boolean updateCourse(EduCourseDTO eduCourseDTO);

    CoursePublishVo getCoursePublishVoById(String id);

    boolean publishCourseById(String id);

    boolean removeCourseById(String id);
}
