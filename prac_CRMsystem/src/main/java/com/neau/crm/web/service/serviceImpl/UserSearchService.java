package com.neau.crm.web.service.serviceImpl;

import com.neau.crm.exceptions.LoginException;
import com.neau.crm.web.dao.UserDao;
import com.neau.crm.web.domain.SysUser;
import com.neau.crm.web.service.UserService;

import java.util.*;

public class UserSearchService extends UserServiceImpl {
    private UserDao userDao;

    @Override
    public List<SysUser> selectAllUser() {
        return userDao.selectAllUser();
    }

    @Override
    public Map<String, String> getNameMap(Map<String, String> idMap) {
        Map<String,String> idNameMap = new HashMap<String,String>();
        String[] keyArray = (String[]) idMap.keySet().toArray();
        List<String> idList = new ArrayList<String>();
        for(String key:keyArray){
            if(idMap.get(key)!=null){
                idList.add(idMap.get(key));
            }
        }
        List<Map<String,String>> nameList = userDao.selectNameByIds((String[]) idList.toArray());
        int i = 0;
        for(Map<String,String> nameMap:nameList){
            idNameMap.putAll(nameMap);
        }
        for(String key:keyArray){
            idMap.put(key,idNameMap.get(idMap.get(key)));
            //将idMap中的id拿出，从idNameMap中寻找name，并按原键值对赋值
        }
        return idMap;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
