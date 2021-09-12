package utils;

import domain.dto.MemberVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {
    /**
     * 获得用户会员登录信息
     * @return
     */
    public static MemberVO getLoginMemberInfo(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("loginMember");
        MemberVO memberVO = (MemberVO) obj;
        return memberVO;
    }
}
