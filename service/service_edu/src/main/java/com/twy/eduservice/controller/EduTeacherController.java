package com.twy.eduservice.controller;


import com.twy.eduservice.entity.EduTeacher;
import com.twy.eduservice.service.EduTeacherService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
public class EduTeacherController {

    private final EduTeacherService eduTeacherService;

    @GetMapping("/findAll")
    public List<EduTeacher> findAllTeacher(){
        return eduTeacherService.list();
    }

}

