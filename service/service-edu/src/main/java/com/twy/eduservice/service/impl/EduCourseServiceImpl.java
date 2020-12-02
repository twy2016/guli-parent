package com.twy.eduservice.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.twy.eduservice.dto.EduCourseDTO;
import com.twy.eduservice.entity.EduCourse;
import com.twy.eduservice.entity.EduCourseDescription;
import com.twy.eduservice.mapper.EduCourseMapper;
import com.twy.eduservice.service.EduCourseDescriptionService;
import com.twy.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.twy.servicebase.exception.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author gongpeng
 * @since 2020-12-01
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Override
    public String addCourse(EduCourseDTO eduCourseDTO) {
        //保存课程基本信息
        EduCourse course = new EduCourse();
        BeanUtil.copyProperties(eduCourseDTO, course);
        course.setStatus(EduCourse.COURSE_DRAFT);
        boolean resultCourseInfo = this.save(course);
        if (!resultCourseInfo) {
            throw new GuliException("课程信息保存失败");
        }
        //保存课程详情信息
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setDescription(eduCourseDTO.getDescription());
        courseDescription.setId(course.getId());
        boolean resultDescription = eduCourseDescriptionService.save(courseDescription);
        if(!resultDescription){
            throw new GuliException("课程详情信息保存失败");
        }
        return course.getId();
    }
}
