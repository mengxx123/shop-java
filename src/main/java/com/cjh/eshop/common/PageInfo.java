package com.cjh.eshop.common;

import java.io.Serializable;
import java.util.List;

import com.github.pagehelper.Page;

public class PageInfo<T> implements Serializable {
	
	// 改进
	
    private static final long serialVersionUID = 8656597559014685635L;
    
    private List<T> result; // 结果集
    private long totalSize; // 总记录数
    private int pageSize; // 每页记录数
    private int totalPage; // 总页数
    private int currentPage; // 当前页数
    private int currentPageSize; // 当前页的数量 <= pageSize，该属性来自ArrayList的size属性
    
    private int startPage;
    private int endPage;
    
    /**
     * 包装Page对象，因为直接返回Page对象，在JSON处理以及其他情况下会被当成List来处理，
     * 而出现一些问题。
     * @param list          page结果
     * @param navigatePages 页码数量
     */
    public PageInfo(List<T> list) {
        if (list instanceof Page) {
            Page<T> page = (Page<T>) list;
            currentPage = page.getPageNum();
            pageSize = page.getPageSize();
            totalSize = page.getTotal();
            totalPage = page.getPages();
            result = page;
            currentPageSize = page.size();
            startPage = currentPage - 3;
            if (startPage < 1) {
            	startPage = 1;
            }
            endPage = currentPage + 3;
            if (endPage > totalPage) {
            	endPage = totalPage;
            }
        }
    }

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public long getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(long totalSize) {
		this.totalSize = totalSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getCurrentPageSize() {
		return currentPageSize;
	}

	public void setCurrentPageSize(int currentPageSize) {
		this.currentPageSize = currentPageSize;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
}
