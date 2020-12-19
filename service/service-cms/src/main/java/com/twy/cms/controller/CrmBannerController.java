package com.twy.cms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.twy.cms.entity.CrmBanner;
import com.twy.cms.service.CrmBannerService;
import com.twy.commonutils.R;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author gongpeng
 * @since 2020-12-18
 */
@RestController
@RequestMapping("/eduservice/banner")
@AllArgsConstructor
@CrossOrigin
public class CrmBannerController {

    private final CrmBannerService crmBannerService;

    @GetMapping("/page")
    public R<Page<CrmBanner>> page(Page page) {
        Page<CrmBanner> result = crmBannerService.page(page);
        return R.ok(result);
    }

    @GetMapping("/{id}")
    public R<CrmBanner> getById(@PathVariable String id) {
        return R.ok(crmBannerService.getById(id));
    }

    @PostMapping
    @CacheEvict(value = "banner", allEntries=true)
    public R<Boolean> save(@RequestBody CrmBanner crmBanner) {
        return R.ok(crmBannerService.save(crmBanner));
    }

    @PutMapping
    @CacheEvict(value = "banner", allEntries=true)
    public R<Boolean> update(@RequestBody CrmBanner crmBanner) {
        return R.ok(crmBannerService.updateById(crmBanner));
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "banner", allEntries=true)
    public R<Boolean> remove(@PathVariable String id) {
        return R.ok(crmBannerService.removeById(id));
    }
}

