package domain;

import java.math.BigDecimal;

public class CartSelectedMerPO {
    private Integer id;
    private Integer cart;
    private Integer merchandise;
    private Integer number;
    private BigDecimal price;
    private BigDecimal money;

    public CartSelectedMerPO(Integer id, Integer cart, Integer merchandise, Integer number, BigDecimal price, BigDecimal money) {
        this.id = id;
        this.cart = cart;
        this.merchandise = merchandise;
        this.number = number;
        this.price = price;
        this.money = money;
    }

    public CartSelectedMerPO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCart() {
        return cart;
    }

    public void setCart(Integer cart) {
        this.cart = cart;
    }

    public Integer getMerchandise() {
        return merchandise;
    }

    public void setMerchandise(Integer merchandise) {
        this.merchandise = merchandise;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
