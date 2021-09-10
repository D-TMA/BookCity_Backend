package service.impl;

import dao.MemberDao;
import domain.MemberPO;
import domain.dto.MemberVO;
import service.MemberServiceIface;

public class MemerServiceImpl implements MemberServiceIface {
    MemberDao memberDao = new MemberDao();
    @Override
    public MemberVO login(String loginName, String loginPwd) {
        MemberVO dto = new MemberVO();
        dto.setLoginName(loginName);
        dto.setLoginPwd(loginPwd);
        return memberDao.selectMemberByLoginNameAndPwd(dto);
    }
}
