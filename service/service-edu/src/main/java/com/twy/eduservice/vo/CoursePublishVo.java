package com.twy.eduservice.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author gongpeng
 * @date 2020/12/7 15:36
 */
@Data
public class CoursePublishVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    //只用于显示
    private String price;
}
