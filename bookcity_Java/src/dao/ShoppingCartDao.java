package dao;

import domain.dto.CartVO;
import domain.dto.CategoryVO;
import utils.DataSource;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShoppingCartDao {
    /**
     * 按照用户id查询没有提交的购物车
     * @param member
     * @return
     */
    public CartVO selectCartByMember(Integer member) {
        CartVO cartVO = null;
        //1- 获得数据库连接
        Connection connection = DataSource.getConnection();
        //2- 操作数据库表
        try {
            //2-1 获得承装sql的容器对象
            String sql = "select id, member, money, cartStatus from cart where member = ? and cartStatus = 0";
            //2-2 获得sql语句
            PreparedStatement ps = connection.prepareStatement(sql);
            //2-3 执行sql语句
            ResultSet set = ps.executeQuery();
            ps.setInt(1,member);
            if (set.next()) {
                cartVO = new CartVO(set.getInt("id"),set.getInt("member"),set.getBigDecimal("money"),
                                    set.getInt("cartStatus"));
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
        return cartVO;
    }

    public boolean insert(Integer member) {
        //1- 获得数据库连接
        Connection connection = DataSource.getConnection();
        //2- 操作数据库表
        try {
            //2-1 获得承装sql的容器对象
            String sql = "insert into cart(member,money,cartStatus) values (?,?,?)";
            //2-2 获得sql语句
            PreparedStatement ps = connection.prepareStatement(sql);
            //2-3 执行sql语句
            ps.setInt(1,member);
            ps.setBigDecimal(2,new BigDecimal("0"));
            ps.setInt(3,0);
            int row = ps.executeUpdate();
            if (row > 0) {
                return true;
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
        return false;
    }
}
