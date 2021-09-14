package dao;

import domain.dto.OrdersVO;
import utils.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdersDao {

    /**
     * 根据订单编号查询订单信息
     * @param orderNO
     * @return
     */
    public OrdersVO selectOrdersByOrderNO(String orderNO) {
        //1- 获得数据库的连接
        Connection connection = DataSource.getConnection();
        //2- 操作数据库表
        try{
            //2-1 获得sql语句
            String sql="select orders.id,orders.member,cart,orderNO,DATE_FORMAT(orderDate,'%Y-%m-%d %H:%i:%S') orderDate,orderStatus,money from orders,cart"
                    + " where orders.cart=cart.id and orders.orderNO=?";
            //2-2 获得承装sql的容器对象
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,orderNO);
            //2-3 执行sql语句
            ResultSet set = ps.executeQuery();
            if (set.next()) {
                return new OrdersVO(set.getInt("id"), set.getInt("member"), set.getInt("cart"),
                        set.getString("orderNO"), set.getString("orderDate"), set.getInt("orderStatus"),
                        set.getBigDecimal("money"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 3 关闭数据库连接
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
