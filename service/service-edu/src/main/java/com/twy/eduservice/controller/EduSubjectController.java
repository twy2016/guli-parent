package com.twy.eduservice.controller;


import com.twy.commonutils.R;
import com.twy.eduservice.entity.SubjectTree;
import com.twy.eduservice.service.EduSubjectService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author gongpeng
 * @since 2020-11-29
 */
@RestController
@RequestMapping("/eduservice/subject")
@AllArgsConstructor
//@CrossOrigin
public class EduSubjectController {
    private final EduSubjectService eduSubjectService;

    @PostMapping("/addSubject")
    public R addSubject(MultipartFile file) {
        eduSubjectService.importSubjectData(file, eduSubjectService);
        return R.ok();
    }

    @GetMapping("/tree")
    public R tree() {
        List<SubjectTree> tree = eduSubjectService.getTreeSubject();
        return R.ok(tree);
    }
}

