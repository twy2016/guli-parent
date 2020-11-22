package com.twy.eduservice.controller;

import com.twy.oss.commonutils.R;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gongpeng
 * @date 2020/11/19 0:03
 */
@RestController
@RequestMapping("/eduservice/user")
@Api(value = "登录模块",tags = "登录模块")
@CrossOrigin
public class EduLoginController {

    @PostMapping("/login")
    public R login(){
        Map map = new HashMap<>();
        map.put("token","admin");
        return R.ok(map);
    }

    @GetMapping("/info")
    public R info(){
        Map map = new HashMap<>();
        map.put("roles","[admin]");
        map.put("name","admin");
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return R.ok(map);
    }

}
