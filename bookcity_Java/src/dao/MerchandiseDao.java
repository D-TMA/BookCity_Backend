package dao;

import domain.dto.CategoryVO;
import domain.dto.MerchandiseVO;
import utils.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MerchandiseDao {
    /**
     * 根据是否特价查询商品列表
     * @param special
     * @param query
     * @return
     */
    public List<MerchandiseVO> selectMerchandiseListBySpecial(int special, String query) {
        List<MerchandiseVO> merchandiseList = new ArrayList<>();
        //1- 获得数据库连接
        Connection connection = DataSource.getConnection();
        //2- 操作数据库表
        try {
            //2-1 获得sql语句
            String sql = null;
            if ("all".equals(query)) {
                sql = "select id, category, merName, price, sprice, merModel, picture," +
                        "merDesc, manufacturer, leaveFactoryDate, special " +
                        "from merchandise where special = ?";
            } else {
                sql = "select id, category, merName, price, sprice, merModel, picture," +
                        "merDesc, manufacturer, leaveFactoryDate, special " +
                        "from merchandise where special = ? limit 3";
            }
            //2-2 获得承装sql的容器对象
            PreparedStatement ps = connection.prepareStatement(sql);
            //对？传值
            ps.setInt(1,special);
            //2-3 执行sql语句
            ResultSet set = ps.executeQuery();
            while (set.next()) {
                MerchandiseVO vo = new MerchandiseVO(set.getInt("id"), set.getInt("category"),
                        set.getString("merName"), set.getBigDecimal("price"), set.getBigDecimal("sprice"),
                        set.getString("merModel"), set.getString("picture"), set.getString("merDesc"),
                        set.getString("manufacturer"), set.getString("leaveFactoryDate"), set.getInt("special"));
                merchandiseList.add(vo);
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
        return merchandiseList;
    }

    /**
     * 通过ID查询商品列表
     * @param id
     * @return
     */
    public MerchandiseVO selectMerchandiseListById(int id) {
        MerchandiseVO vo = null;
        //1- 获得数据库连接
        Connection connection = DataSource.getConnection();
        //2- 操作数据库表
        try {
            //2-1 获得承装sql的容器对象
            String sql = "select id, category, merName, price, sprice, merModel, picture," +
                    "merDesc, manufacturer, DATE_FORMAT(leaveFactoryDate,'%Y-%m-%d') leaveFactoryDate, special " +
                    "from merchandise where id = ?";
            //2-2 获得sql语句
            PreparedStatement ps = connection.prepareStatement(sql);
            //对？传值
            ps.setInt(1,id);
            //2-3 执行sql语句
            ResultSet set = ps.executeQuery();
            if (set.next()) {
                vo = new MerchandiseVO(set.getInt("id"), set.getInt("category"),
                        set.getString("merName"), set.getBigDecimal("price"), set.getBigDecimal("sprice"),
                        set.getString("merModel"), set.getString("picture"), set.getString("merDesc"),
                        set.getString("manufacturer"), set.getString("leaveFactoryDate"), set.getInt("special"));
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
