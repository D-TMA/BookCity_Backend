package domain.dto;

import domain.CartSelectedMerPO;

import java.math.BigDecimal;

public class CartSelectedMerVO extends CartSelectedMerPO {
    //扩展字段
    String merName; //商品名称
    BigDecimal merPrice; //市场价

    public CartSelectedMerVO() {
    }

    public CartSelectedMerVO(Integer id, Integer cart, Integer merchandise, Integer number, BigDecimal price, BigDecimal money, String merName, BigDecimal merPrice) {
        super(id, cart, merchandise, number, price, money);
        this.merName = merName;
        this.merPrice = merPrice;
    }

    public String getMerName() {
        return merName;
    }

    public void setMerName(String merName) {
        this.merName = merName;
    }

    public BigDecimal getMerPrice() {
        return merPrice;
    }

    public void setMerPrice(BigDecimal merPrice) {
        this.merPrice = merPrice;
    }
}
