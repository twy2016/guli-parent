package com.twy.eduservice.entity;

import lombok.Data;

import java.util.List;

/**
 * @author gongpeng
 * @date 2020/11/29 20:53
 */
@Data
public class SubjectTree {

    private String id;
    private String title;
    private List<EduSubject> children;
}
