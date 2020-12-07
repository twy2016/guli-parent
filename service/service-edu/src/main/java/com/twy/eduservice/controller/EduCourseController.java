package com.twy.eduservice.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.twy.commonutils.R;
import com.twy.eduservice.dto.EduCourseDTO;
import com.twy.eduservice.entity.EduCourse;
import com.twy.eduservice.entity.EduTeacher;
import com.twy.eduservice.service.EduCourseService;
import com.twy.eduservice.vo.CoursePublishVo;
import com.twy.eduservice.vo.CourseVo;
import io.swagger.annotations.ApiParam;
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

    @DeleteMapping("/{id}")
    public R removeById(@PathVariable("id") String id) {
        boolean result = eduCourseService.removeCourseById(id);
        return R.ok(result);
    }

    @GetMapping("/publish/{id}")
    public R getCoursePublishVoById(@PathVariable("id") String id) {
        CoursePublishVo info = eduCourseService.getCoursePublishVoById(id);
        return R.ok(info);
    }

    @PutMapping("/publish/{id}")
    public R publishCourseById(@PathVariable String id) {
        eduCourseService.publishCourseById(id);
        return R.ok();
    }

    @GetMapping("/page")
    public R<Page<EduCourse>> pageQuery(Page page, CourseVo courseVo) {
        QueryWrapper<EduCourse> query = new QueryWrapper<>();
        String title = courseVo.getTitle();
        String teacherId = courseVo.getTeacherId();
        String subjectParentId = courseVo.getSubjectParentId();
        String subjectId = courseVo.getSubjectId();
        if (!StrUtil.isEmpty(title)) {
            query.like("title", title);
        }
        if (!StrUtil.isEmpty(teacherId)) {
            query.eq("teacher_id", teacherId);
        }
        if (!StrUtil.isEmpty(subjectParentId)) {
            query.eq("subject_parent_id", subjectParentId);
        }
        if (!StrUtil.isEmpty(subjectId)) {
            query.eq("subject_id", subjectId);
        }
        query.orderByDesc("gmt_create");
        Page<EduCourse> result = eduCourseService.page(page, query);
        return R.ok(result);
    }
}

