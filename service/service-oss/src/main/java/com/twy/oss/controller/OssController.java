package com.twy.oss.controller;

import com.twy.commonutils.R;
import com.twy.oss.service.OssService;
import com.twy.servicebase.exception.GuliException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author gongpeng
 * @date 2020/11/22 16:33
 */
@RestController
@RequestMapping("/eduoss/file")
@AllArgsConstructor
@CrossOrigin
public class OssController {

    private final OssService ossService;

    @PostMapping
    public R upload(MultipartFile file) {
        if (file == null) {
            throw new GuliException("文件为空");
        }
        String url = ossService.uploadFile(file);
        return R.ok(url);
    }
}
