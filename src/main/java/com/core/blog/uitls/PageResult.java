package com.core.blog.uitls;

import java.io.Serializable;
import java.util.List;

/**
 * 包名：com.xxx.orders.util.
 * 描述详情：分页结果集对象
 * 创建者： yusq
 * 创建时间：2017/4/27 0027-13:46.
 * 版权：Copyright xxx. All Rights Reserved.
 */
public class PageResult<T> implements Serializable {

    public PageResult(){}

    /**当前页数*/
    private Integer currentPage;
    /**总数*/
    private Integer totals;
    /**总页数*/
    private Integer pages;
    /**每页显示条数*/
    private Integer pageSize;
    /**是否存在上一页*/
    private Boolean hasPre;
    /**是否存在下一页*/
    private Boolean hasNext;
    /**分页结果集*/
    private List<T> data;

    /**总数*/
    private Integer total;

    public PageResult(List<T> data, Integer count, Integer currentPage, Integer size) {
        this.data = data;
        this.totals = count;
        this.pageSize = size;
        //当前页
        this.currentPage = currentPage;
/*        if(getCurrentPage() == null || getCurrentPage() <= 0) {
            this.currentPage = 1;
        }*/
        //计算总页数
        int pages = count / size;
        if(count % size != 0) {
            this.pages = pages + 1;
        } else {
            this.pages = pages;
        }
        //是否存在上一页
        this.hasPre = getCurrentPage()>1&&getCurrentPage()<=getPages()+1;
        //是否存在下一页
        this.hasNext = getCurrentPage()>0&&getCurrentPage() < getPages();
    }

    public PageResult(List<T> data, Integer count) {
        this.data = data;
        this.total = count;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Boolean getHasPre() {
        return hasPre;
    }

    public void setHasPre(Boolean hasPre) {
        this.hasPre = hasPre;
    }

    public Boolean getHasNext() {
        return hasNext;
    }

    public void setHasNext(Boolean hasNext) {
        this.hasNext = hasNext;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Integer getTotals() {
        return totals;
    }

    public void setTotals(Integer totals) {
        this.totals = totals;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
