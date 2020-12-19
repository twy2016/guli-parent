package com.twy.ucenter.service;

import com.twy.ucenter.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;
import com.twy.ucenter.vo.LoginVo;
import com.twy.ucenter.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author gongpeng
 * @since 2020-12-19
 */
public interface MemberService extends IService<Member> {

    String login(LoginVo loginVo);

    void register(RegisterVo registerVo);
}
