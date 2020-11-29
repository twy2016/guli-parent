package com.twy.eduservice.service;

import com.twy.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.twy.eduservice.entity.SubjectTree;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author gongpeng
 * @since 2020-11-29
 */
public interface EduSubjectService extends IService<EduSubject> {

    void importSubjectData(MultipartFile file, EduSubjectService eduSubjectService);

    List<SubjectTree> getTreeSubject();
}
