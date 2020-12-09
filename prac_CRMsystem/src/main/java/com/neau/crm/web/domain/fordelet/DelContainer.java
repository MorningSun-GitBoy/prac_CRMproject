package com.neau.crm.web.domain.fordelet;

public class DelContainer<E> {
    private String id;
    private E entity;
    private String deletTime;
    private String deletBy;
    public DelContainer(){

    }
    public DelContainer(String id,String deletTime,E entity,String deletBy){
        this.id = id;
        this.deletTime = deletTime;
        this.entity = entity;
        this.deletBy = deletBy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public E getEntity() {
        return entity;
    }

    public void setEntity(E entity) {
        this.entity = entity;
    }

    public String getDeletTime() {
        return deletTime;
    }

    public void setDeletTime(String deletTime) {
        this.deletTime = deletTime;
    }

    public String getDeletBy() {
        return deletBy;
    }

    public void setDeletBy(String deletBy) {
        this.deletBy = deletBy;
    }
}
