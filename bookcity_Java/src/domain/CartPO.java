package domain;

import java.math.BigDecimal;

public class CartPO {
    private Integer id;
    private Integer member;
    private BigDecimal money;
    private Integer cartStatus;

    public CartPO() {
    }

    public CartPO(Integer id, Integer member, BigDecimal money, Integer cartStatus) {
        this.id = id;
        this.member = member;
        this.money = money;
        this.cartStatus = cartStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMember() {
        return member;
    }

    public void setMember(Integer member) {
        this.member = member;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getCartStatus() {
        return cartStatus;
    }

    public void setCartStatus(Integer cartStatus) {
        this.cartStatus = cartStatus;
    }
}
