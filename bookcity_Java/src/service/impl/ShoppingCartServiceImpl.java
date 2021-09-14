package service.impl;

import dao.ShoppingCartDao;
import domain.dto.CartSelectedMerVO;
import domain.dto.CartVO;
import domain.dto.MerchandiseVO;
import domain.dto.OrdersVO;
import service.MerchandiseServiceIface;
import service.OrdersServiceIface;
import service.ShoppingCartServiceIface;
import utils.DataFormatUtil;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ShoppingCartServiceImpl implements ShoppingCartServiceIface {
    ShoppingCartDao shoppingCartDao = new ShoppingCartDao();
    MerchandiseServiceIface merchandiseService = new MerchandiseServiceImpl();
    OrdersServiceIface ordersService = new OrdersServiceImpl();
    //ShoppingCartServiceIface shoppingCartService = new ShoppingCartServiceImpl(); 自己new自己造成StackOverflow

    @Override
    public CartVO getShoppingCart(Integer member) {
        //是否存在购物车
        CartVO cartVO = shoppingCartDao.selectCartByMember(member);
        if (cartVO==null) {
            //创建购物车
            shoppingCartDao.insert(member);
            cartVO = shoppingCartDao.selectCartByMember(member);
        }
        return cartVO;
    }

    @Override
    public List<CartSelectedMerVO> getCartSelectedMerListByCart(Integer cart) {
        return shoppingCartDao.selectCartSelectedMerListByCart(cart);
    }

    @Override
    public boolean addShoppingCart(Integer cart, String merId, Integer favourable) {
        //获得商品信息
        MerchandiseVO merchandiseVO = merchandiseService.getMerchandiseListById(merId);
        boolean b = false;
        //去实现
        //该商品在购物车中是否存在
        CartSelectedMerVO cartSelectedMerVO = getCartSelectedMerListByCartAndMid(cart,merId);
        //商品不存在再进行插入
        if (cartSelectedMerVO==null) {
            // insert
            //商品是否特价 特价和会员价比较 存入最小值
            BigDecimal calcPrice = null;
            //打折后
            BigDecimal favourablePrice = merchandiseVO.getPrice().multiply(new BigDecimal(String.valueOf(favourable)));
            favourablePrice = favourablePrice.multiply(new BigDecimal("0.01"));
            if (merchandiseVO.getSpecial()==1) {
                //特价
                BigDecimal specialPrice = merchandiseVO.getSprice();
                //比较BigDecimal
                if (specialPrice.compareTo(favourablePrice)==1) {
                    System.out.println("特价大于优惠价");
                    calcPrice = favourablePrice;
                } else {
                    calcPrice = specialPrice;
                }
            } else {
                //商品是新品则按照会员价
                calcPrice = favourablePrice;
            }
            //插入数据库
            b = addCartSelectedMer(cart, merchandiseVO.getId(), calcPrice);
        } else {
            // update
            b = updateCartSelectedMerNum(cartSelectedMerVO.getId(), cart);    //更新购物项
        }

        return b;
    }

    @Override
    public boolean updateCartSelectedMerNum(Integer id, Integer cart) {
        //更新商品项

        //更新购物车总金额
        return shoppingCartDao.updateCartSelectedMerNum(id, cart);
    }

    @Override
    public CartSelectedMerVO getCartSelectedMerListByCartAndMid(Integer cart, String merId) {
        //查询
        int merchandise = Integer.parseInt(merId);
        return shoppingCartDao.selectCartSelectedMerByCartAndMid(cart, merchandise);
    }

    @Override
    public boolean addCartSelectedMer(Integer cart, Integer merchandise, BigDecimal calcPrice) {
        return shoppingCartDao.addCartSelectedMer(cart, merchandise, calcPrice);
    }

    @Override
    public boolean deleteCartSelectedMerById(String id, int cart) {
        int int_id = Integer.parseInt(id);
        return shoppingCartDao.deleteCartSelectedMerById(int_id, cart);
    }

    @Override
    public boolean clearCartSelectedMerByCart(Integer cart) {
        return shoppingCartDao.deleteCartSelectedMerByCart(cart);
    }

    @Override
    public boolean updateCartSelectedMerNumById(String id, int num, int cart) {
        int int_id = Integer.parseInt(id);
        return shoppingCartDao.updateCartSelectedMerNumById(int_id, num, cart);
    }

    @Override
    public OrdersVO submitShoppingCart(Integer cart, Integer member) {
        String orderNO = DataFormatUtil.getOrderNoByUUID("");
        String orderDate = DataFormatUtil.formatDateString(new Date(), "yyyy-MM-dd HH:mm:ss");
        boolean b = shoppingCartDao.submitShoppingCart(cart, member, orderNO, orderDate);
        if (b)
            return ordersService.getOrderByOrderNO(orderNO);
        return null;
    }
}
