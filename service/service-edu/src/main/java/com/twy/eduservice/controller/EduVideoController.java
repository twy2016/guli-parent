package com.twy.eduservice.controller;


import com.twy.commonutils.R;
import com.twy.eduservice.entity.EduChapter;
import com.twy.eduservice.entity.EduVideo;
import com.twy.eduservice.service.EduVideoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author gongpeng
 * @since 2020-12-07
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
@AllArgsConstructor
public class EduVideoController {

    private final EduVideoService videoService;

    @PostMapping
    public R save(@RequestBody EduVideo video) {
        videoService.save(video);
        return R.ok();
    }

    @PutMapping
    public R update(@RequestBody EduVideo video) {
        videoService.updateById(video);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable String id) {
        boolean result = videoService.removeVideoById(id);
        return result ? R.ok(true) : R.error(false, "删除失败");
    }

    @GetMapping("/{id}")
    public R getById(@PathVariable String id) {
        EduVideo video = videoService.getById(id);
        return R.ok(video);
    }
}

