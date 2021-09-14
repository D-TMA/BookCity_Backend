package service;

import domain.dto.MemberVO;

public interface MemberServiceIface {
    /**
     * 登录
     * @param loginName
     * @param loginPwd
     * @return
     */
    MemberVO login(String loginName, String loginPwd);
}
