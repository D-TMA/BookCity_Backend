package service;

import domain.dto.CartVO;

public interface ShoppingCartServiceIface {
    void getCartSelectedMerList(Integer id);

    CartVO getShoppingCart(Integer id);
}
