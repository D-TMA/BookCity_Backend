package domain;

public class LeaveWordPO {
    private Integer id;
    private Integer member;
    private String title;
    private String content;
    private String leaveDate;
    private String answerContent;
    private String answerDate;

    public LeaveWordPO() {
    }

    public LeaveWordPO(Integer id, Integer member, String title, String content, String leaveDate, String answerContent, String answerDate) {
        this.id = id;
        this.member = member;
        this.title = title;
        this.content = content;
        this.leaveDate = leaveDate;
        this.answerContent = answerContent;
        this.answerDate = answerDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMember() {
        return member;
    }

    public void setMember(Integer member) {
        this.member = member;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(String leaveDate) {
        this.leaveDate = leaveDate;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public String getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(String answerDate) {
        this.answerDate = answerDate;
    }


}
