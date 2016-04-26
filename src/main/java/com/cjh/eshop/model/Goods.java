package com.cjh.eshop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品
 * 
 * @author 陈建杭
 */
public class Goods implements Serializable {

	private static final long serialVersionUID = -8866298078416026489L;

	private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private Shop shop;
    private GoodsCategory category;
    private Integer clickCount; // 商品浏览总数
    private Integer number; // 商品库存量
    private String image; // 商品图片，优化用的列
    private Integer isOnSale; // 是否上架
    private Integer isFreePostage; // 是否免邮
    private Brand brand; // 商品品牌
    private Date createTime;
    private Date modifyTime;
    private Integer collectionCount;
    
    public Goods() {}
    
    public Goods(String id) {
    	this.id = id;
    }
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public GoodsCategory getCategory() {
		return category;
	}

	public void setCategory(GoodsCategory category) {
		this.category = category;
	}

	public Integer getClickCount() {
		return clickCount;
	}

	public void setClickCount(Integer clickCount) {
		this.clickCount = clickCount;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getIsOnSale() {
		return isOnSale;
	}

	public void setIsOnSale(Integer isOnSale) {
		this.isOnSale = isOnSale;
	}

	public Integer getIsFreePostage() {
		return isFreePostage;
	}

	public void setIsFreePostage(Integer isFreePostage) {
		this.isFreePostage = isFreePostage;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Integer getCollectionCount() {
		return collectionCount;
	}

	public void setCollectionCount(Integer collectionCount) {
		this.collectionCount = collectionCount;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}