package domain;

public class MemberPO {
    private Integer id;
    private Integer memberLevel;
    private String loginName;
    private String loginPwd;
    private String memberName;
    private String phone;
    private String address;
    private String zip;
    private String regDate;
    private String lastDate;
    private int loginTimes;
    private String email;

    public MemberPO() {
    }

    public MemberPO(Integer id, Integer memberLevel, String loginName, String loginPwd, String memberName, String phone, String address, String zip, String regDate, String lastDate, int loginTimes, String email) {
        this.id = id;
        this.memberLevel = memberLevel;
        this.loginName = loginName;
        this.loginPwd = loginPwd;
        this.memberName = memberName;
        this.phone = phone;
        this.address = address;
        this.zip = zip;
        this.regDate = regDate;
        this.lastDate = lastDate;
        this.loginTimes = loginTimes;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(Integer memberLevel) {
        this.memberLevel = memberLevel;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public int getLoginTimes() {
        return loginTimes;
    }

    public void setLoginTimes(int loginTimes) {
        this.loginTimes = loginTimes;
    }
}
