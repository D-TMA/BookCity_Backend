package dao;

import domain.dto.CartSelectedMerVO;
import domain.dto.CartVO;
import domain.dto.CategoryVO;
import utils.DataSource;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
            ps.setInt(1,member);
            ResultSet set = ps.executeQuery();
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

    public List<CartSelectedMerVO> selectCartSelectedMerListByCart(Integer cart) {
        List<CartSelectedMerVO> cartSelectedMerList = new ArrayList<>();
        //1- 获得数据库连接
        Connection connection = DataSource.getConnection();
        //2- 操作数据库表
        try {
            //2-1 获得承装sql的容器对象
            String sql = "select c.id, c.cart, c.merchandise, c.number, c.price, c.money, m.merName merName, m.price merPrice " +
                         "from cartselectedmer c, merchandise m where cart = ? and c.merchandise = m.id";
            //2-2 获得sql语句
            PreparedStatement ps = connection.prepareStatement(sql);
            //2-3 执行sql语句
            ps.setInt(1,cart);
            ResultSet set = ps.executeQuery();
            while (set.next()) {
                CartSelectedMerVO vo = new CartSelectedMerVO(set.getInt("id"), set.getInt("cart"),
                        set.getInt("merchandise"), set.getInt("number"), set.getBigDecimal("price"),
                        set.getBigDecimal("money"), set.getString("merName"), set.getBigDecimal("merPrice"));
                cartSelectedMerList.add(vo);
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
        return cartSelectedMerList;
    }
}
