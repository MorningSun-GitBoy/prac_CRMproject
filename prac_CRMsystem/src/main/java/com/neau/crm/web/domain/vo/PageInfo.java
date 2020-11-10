package com.neau.crm.web.domain.vo;

import java.util.List;

public class PageInfo<T> {
    private Integer total;
    private List<T> entityList;
    public PageInfo(){

    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<T> entityList) {
        this.entityList = entityList;
    }
}
