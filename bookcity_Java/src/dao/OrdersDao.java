package dao;

import domain.dto.OrdersVO;
import utils.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
                //3- 关闭数据库连接
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 根据会员ID查询订单列表 按照时间倒序
     * @param member
     * @return
     */
    public List<OrdersVO> selectOrdersListByMid(Integer member) {
        List<OrdersVO> ordersList = new ArrayList<>();
        //1- 获得数据库的连接
        Connection connection = DataSource.getConnection();
        //2- 操作数据库表
        try{
            //2-1 获得sql语句
            String sql="select orders.id,orders.member,cart,orderNO,DATE_FORMAT(orderDate,'%Y-%m-%d') orderDate,orderStatus,money from orders,cart"
                    + " where orders.cart=cart.id and orders.member=? order by orderDate desc";
            //2-2 获得承装sql的容器对象
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,member);
            //2-3 执行sql语句
            ResultSet set = ps.executeQuery();
            while (set.next()) {
                OrdersVO ordersVO = new OrdersVO(set.getInt("id"), set.getInt("member"), set.getInt("cart"),
                        set.getString("orderNO"), set.getString("orderDate"), set.getInt("orderStatus"),
                        set.getBigDecimal("money"));
                ordersList.add(ordersVO);
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
        return ordersList;
    }

    /**
     * 根据订单ID删除订单
     * @param id
     * @return
     */
    public boolean deleteOrdersById(int id) {
        //1- 获得数据库的连接
        Connection connection = DataSource.getConnection();
        //2- 操作数据库表
        try{
            //2-1 获得sql语句
            String sql="delete from orders where id=?";
            //2-2 获得承装sql的容器对象
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,id);
            //2-3 执行sql语句
            int row = ps.executeUpdate();
            if(row>0)
                return true;
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
        return false;
    }

    public OrdersVO selectOrdersById(int id) {
        //1- 获得数据库的连接
        Connection connection = DataSource.getConnection();
        //2- 操作数据库表
        try{
            // 2-1 获得sql语句
            String sql="select orders.id,orders.member,cart,orderNO,DATE_FORMAT(orderDate,'%Y-%m-%d %H:%i:%S') orderDate,orderStatus,money from orders,cart"
                    + " where orders.cart=cart.id and orders.id=?";
            // 2-2 获得承装sql的容器对象
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,id);
            // 2-3 执行sql语句
            ResultSet set = ps.executeQuery();
            if(set.next()){
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
