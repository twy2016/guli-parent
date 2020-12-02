package com.twy.eduservice.controller;


import cn.hutool.core.util.StrUtil;
import com.twy.commonutils.R;
import com.twy.eduservice.dto.EduCourseDTO;
import com.twy.eduservice.entity.EduCourse;
import com.twy.eduservice.service.EduCourseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author gongpeng
 * @since 2020-12-01
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
@AllArgsConstructor
public class EduCourseController {
    private final EduCourseService eduCourseService;

    @PostMapping
    public R addCourse(@RequestBody EduCourseDTO eduCourseDTO) {
        eduCourseDTO.setTeacherId("1189426464967995393");
        eduCourseDTO.setSubjectId("1178214681118568449");
        eduCourseDTO.setSubjectParentId("1178214681139539969");
        String courseId = eduCourseService.addCourse(eduCourseDTO);
        return StrUtil.isNotEmpty(courseId) ? R.ok(courseId) : R.error(false);
    }
}

