package com.neau.crm.web.service.serviceImpl;

import com.neau.crm.exceptions.LoginException;
import com.neau.crm.utils.DateTimeUtils;
import com.neau.crm.web.dao.DeleteDao;
import com.neau.crm.web.dao.UserDao;
import com.neau.crm.web.domain.SysUser;
import com.neau.crm.web.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserManageService  extends UserServiceImpl {
    private UserDao userDao;
    private DeleteDao deleteDao;
    @Override
    public SysUser login(String loginAct, String loginPwd, String ip) throws LoginException {
        Map<String,String> logMap = new HashMap<String,String>();
        logMap.put("loginAct",loginAct);
        logMap.put("loginPwd",loginPwd);
        SysUser user = userDao.login(logMap);
        if(user==null){//数据库中没有该用户名和密码的记录
            throw new LoginException("用户名或密码错误");
        }else if(user.getExpireTime().compareTo(DateTimeUtils.getSysTime())<=0){//查看此时是否超过失效时间
            throw new LoginException("用户已失效，请联系管理员");
        }else if(user.getLockState().equals("0")){//查看锁定位有没有问题
            throw new LoginException("该账户已被锁定，请联系管理员");
        }else if(!user.getAllowIps().contains(ip)){//查看登录IP是否符合要求
            throw new LoginException("IP非法，请在合理的位置登录系统");
        }
        return user;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    public void setDeleteDao(DeleteDao deleteDao) {
        this.deleteDao = deleteDao;
    }
}
