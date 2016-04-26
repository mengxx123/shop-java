package com.cjh.eshop.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品评价
 * @author 陈建杭
 */
public class GoodsComment implements Serializable {

	private static final long serialVersionUID = -230230119633479713L;

	private String id;

    private User user;

    private Goods goods;

    private String content;

    private Date commentTime;

    private Integer score; // 评分，取值1-5

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

}