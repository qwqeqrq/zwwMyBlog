package com.core.console.uitl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: ZHANGWEI
 * @Date: 2018/7/21
 * @Description:
 */
public class PageInfo implements Serializable {

    /**
     * 分页信息。
     * <p>
     * Page index从1开始递增，第1页的page index为1，第2页的page index为2，以此类推第n页的page index为n。
     * @Filename: PagerInfo.java
     * @Version: 1.0
     * @Author: 张维维
     * @Email:
     */
        /**
         * 创建分页信息对象。
         * @param pageSize 每页记录数。必须大于0。
         * @param pageIndex 第几页。Page index从1开始递增，第1页的page index为1，第2页的page index为2，以此类推第n页的page index为n。
         */
        private int num; // 当前页号, 采用自然数计数 1,2,3,...
    private int size; // 页面大小:一个页面显示多少个数据

    // 需要从数据库中查找出
    private long rowCount;// 数据总数：一共有多少个数据
    private List<?> list;

    // 可以根据上面属性：num,size,rowCount计算出
    private int pageCount; // 页面总数
    private int startRow;// 当前页面开始行, 第一行是0行
    private int first = 1;// 第一页 页号
    private int last;// 最后页 页号
    private int next;// 下一页 页号
    private int prev;// 前页 页号
    private int start;// 页号式导航, 起始页号
    private int end;// 页号式导航, 结束页号
    private int numCount = 4;// 页号式导航, 最多显示页号数量为numCount+1;这里显示5页。
    public List<String> showPages = new ArrayList<String>(); //要显示的页号
    private Object queryClass;//保存查询条件
    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public long getRowCount() {
        return rowCount;
    }
    public void setRowCount(long rowCount) {
        this.rowCount = rowCount;
    }
    public List<?> getList() {
        return list;
    }
    public void setList(List<?> list) {
        this.list = list;
    }
    public int getPageCount() {
        return pageCount;
    }
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
    public int getStartRow() {
        return startRow;
    }
    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }
    public int getFirst() {
        return first;
    }
    public void setFirst(int first) {
        this.first = first;
    }
    public int getLast() {
        return last;
    }
    public void setLast(int last) {
        this.last = last;
    }
    public int getNext() {
        return next;
    }
    public void setNext(int next) {
        this.next = next;
    }
    public int getPrev() {
        return prev;
    }
    public void setPrev(int prev) {
        this.prev = prev;
    }
    public int getStart() {
        return start;
    }
    public void setStart(int start) {
        this.start = start;
    }
    public int getEnd() {
        return end;
    }
    public void setEnd(int end) {
        this.end = end;
    }
    public int getNumCount() {
        return numCount;
    }
    public void setNumCount(int numCount) {
        this.numCount = numCount;
    }
    public List<String> getShowPages() {
        return showPages;
    }
    public void setShowPages(List<String> showPages) {
        this.showPages = showPages;
    }
    public Object getQueryClass() {
        return queryClass;
    }
    public void setQueryClass(Object queryClass) {
        this.queryClass = queryClass;
    }
    public void returnPageCount( long rowCount ,int size ){//计算页面总数
        if(rowCount%size!=0)
            this.pageCount = ((int)rowCount/size)+1;
        else
            this.pageCount = (int) rowCount/size;
    }
    public void returnStartRow (){//返回当前页面开始行，第一行是0行
        this.startRow=(num-1)*size;
    }
    /*public int lastPage(){ //最后一页 页码号
         if((int)rowCount%size==0){
             return (int) rowCount/size;
         }
         else
             return ((int)rowCount/size)+1;
    }*/
    public int nextPage (){//下一页的页号
        return num+1;
    }
    public int prePage (){//前一页的页号
        return num-1;
    }


}
