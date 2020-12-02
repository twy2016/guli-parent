package com.twy.eduservice.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.twy.eduservice.entity.EduSubject;
import com.twy.eduservice.entity.ExcelSubjectData;
import com.twy.eduservice.entity.SubjectTree;
import com.twy.eduservice.listener.SubjectExcelListener;
import com.twy.eduservice.mapper.EduSubjectMapper;
import com.twy.eduservice.service.EduSubjectService;
import com.twy.servicebase.exception.GuliException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author gongpeng
 * @since 2020-11-29
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void importSubjectData(MultipartFile file, EduSubjectService eduSubjectService) {
        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, ExcelSubjectData.class, new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
            throw new GuliException("添加课程分类失败");
        }
    }

    @Override
    public List<SubjectTree> getTreeSubject() {
        List<EduSubject> oneSubjects = this.list(Wrappers.<EduSubject>lambdaQuery().eq(EduSubject::getParentId, "0"));
        List<SubjectTree> tree = oneSubjects.stream().map(x -> {
            SubjectTree oneSubject = new SubjectTree();
            BeanUtil.copyProperties(x, oneSubject);
            List<EduSubject> twoSubjects = this.list(Wrappers.<EduSubject>lambdaQuery().eq(EduSubject::getParentId, oneSubject.getId()));
            oneSubject.setChildren(twoSubjects);
            return oneSubject;
        }).collect(Collectors.toList());
        return tree;
    }
}
