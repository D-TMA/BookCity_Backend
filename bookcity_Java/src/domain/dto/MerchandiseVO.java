package domain.dto;

import domain.MerchandisePO;

public class MerchandiseVO extends MerchandisePO {
    //扩展字段

    public MerchandiseVO() {
    }

    public MerchandiseVO(Integer id, Integer category, String merName, Double price, Double sprice, String merModel, String picture, String merDesc, String manufacturer, String leaveFactoryDate, Integer special) {
        super(id, category, merName, price, sprice, merModel, picture, merDesc, manufacturer, leaveFactoryDate, special);
    }
}
