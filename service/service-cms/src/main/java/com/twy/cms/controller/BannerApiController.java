package com.twy.cms.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.twy.cms.entity.CrmBanner;
import com.twy.cms.service.CrmBannerService;
import com.twy.commonutils.R;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author gongpeng
 * @date 2020/12/18 23:14
 */
@RestController
@RequestMapping("/educms/banner")
@CrossOrigin
@AllArgsConstructor
public class BannerApiController {

    private final CrmBannerService bannerService;

    @ApiOperation(value = "获取首页banner")
    @GetMapping("/getAllBanner")
    @Cacheable(value = "branner", key = "'getAllBanner'")
    public R<List<CrmBanner>> index() {
        List<CrmBanner> list = bannerService.list(Wrappers.<CrmBanner>lambdaQuery().orderByAsc(CrmBanner::getSort));
        return R.ok(list);
    }
}
