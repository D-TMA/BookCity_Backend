package dao;

import domain.dto.CategoryVO;
import utils.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {
    /**
     * 获得商品列表信息
     * @return
     */
    public List<CategoryVO> selectCategoryList() {
        List<CategoryVO> list = new ArrayList<>();
        //1- 获得数据库连接
        Connection connection = DataSource.getConnection();
        //2- 操作数据库表
        try {
            //2-1 获得承装sql的容器对象
            String sql = "select id, cateName, cateDesc from category";
            //2-2 获得sql语句
            PreparedStatement ps = connection.prepareStatement(sql);
            //2-3 执行sql语句
            ResultSet set = ps.executeQuery();
            while (set.next()) {
                CategoryVO categoryVO = new CategoryVO();
                categoryVO.setId(set.getInt("id"));
                categoryVO.setCateName(set.getString("cateName"));
                categoryVO.setCateDesc(set.getString("cateDesc"));
                list.add(categoryVO);
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
        return list;
    }
}
