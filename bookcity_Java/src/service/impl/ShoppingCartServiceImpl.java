package service.impl;

import dao.ShoppingCartDao;
import domain.dto.CartVO;
import service.ShoppingCartServiceIface;

public class ShoppingCartServiceImpl implements ShoppingCartServiceIface {
    ShoppingCartDao shoppingCartDao = new ShoppingCartDao();
    @Override
    public void getCartSelectedMerList(Integer id) {

    }

    @Override
    public CartVO getShoppingCart(Integer member) {
        CartVO cartVO = shoppingCartDao.selectCartByMember(member);
        if (cartVO==null) {
            shoppingCartDao.insert(member);
            cartVO = shoppingCartDao.selectCartByMember(member);
        }
        return cartVO;
    }
}
