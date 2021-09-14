package service.impl;

import dao.OrdersDao;
import domain.dto.OrdersVO;
import service.OrdersServiceIface;

public class OrdersServiceImpl implements OrdersServiceIface {
    OrdersDao ordersDao = new OrdersDao();
    @Override
    public OrdersVO getOrderByOrderNO(String orderNO) {
        return ordersDao.selectOrdersByOrderNO(orderNO);
    }
}
