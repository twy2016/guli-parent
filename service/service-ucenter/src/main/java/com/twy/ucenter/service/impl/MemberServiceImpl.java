package com.twy.ucenter.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.twy.ucenter.util.JwtUtils;
import com.twy.servicebase.exception.GuliException;
import com.twy.ucenter.entity.Member;
import com.twy.ucenter.mapper.MemberMapper;
import com.twy.ucenter.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.twy.ucenter.vo.LoginVo;
import com.twy.ucenter.vo.RegisterVo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author gongpeng
 * @since 2020-12-19
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public String login(LoginVo loginVo) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        if (StrUtil.isBlank(mobile) || StrUtil.isBlank(password)) {
            throw new GuliException("参数错误");
        }
        Member member = this.getOne(Wrappers.<Member>lambdaQuery().eq(Member::getMobile, mobile));
        if (member == null) {
            throw new GuliException("用户名或密码错误");
        }
        if (!SecureUtil.md5(password).equals(member.getPassword())) {
            throw new GuliException("用户名或密码错误");
        }
        if (member.getIsDisabled()) {
            throw new GuliException("该用户被禁用");
        }
        String token = JwtUtils.getToken(member.getId(), member.getNickname());
        return token;
    }

    @Override
    public void register(RegisterVo registerVo) {
        String mobile = registerVo.getMobile();
        String password = registerVo.getPassword();
        String nickname = registerVo.getNickname();
        String code = registerVo.getCode();
        if (StrUtil.isBlank(mobile) || StrUtil.isBlank(password) || StrUtil.isBlank(nickname) || StrUtil.isBlank(code)) {
            throw new GuliException("该用户被禁用");
        }
        String sendCode = (String) redisTemplate.opsForValue().get(mobile);
        if (!code.equals(sendCode)) {
            throw new GuliException("验证码不正确");
        }
        Member result = this.getOne(Wrappers.<Member>lambdaQuery().eq(Member::getMobile, mobile));
        if (result != null) {
            throw new GuliException("该用户已存在");
        }
        Member member = new Member();
        member.setMobile(mobile);
        member.setPassword(SecureUtil.md5(password));
        member.setNickname(nickname);
        member.setIsDisabled(false);
        member.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");
        this.save(member);
    }
}
