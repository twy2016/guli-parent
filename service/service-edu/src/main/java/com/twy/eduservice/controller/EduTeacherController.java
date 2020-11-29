package com.twy.eduservice.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.twy.eduservice.vo.TeacherVO;
import com.twy.commonutils.R;
import com.twy.eduservice.entity.EduTeacher;
import com.twy.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author gongpeng
 * @since 2020-11-12
 */
@RestController
@RequestMapping("/eduservice/teacher")
@AllArgsConstructor
@Api(value = "讲师管理模块",tags = "讲师管理模块")
@CrossOrigin
public class EduTeacherController {

    private final EduTeacherService eduTeacherService;

    @GetMapping("/findAll")
    public R<List<EduTeacher>> findAllTeacher() {
        List<EduTeacher> list = eduTeacherService.list();
        return R.ok(list);
    }

    @DeleteMapping("/{id}")
    public R removeTeacher(@PathVariable String id) {
        boolean flag = eduTeacherService.removeById(id);
        return flag ? R.ok() : R.error();
    }

    @GetMapping("/pageTeacher")
    public R<IPage<EduTeacher>> page(Page page) {
        Page result = eduTeacherService.page(page);
        return R.ok(result);
    }

    @GetMapping("/pageTeacherByCondition")
    public R<IPage<EduTeacher>> pageByCondition(Page page, TeacherVO vo) {
        QueryWrapper query = new QueryWrapper();
        String name = vo.getName();
        Integer level = vo.getLevel();
        String begin = vo.getBegin();
        String end = vo.getEnd();
        if (StrUtil.isNotEmpty(name)) {
            query.like("name", name);
        }
        if (level != null) {
            query.eq("level", level);
        }
        if (StrUtil.isNotEmpty(begin)) {
            query.ge("gmt_create", begin);
        }
        if (StrUtil.isNotEmpty(end)) {
            query.le("gmt_create", end);
        }
        query.orderByDesc("gmt_create");
        Page result = eduTeacherService.page(page, query);
        return R.ok(result);
    }

    @GetMapping("/{id}")
    public R getById(@PathVariable String id) {
        return R.ok(eduTeacherService.getById(id));
    }

    @PostMapping
    public R save(@RequestBody EduTeacher teacher){
        boolean flag = eduTeacherService.save(teacher);
        return flag ? R.ok(true) : R.error(false);
    }

    @PutMapping
    public R update(@RequestBody EduTeacher teacher){
        boolean flag = eduTeacherService.updateById(teacher);
        return flag ? R.ok(true) : R.error(false);
    }
}

