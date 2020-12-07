package com.twy.eduservice.controller;


import com.twy.commonutils.R;
import com.twy.eduservice.entity.EduChapter;
import com.twy.eduservice.service.EduChapterService;
import com.twy.eduservice.vo.ChapterVo;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author gongpeng
 * @since 2020-12-07
 */
@RestController
@RequestMapping("/eduservice/chapter")
@AllArgsConstructor
@CrossOrigin
public class EduChapterController {
    private final EduChapterService chapterService;

    @GetMapping("/list/{courseId}")
    public R getChapterListByCourseId(@PathVariable String courseId) {
        List<ChapterVo> chapterVoList = chapterService.getChapterListByCourseId(courseId);
        return R.ok(chapterVoList);
    }

    @PostMapping
    public R save(@RequestBody EduChapter chapter) {
        chapterService.save(chapter);
        return R.ok();
    }

    @PutMapping
    public R update(@RequestBody EduChapter chapter) {
        chapterService.updateById(chapter);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable String id) {
        boolean result = chapterService.removeChapterById(id);
        return result?R.ok(true):R.error(false,"删除失败");
    }

    @GetMapping("/{id}")
    public R getById(@PathVariable String id) {
        EduChapter chapter = chapterService.getById(id);
        return R.ok(chapter);
    }
}

