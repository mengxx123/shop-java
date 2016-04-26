package com.cjh.eshop.model;

import java.io.Serializable;

/**
 * 商品图片
 * @author 陈建杭
 */
public class GoodsImage implements Serializable {

	private static final long serialVersionUID = -5283580851938070553L;

	private String id;

    private String goodsId;

    private String url; // 图片存储路径

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	

}