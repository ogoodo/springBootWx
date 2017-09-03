package com.ogoodo.wx.dao.test.utils;

import java.io.Serializable;
import java.util.List;

import com.github.pagehelper.Page;

// 参考: http://blog.csdn.net/y534560449/article/details/53491289
public class PageBean<T> implements Serializable {

	private static final long serialVersionUID = 8656597559014685636L;
    private List<T> list;    //结果集
    private PageEx page;

	/**
     * 包装Page对象，因为直接返回Page对象，在JSON处理以及其他情况下会被当成List来处理，
     * 而出现一些问题。
     * @param list          page结果
     * @param navigatePages 页码数量
     */
    public PageBean(List<T> list) {
        if (list instanceof Page) {
            Page<T> page = (Page<T>) list;
            this.list = page;
            this.page = new PageEx();
            this.page.setPageNum(page.getPageNum());
            this.page.setPageSize(page.getPageSize());
            this.page.setTotal(page.getTotal());
            this.page.setPages(page.getPages());
            this.page.setSize(page.size());
        }
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public PageEx getPage() {
		return page;
	}

	public void setPage(PageEx pageEx) {
		this.page = pageEx;
	}

}
