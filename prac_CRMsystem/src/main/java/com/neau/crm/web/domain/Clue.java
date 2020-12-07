package com.neau.crm.web.domain;

public class Clue {
    private String id;	            //主键
    private String clueName;	    //线索名
    private String callableName;	//称呼
    private String clueOwner;	    //所有者
    private String company;	        //公司名称
    private String job;	            //职业
    private String email;	        //邮箱
    private String phone;	        //公司电话
    private String website;	        //公司网站
    private String clueState;	    //状态
    private String source;	        //来源
    private String createBy;	    //创建人
    private String createTime;	    //创建时间
    private String editBy;	        //修改人
    private String editTime;	    //修改时间
    private String descriptions; 	//描述
    private String contactSummary;	//联系纪要
    private String nextContactTime;	//下次联系时间
    private String clueAddress;	    //地址

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClueName() {
        return clueName;
    }

    public void setClueName(String clueName) {
        this.clueName = clueName;
    }

    public String getCallableName() {
        return callableName;
    }

    public void setCallableName(String callableName) {
        this.callableName = callableName;
    }

    public String getClueOwner() {
        return clueOwner;
    }

    public void setClueOwner(String clueOwner) {
        this.clueOwner = clueOwner;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getClueState() {
        return clueState;
    }

    public void setClueState(String clueState) {
        this.clueState = clueState;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getEditBy() {
        return editBy;
    }

    public void setEditBy(String editBy) {
        this.editBy = editBy;
    }

    public String getEditTime() {
        return editTime;
    }

    public void setEditTime(String editTime) {
        this.editTime = editTime;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getContactSummary() {
        return contactSummary;
    }

    public void setContactSummary(String contactSummary) {
        this.contactSummary = contactSummary;
    }

    public String getNextContactTime() {
        return nextContactTime;
    }

    public void setNextContactTime(String nextContactTime) {
        this.nextContactTime = nextContactTime;
    }

    public String getClueAddress() {
        return clueAddress;
    }

    public void setClueAddress(String clueAddress) {
        this.clueAddress = clueAddress;
    }
}
