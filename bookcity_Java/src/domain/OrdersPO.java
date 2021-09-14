package domain;

public class OrdersPO {
    private Integer id;
    private Integer member;
    private Integer cart;
    private String orderNO;
    private String orderDate;
    private Integer orderStatus;
    //1-未付款
    //2-已付款 未发货
    //3-已发货 未签收
    //4-已签收 未评价
    //5-已评价

    public OrdersPO() {
    }

    public OrdersPO(Integer id, Integer member, Integer cart, String orderNO, String orderDate, Integer orderStatus) {
        this.id = id;
        this.member = member;
        this.cart = cart;
        this.orderNO = orderNO;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
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

    public Integer getCart() {
        return cart;
    }

    public void setCart(Integer cart) {
        this.cart = cart;
    }

    public String getOrderNO() {
        return orderNO;
    }

    public void setOrderNO(String orderNO) {
        this.orderNO = orderNO;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }
}
