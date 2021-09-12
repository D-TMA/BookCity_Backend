package domain.dto;

import domain.CartPO;

import java.math.BigDecimal;

public class CartVO extends CartPO {
    public CartVO(Integer id, Integer member, BigDecimal money, Integer cartStatus) {
        super(id, member, money, cartStatus);
    }
}
