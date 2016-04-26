package com.cjh.eshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjh.eshop.common.PageInfo;
import com.cjh.eshop.dao.IArticleDao;
import com.cjh.eshop.dao.common.IBaseDao;
import com.cjh.eshop.model.Article;
import com.cjh.eshop.service.IArticleService;
import com.cjh.eshop.service.common.BaseService;

@Service
@Transactional(rollbackFor = { Exception.class })
public class ArticleService extends BaseService<Article, String> implements IArticleService {
	
	@Resource(name = "articleDao")
	private IArticleDao dao;

	@Override
	protected IBaseDao<Article, String> getDao() {
		return dao;
	}

	@Transactional(rollbackFor = Exception.class, readOnly = true)
	@Override
	public PageInfo<Article> getAllByKeyword(String keyword, int pageNo, int pageSize) {
		PageInfo<Article> pageInfo = dao.getAllByKeyword(keyword, pageNo, pageSize);
		System.out.println(pageInfo.getTotalSize()+"哦");
		List<Article> articles = pageInfo.getResult();
		for (Article article : articles) {
			String content = article.getContent();
			// 去掉里面的html标签
			Document document = Jsoup.parse(content);
			content = document.text();
			final int MAX_TEXT_LENGTH = 300; // 文章内容最多显示300个字符
			if (content.length() > MAX_TEXT_LENGTH) {
				content = content.substring(0, MAX_TEXT_LENGTH);
			}
			
			article.setContent(content);
		}

		return pageInfo;
	}

	@Override
	public List<Article> getAll(int page, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Article> getAll() {
		return dao.getAllArticle(1, 20); // 不可能超过20
	}

	@Override
	public PageInfo<Article> getAllByCategoryId(String categoryId, int pageNo,
			int pageSize) {
		return dao.getAllByCategoryId(categoryId, pageNo, pageSize);
	}

}
