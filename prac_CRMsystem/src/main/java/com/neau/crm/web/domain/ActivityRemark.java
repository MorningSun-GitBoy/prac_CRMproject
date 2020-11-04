package com.neau.crm.web.domain;

public class ActivityRemark {
    private String id;              //主键
    private String noteContent;     //备注信息
    private String createTime;      //创建时间
    private String createBy;        //创建人
    private String editTime;        //修改时间
    private String editBy;          //修改人
    private String editFlage;       //修改标志
    private String activityId;      //活动号 外键

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getEditTime() {
        return editTime;
    }

    public void setEditTime(String editTime) {
        this.editTime = editTime;
    }

    public String getEditBy() {
        return editBy;
    }

    public void setEditBy(String editBy) {
        this.editBy = editBy;
    }

    public String getEditFlage() {
        return editFlage;
    }

    public void setEditFlage(String editFlage) {
        this.editFlage = editFlage;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }
}
