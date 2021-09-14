package domain.dto;

import domain.OrdersPO;

import java.math.BigDecimal;

public class OrdersVO extends OrdersPO {
    private BigDecimal totalMoney;

    public OrdersVO() {
    }

    public OrdersVO(Integer id, Integer member, Integer cart, String orderNO, String orderDate, Integer orderStatus, BigDecimal totalMoney) {
        super(id, member, cart, orderNO, orderDate, orderStatus);
        this.totalMoney = totalMoney;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }
}
