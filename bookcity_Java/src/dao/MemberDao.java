package dao;

import domain.MemberPO;
import domain.dto.CategoryVO;
import domain.dto.MemberVO;
import utils.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {
    /**
     * 按照用户名和密码查询用户信息
     * @param dto
     * @return
     */
    public MemberVO selectMemberByLoginNameAndPwd(MemberVO dto) {
        MemberVO vo = null;
        //1- 获得数据库连接
        Connection connection = DataSource.getConnection();
        //2- 操作数据库表
        try {
            //2-1 获得承装sql的容器对象
            String sql = "select member.id, memberlevel, loginName, loginPwd, memberName, phone, address, zip,"+
                         "DATE_FORMAT(regDate,'%Y-%m-%d') regDate, DATE_FORMAT(lastDate,'%Y-%m-%d') lastDate,"+
                         "loginTimes, email, memberlevel.levelName, memberlevel.favourable "+
                         "from member, memberlevel where member.memberlevel = memberlevel.id and loginName = ? and loginPwd = ?";
            //2-2 获得sql语句
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, dto.getLoginName());
            ps.setString(2, dto.getLoginPwd());
            //2-3 执行sql语句
            ResultSet set = ps.executeQuery();
            if (set.next()) {
                vo = new MemberVO(set.getInt("id"), set.getInt("memberlevel"), set.getString("loginName"),
                        set.getString("loginPwd"), set.getString("memberName"), set.getString("phone"),
                        set.getString("address"), set.getString("zip"), set.getString("regDate"),
                        set.getString("lastDate"), set.getInt("loginTimes"), set.getString("email"),
                        set.getString("levelName"), set.getInt("favourable"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //3 关闭数据库连接
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return vo;
    }
}
