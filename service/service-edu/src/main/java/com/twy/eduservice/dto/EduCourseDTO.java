package com.twy.eduservice.dto;

import com.twy.eduservice.entity.EduCourse;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author gongpeng
 * @date 2020/12/1 16:17
 */
@Data
public class EduCourseDTO extends EduCourse {
    @ApiModelProperty(value = "课程简介")
    private String description;
}
