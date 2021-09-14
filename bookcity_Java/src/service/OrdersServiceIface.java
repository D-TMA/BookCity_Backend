package service;

import domain.dto.OrdersVO;

public interface OrdersServiceIface {
    /**
     * 根据订单编号查询订单信息
     * @param orderNO
     * @return
     */
    OrdersVO getOrderByOrderNO(String orderNO);
}
