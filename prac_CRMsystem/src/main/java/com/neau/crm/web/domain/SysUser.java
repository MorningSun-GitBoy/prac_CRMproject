package com.neau.crm.web.domain;

public class SysUser {
    private String id;          //编号，主键
    private String loginAct;    //登录账号
    private String uname;       //用户真实姓名
    private String loginPWD;    //登录密码
    private String email;       //邮箱
    private String expireTime;  //失效时间
    private String lockState;   //锁定状态:0锁定，1开启
    private String deptno;      //部门编号
    private String allowIps;   //允许访问IP地址
    private String createTime;  //创建时间
    private String createBy;    //创建人 System代表系统默认创建 其他用户创建，则是显示为对应的UID
    private String editTime;    //修改时间
    private String editBy;      //修改人 System代表系统更新时修改 其他用户修改，则是显示为对应的UID

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginAct() {
        return loginAct;
    }

    public void setLoginAct(String loginAct) {
        this.loginAct = loginAct;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getLoginPWD() {
        return loginPWD;
    }

    public void setLoginPWD(String loginPWD) {
        this.loginPWD = loginPWD;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public String getLockState() {
        return lockState;
    }

    public void setLockState(String lockState) {
        this.lockState = lockState;
    }

    public String getDeptno() {
        return deptno;
    }

    public void setDeptno(String deptno) {
        this.deptno = deptno;
    }

    public String getAllowIps() {
        return allowIps;
    }

    public void setAllowIps(String allowIps) {
        this.allowIps = allowIps;
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
}
