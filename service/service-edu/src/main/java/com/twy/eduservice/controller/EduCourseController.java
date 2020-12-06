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
        String courseId = eduCourseService.addCourse(eduCourseDTO);
        return StrUtil.isNotEmpty(courseId) ? R.ok(courseId) : R.error(false);
    }

    @PutMapping
    public R updateCourse(@RequestBody EduCourseDTO eduCourseDTO) {
        boolean result = eduCourseService.updateCourse(eduCourseDTO);
        return result ? R.ok(true) : R.error(false);
    }

    @GetMapping("/{id}")
    public R getCourseInfoById(@PathVariable("id") String id) {
        EduCourseDTO info = eduCourseService.getCourseInfoById(id);
        return R.ok(info);
    }
}

