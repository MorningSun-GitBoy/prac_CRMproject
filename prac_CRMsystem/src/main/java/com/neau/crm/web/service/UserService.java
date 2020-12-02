package com.neau.crm.web.service;

import com.neau.crm.exceptions.LoginException;
import com.neau.crm.web.domain.SysUser;

import java.util.List;
import java.util.Map;

public interface UserService {
    public SysUser login (String loginAct,String loginPwd,String ip) throws LoginException;
    public List<SysUser> selectAllUser();
    public Map<String,String> getNameMap(Map<String,String> idMap);
}
