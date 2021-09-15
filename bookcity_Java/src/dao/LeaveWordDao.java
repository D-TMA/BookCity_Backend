package dao;

import domain.dto.LeaveWordVO;
import domain.dto.OrdersVO;
import utils.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LeaveWordDao {
    public List<LeaveWordVO> selectLeaveWordList() {
        List<LeaveWordVO> leaveWordList = new ArrayList<>();
        //1- 获得数据库的连接
        Connection connection = DataSource.getConnection();
        //2- 操作数据库表
        try{
            //2-1 获得sql语句
            String sql="select leaveword.id id,leaveword.member,title,content,leaveDate,answerContent,answerDate,memberName "+
                    "from leaveword,member where leaveword.member=member.id";
            //2-2 获得承装sql的容器对象
            PreparedStatement ps = connection.prepareStatement(sql);
            //2-3 执行sql语句
            ResultSet set = ps.executeQuery();
            while (set.next()) {
                LeaveWordVO leavewordVO = new LeaveWordVO(set.getInt("id"), set.getInt("member"), set.getString("title"),
                        set.getString("content"), set.getString("leaveDate"), set.getString("answerContent"),
                        set.getString("answerDate"), set.getString("memberName"));
                leaveWordList.add(leavewordVO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                //3- 关闭数据库连接
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return leaveWordList;
    }
}
