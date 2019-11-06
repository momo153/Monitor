package com.petrochina.e7.monitor.commons.core.domain;

import lombok.Data;

import java.util.List;
@Data
public class PageResult<T> {

    private static final long serialVersionUID = 7223334258815093683L;
    //当前页
    private Long currentPage;
    //当前页条数
    private Long pagesize;
    //数据内容
    private List<?> data;

    public PageResult (long recordsTotal, long recordsFiltered, List<?> data){
        super();
        this.currentPage = currentPage;
        this.pagesize = pagesize;
        this.data = data;
    }
    public PageResult (){
        super();
    }
}
