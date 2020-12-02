package com.neau.crm.web.service.serviceImpl;

import com.neau.crm.exceptions.LoginException;
import com.neau.crm.web.domain.SysUser;
import com.neau.crm.web.service.UserService;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {


    @Override
    public SysUser login(String loginAct, String loginPwd, String ip) throws LoginException {
        return null;
    }

    @Override
    public List<SysUser> selectAllUser() {
        return null;
    }

    @Override
    public Map<String, String> getNameMap(Map<String, String> idMap) {
        return null;
    }
}
