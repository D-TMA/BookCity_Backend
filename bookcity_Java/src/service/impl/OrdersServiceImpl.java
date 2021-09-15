package service.impl;

import dao.OrdersDao;
import domain.dto.OrdersVO;
import service.OrdersServiceIface;

import java.util.List;

public class OrdersServiceImpl implements OrdersServiceIface {
    OrdersDao ordersDao = new OrdersDao();
    @Override
    public OrdersVO getOrderByOrderNO(String orderNO) {
        return ordersDao.selectOrdersByOrderNO(orderNO);
    }

    @Override
    public List<OrdersVO> getOrderListByMid(Integer id) {
        return ordersDao.selectOrdersListByMid(id);
    }

    @Override
    public boolean deleteOrdersById(String id) {
        int int_id = Integer.parseInt(id);
        return ordersDao.deleteOrdersById(int_id);
    }

    @Override
    public OrdersVO getOrderById(String id) {
        int int_id = Integer.parseInt(id);
        return ordersDao.selectOrdersById(int_id);
    }
}
