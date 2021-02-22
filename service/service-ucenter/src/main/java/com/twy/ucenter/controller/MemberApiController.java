package com.twy.ucenter.controller;

import com.twy.commonutils.R;
import com.twy.ucenter.entity.Member;
import com.twy.ucenter.service.MemberService;
import com.twy.ucenter.util.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author gongpeng
 * @date 2020/12/19 20:41
 */
@RestController
@AllArgsConstructor
@RequestMapping("/ucenter/auth")
//@CrossOrigin
public class MemberApiController {

    private final MemberService memberService;

    @GetMapping("/getLoginInfo")
    public R getLoginInfo(HttpServletRequest request) {
        String id = JwtUtils.getIdByToken(request.getHeader("token"));
        Member member = memberService.getById(id);
        return R.ok(member);
    }
}
