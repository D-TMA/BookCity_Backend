package service.impl;

import dao.CategoryDao;
import domain.dto.CategoryVO;
import service.CategoryServiceIface;

import java.util.List;

public class CategoryServiceImpl implements CategoryServiceIface {
    CategoryDao categoryDao = new CategoryDao();
    @Override
    public List<CategoryVO> getCategoryList() {
        return categoryDao.selectCategoryList();
    }
}
