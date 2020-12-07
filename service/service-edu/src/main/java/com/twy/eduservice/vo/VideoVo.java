package com.twy.eduservice.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author gongpeng
 * @date 2020/12/7 14:07
 */
@Data
public class VideoVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private Boolean free;
}
