package domain.dto;

import domain.MemberPO;

public class MemberVO extends MemberPO {
    private String levelName;
    private int favourable;
    public MemberVO() {

    }

    public MemberVO(Integer id, Integer memberLevel, String loginName, String loginPwd, String memberName,
                    String phone, String address, String zip, String regDate, String lastDate, int loginTimes,
                    String email, String levelName, int favourable) {
        super(id, memberLevel, loginName, loginPwd, memberName, phone, address, zip, regDate, lastDate, loginTimes, email);
        this.levelName = levelName;
        this.favourable = favourable;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public int getFavourable() {
        return favourable;
    }

    public void setFavourable(int favourable) {
        this.favourable = favourable;
    }
}
