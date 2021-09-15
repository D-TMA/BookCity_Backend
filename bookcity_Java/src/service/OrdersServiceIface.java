package service;

import domain.dto.OrdersVO;

import java.util.List;

public interface OrdersServiceIface {
    /**
     * 根据订单编号查询订单信息
     * @param orderNO
     * @return
     */
    OrdersVO getOrderByOrderNO(String orderNO);

    /**
     * 根据会员ID查询订单列表
     * @param id
     * @return
     */
    List<OrdersVO> getOrderListByMid(Integer id);

    /**
     * 通过订单ID删除订单
     * @param id
     * @return
     */
    boolean deleteOrdersById(String id);

    /**
     * 根据订单id查询订单
     * @param id
     * @return
     */
    OrdersVO getOrderById(String id);
}
