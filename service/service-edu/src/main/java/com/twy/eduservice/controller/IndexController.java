package com.twy.eduservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.twy.commonutils.R;
import com.twy.eduservice.entity.EduCourse;
import com.twy.eduservice.entity.EduTeacher;
import com.twy.eduservice.service.EduCourseService;
import com.twy.eduservice.service.EduTeacherService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author gongpeng
 * @date 2020/12/18 23:31
 */
@RestController
@RequestMapping("/eduservice/index")
//@CrossOrigin
@AllArgsConstructor
public class IndexController {

    private final EduCourseService courseService;
    private final EduTeacherService teacherService;

    /**
     * 查询前8条热门课程，查询前4条名师
     *
     * @return
     */
    @GetMapping("/courseList")
    public R eduCourseList() {
        //查询前8条热门课程
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 8");
        List<EduCourse> eduCourseList = courseService.list(wrapper);
        return R.ok(eduCourseList);
    }

    @GetMapping("/teacherList")
    public R index() {
        //查询前4条名师
        QueryWrapper<EduTeacher> wrapperTeacher = new QueryWrapper<>();
        wrapperTeacher.orderByDesc("id");
        wrapperTeacher.last("limit 4");
        List<EduTeacher> teacherList = teacherService.list(wrapperTeacher);
        return R.ok(teacherList);
    }
}
