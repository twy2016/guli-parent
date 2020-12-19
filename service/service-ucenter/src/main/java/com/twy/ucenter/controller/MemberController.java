package com.twy.ucenter.controller;


import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.twy.commonutils.R;
import com.twy.ucenter.service.MemberService;
import com.twy.ucenter.vo.LoginVo;
import com.twy.ucenter.vo.RegisterVo;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author gongpeng
 * @since 2020-12-19
 */
@RestController
@RequestMapping("/ucenter/member")
@AllArgsConstructor
@CrossOrigin
public class MemberController {

    private final MemberService memberService;
    private final RedisTemplate redisTemplate;

    @ApiOperation(value = "会员登录")
    @PostMapping("login")
    public R login(@RequestBody LoginVo loginVo) {
        String token = memberService.login(loginVo);
        return R.ok(token);
    }

    @ApiOperation(value = "会员注册")
    @PostMapping("register")
    public R register(@RequestBody RegisterVo registerVo) {
        memberService.register(registerVo);
        return R.ok();
    }

    @GetMapping(value = "/send/{phone}")
    public R code(@PathVariable String phone) {
        String code = (String) redisTemplate.opsForValue().get(phone);
        if (!StrUtil.isEmpty(code)) {
            return R.ok();
        }
        code = RandomUtil.randomNumbers(4);
        Map<String, Object> param = new HashMap<>();
        param.put("code", code);
        redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);
        return R.ok();
    }

}

