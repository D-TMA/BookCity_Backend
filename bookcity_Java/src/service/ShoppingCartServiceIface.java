package service;

import domain.dto.CartSelectedMerVO;
import domain.dto.CartVO;

import java.util.List;

public interface ShoppingCartServiceIface {
    /**
     * 获得购物车
     * @param id
     * @return
     */
    CartVO getShoppingCart(Integer id);

    /**
     * 按照购物车查询获得商品详情类别
     * @param id
     * @return
     */
    List<CartSelectedMerVO> getCartSelectedMerListByCart(Integer id);
}
