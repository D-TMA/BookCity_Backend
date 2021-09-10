package domain;

public class MerchandisePO {
    private Integer id;
    private Integer category;
    private String merName;
    private Double price;
    private Double sprice;
    private String merModel;
    private String picture;
    private String merDesc;
    private String manufacturer;
    private String leaveFactoryDate;
    private Integer special;

    public MerchandisePO() {
    }

    public MerchandisePO(Integer id, Integer category, String merName, Double price, Double sprice, String merModel, String picture, String merDesc, String manufacturer, String leaveFactoryDate, Integer special) {
        this.id = id;
        this.category = category;
        this.merName = merName;
        this.price = price;
        this.sprice = sprice;
        this.merModel = merModel;
        this.picture = picture;
        this.merDesc = merDesc;
        this.manufacturer = manufacturer;
        this.leaveFactoryDate = leaveFactoryDate;
        this.special = special;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getMerName() {
        return merName;
    }

    public void setMerName(String merName) {
        this.merName = merName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSprice() {
        return sprice;
    }

    public void setSprice(Double sprice) {
        this.sprice = sprice;
    }

    public String getMerModel() {
        return merModel;
    }

    public void setMerModel(String merModel) {
        this.merModel = merModel;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getMerDesc() {
        return merDesc;
    }

    public void setMerDesc(String merDesc) {
        this.merDesc = merDesc;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getLeaveFactoryDate() {
        return leaveFactoryDate;
    }

    public void setLeaveFactoryDate(String leaveFactoryDate) {
        this.leaveFactoryDate = leaveFactoryDate;
    }

    public Integer getSpecial() {
        return special;
    }

    public void setSpecial(Integer special) {
        this.special = special;
    }
}
