package service;

import dao.CategoryDao;
import domain.dto.CategoryVO;

import java.util.List;

public interface CategoryServiceIface {
    /**
     * 获得商品列表
     * @return
     */
    List<CategoryVO> getCategoryList();
}
