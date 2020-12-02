package com.neau.crm.web.service.serviceImpl;

import com.neau.crm.exceptions.LoginException;
import com.neau.crm.web.dao.UserDao;
import com.neau.crm.web.domain.SysUser;
import com.neau.crm.web.service.UserService;

import java.util.List;

public class UserSearchService implements UserService {
    private UserDao userDao;
    @Override
    public SysUser login(String loginAct, String loginPwd, String ip) throws LoginException {
        return null;
    }

    @Override
    public List<SysUser> selectAllUser() {
        return userDao.selectAllUser();
    }
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
