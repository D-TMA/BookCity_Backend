package service.impl;

import dao.ShoppingCartDao;
import domain.dto.CartSelectedMerVO;
import domain.dto.CartVO;
import service.ShoppingCartServiceIface;

import java.util.List;

public class ShoppingCartServiceImpl implements ShoppingCartServiceIface {
    ShoppingCartDao shoppingCartDao = new ShoppingCartDao();
    @Override
    public CartVO getShoppingCart(Integer member) {
        CartVO cartVO = shoppingCartDao.selectCartByMember(member);
        if (cartVO==null) {
            shoppingCartDao.insert(member);
            cartVO = shoppingCartDao.selectCartByMember(member);
        }
        return cartVO;
    }

    @Override
    public List<CartSelectedMerVO> getCartSelectedMerListByCart(Integer cart) {
        return shoppingCartDao.selectCartSelectedMerListByCart(cart);
    }
}
