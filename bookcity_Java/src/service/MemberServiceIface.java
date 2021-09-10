package service;

import domain.dto.MemberVO;

public interface MemberServiceIface {
    MemberVO login(String loginName, String loginPwd);
}
