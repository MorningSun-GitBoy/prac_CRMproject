package com.neau.crm.web.service;

import com.neau.crm.exceptions.LoginException;
import com.neau.crm.web.domain.SysUser;

public interface UserService {
    public SysUser login (String loginAct,String loginPwd,String ip) throws LoginException;
}
