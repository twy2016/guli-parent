package com.twy.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author gongpeng
 * @date 2020-11-22 16:35
 */
public interface OssService {
    String uploadFile(MultipartFile file);
}
