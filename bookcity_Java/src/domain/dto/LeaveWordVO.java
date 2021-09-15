package domain.dto;

import domain.LeaveWordPO;

public class LeaveWordVO extends LeaveWordPO {
    private String memberName;

    public LeaveWordVO() {
    }

    public LeaveWordVO(Integer id, Integer member, String title, String content, String leaveDate, String answerContent, String answerDate, String memberName) {
        super(id, member, title, content, leaveDate, answerContent, answerDate);
        this.memberName = memberName;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}
