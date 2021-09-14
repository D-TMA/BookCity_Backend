package service;

import domain.dto.CartSelectedMerVO;
import domain.dto.CartVO;
import domain.dto.OrdersVO;

import java.math.BigDecimal;
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

    /**
     * 添加购物车
     * @param id
     * @param merId
     * @param favourable
     * @return
     */
    boolean addShoppingCart(Integer id, String merId, Integer favourable);

    /**
     * 修改商品项和购物车
     * @param id
     * @return
     */
    boolean updateCartSelectedMerNum(Integer id, Integer cart);

    /**
     * 按照购物车Id和商品项查看购物车详情信息
     * @param cart
     * @param merId
     * @return
     */
    CartSelectedMerVO getCartSelectedMerListByCartAndMid(Integer cart, String merId);

    /**
     * 添加购物项 商品个数=1
     * @param cart
     * @param id
     * @param calcPrice
     * @return
     */
    boolean addCartSelectedMer(Integer cart, Integer id, BigDecimal calcPrice);

    /**
     * 删除商品项
     * @param id
     * @return
     */
    boolean deleteCartSelectedMerById(String id, int cart);

    /**
     * 清空购物车
     * @param cart
     * @return
     */
    boolean clearCartSelectedMerByCart(Integer cart);

    /**
     * 修改商品数量
     * @param id
     * @return
     */
    boolean updateCartSelectedMerNumById(String id, int num, int cart);

    /**
     * 提交购物车
     * @param id
     * @param member
     * @return
     */
    OrdersVO submitShoppingCart(Integer id, Integer member);
}
