package dao;

import domain.dto.CartSelectedMerVO;
import domain.dto.CartVO;
import domain.dto.CategoryVO;
import domain.dto.OrdersVO;
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

    /**
     * 给购物车插入商品
     * @param member
     * @return
     */
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

    /**
     * 通过购物车查询购物列表
     * @param cart
     * @return
     */
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
            //3- 关闭数据库连接
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return cartSelectedMerList;
    }

    /**
     * 更新物品数量
     * @param id
     * @param cart
     * @return
     */
    public boolean updateCartSelectedMerNum(Integer id, Integer cart) {
        //1- 获得数据库连接
        Connection connection = DataSource.getConnection();
        //2- 操作数据库表
        try{
            //把提交方式改为手动
            connection.setAutoCommit(false);
            //2-1 获得sql语句
            String sql="update cartselectedmer set number=number+1,money=money+price where id=?";
            //2-2 获得承装sql的容器对象
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,id);
            //2-3 执行sql语句
            int row1 = ps.executeUpdate();
            //更新购物车
            String sql2 = "update cart set money=(select sum(money) from cartselectedmer where cart=? group by cart) where id=?";
            ps = connection.prepareStatement(sql2);
            ps.setInt(1,cart);
            ps.setInt(2,cart);
            int row2 = ps.executeUpdate();
            if(row1>0 && row2>0) {
                connection.commit();//手动提交
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                // 3-关闭数据库连接
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 通过购物车ID和商品项查询购物列表
     * @param cart
     * @param merchandise
     * @return
     */
    public CartSelectedMerVO selectCartSelectedMerByCartAndMid(Integer cart, int merchandise) {
        CartSelectedMerVO vo = null;
        //1- 获得数据库连接
        Connection connection = DataSource.getConnection();
        //2-1 操作数据库表
        try {
            //2-1 获得sql语句
            String sql="select c.id,c.cart,c.merchandise,c.number,c.price,c.money " +
                    "from cartselectedmer c where cart=? and c.merchandise=?";
            //2-2 获得承装sql的容器对象
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,cart);
            ps.setInt(2,merchandise);
            //2-3 执行sql语句
            ResultSet set = ps.executeQuery();
            if (set.next()){
                vo = new CartSelectedMerVO(set.getInt("id"),set.getInt("cart") ,
                        set.getInt("merchandise") , set.getInt("number") , set.getBigDecimal("price") ,
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
        return vo;
    }

    /**
     * 添加购物项 商品个数为1
     * @param cart
     * @param merchandise
     * @param price
     * @return
     */
    public boolean addCartSelectedMer(Integer cart, Integer merchandise, BigDecimal price) {
        //1- 获得数据库连接
        Connection connection = DataSource.getConnection();
        //2- 操作数据库表
        try{
            //把提交方式改为手动
            connection.setAutoCommit(false);
            //2-1 获得sql语句
            String sql="insert into cartselectedmer(cart,merchandise,number,price,money) values (?,?,1,?,?)";
            //2-2 获得承装sql的容器对象
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,cart);
            ps.setInt(2,merchandise);
            ps.setBigDecimal(3,price);
            ps.setBigDecimal(4,price);
            //2-3 执行sql语句
            int row1 = ps.executeUpdate();
            //更新购物车
            String sql2 = "update cart set money=(select sum(money) from cartselectedmer where cart=? group by cart) where id=?";
            ps = connection.prepareStatement(sql2);
            ps.setInt(1,cart);
            ps.setInt(2,cart);
            int row2 = ps.executeUpdate();
            if(row1>0 && row2>0){
                connection.commit();//手动提交
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();//手动回滚
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                // 3 关闭数据库连接
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 删除购物车商品项
     * @param id
     * @return
     */
    public boolean deleteCartSelectedMerById(int id, int cart) {
        //1- 获得数据库连接
        Connection connection = DataSource.getConnection();
        //2- 操作数据库表
        try{
            //把提交方式改为手动
            connection.setAutoCommit(false);
            //2-1 获得sql语句
            String sql="delete from cartselectedmer where id=?";
            //2-2 获得承装sql的容器对象
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,id);
            //2-3 执行sql语句
            int row1 = ps.executeUpdate();
            //更新购物车
            String sql2 = "update cart set money=(select sum(money) from cartselectedmer where cart=? group by cart) where id=?";
            ps = connection.prepareStatement(sql2);
            ps.setInt(1,cart);
            ps.setInt(2,cart);
            int row2 = ps.executeUpdate();
            if(row1>0 && row2>0) {
                connection.commit();//手动提交
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
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

    /**
     * 清空购物车
     * @param cart
     * @return
     */
    public boolean deleteCartSelectedMerByCart(Integer cart) {
        //1- 获得数据库连接
        Connection connection = DataSource.getConnection();
        //2- 操作数据库表
        try{
            //把提交方式改为手动
            connection.setAutoCommit(false);
            //2-2 获得sql语句
            String sql="delete from cartselectedmer where cart=?";
            //2-1 获得承装sql的容器对象
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,cart);
            //2-3 执行sql语句
            int row1 = ps.executeUpdate();
            //更新购物车
            String sql2 = "update cart set money=(select sum(money) from cartselectedmer where cart=? group by cart) where id=?";
            ps = connection.prepareStatement(sql2);
            ps.setInt(1,cart);
            ps.setInt(2,cart);
            int row2 = ps.executeUpdate();
            if(row1>0 && row2>0) {
                connection.commit();//手动提交
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                // 3 关闭数据库连接
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 通过id修改商品数量
     * @param id
     * @param num
     * @param cart
     * @return
     */
    public boolean updateCartSelectedMerNumById(int id, int num, int cart) {
        //1- 获得数据库连接
        Connection connection = DataSource.getConnection();
        //2- 操作数据库表
        try{
            //把提交方式改为手动
            connection.setAutoCommit(false);
            //2-1 获得sql语句
            String sql="update cartselectedmer set number=?,money=price*? where id=?";
            //2-2 获得承装sql的容器对象
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,num);
            ps.setInt(2,num);
            ps.setInt(3,id);
            //2-3 执行sql语句
            int row1 = ps.executeUpdate();
            //更新我们得购物车
            String sql2 = "update cart set money=(select sum(money) from cartselectedmer where cart=? group by cart) where id=?";
            ps = connection.prepareStatement(sql2);
            ps.setInt(1,cart);
            ps.setInt(2,cart);
            int row2 = ps.executeUpdate();
            if(row1>0 && row2>0){
                connection.commit();//手动提交
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();//手动回滚
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
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

    public boolean submitShoppingCart(Integer cart, Integer member, String orderNO, String orderDate) {
        //1- 获得数据库连接
        Connection connection = DataSource.getConnection();
        //2- 操作数据库表
        try{
            //把提交方式改为手动
            connection.setAutoCommit(false);
            //把购物车的状态修改为1
            String sql1 = "update cart set cartStatus=1 where id=?";
            PreparedStatement ps = connection.prepareStatement(sql1);
            ps.setInt(1,cart);
            int row1 = ps.executeUpdate();
            //生成一个订单信息
            String sql2 = "insert into orders(member,cart,orderNO,orderDate,orderStatus) values (?,?,?,?,1)";
            ps = connection.prepareStatement(sql2);
            ps.setInt(1,member);
            ps.setInt(2,cart);
            ps.setString(3,orderNO);
            ps.setString(4,orderDate);
            int row2 = ps.executeUpdate();
            //继续购物需要重新创建购物车
            String sql3="insert into cart(member,money,cartStatus) values (?,?,?)";
            ps = connection.prepareStatement(sql3);
            ps.setInt(1,member);
            ps.setBigDecimal(2,new BigDecimal("0.00"));
            ps.setInt(3,0);
            int row3 = ps.executeUpdate();
            if (row1>0 && row2>0 && row3>0) {
                connection.commit();// 提交数据
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
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
}
