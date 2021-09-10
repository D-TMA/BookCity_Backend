package service;

import dao.CategoryDao;
import domain.dto.CategoryVO;

import java.util.List;

public interface CategoryServiceIface {
    List<CategoryVO> getCategoryList();
}
