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
        Object[] keyArray = idMap.keySet().toArray();
        List<String> idList = new ArrayList<String>();
        for(Object key:keyArray){
            if(idMap.get(key)!=null){
                if(!idList.contains(idMap.get(key)))
                    idList.add(idMap.get(key));
            }
        }
        //System.out.println(idList);
        List<Map<String,String>> nameList = userDao.selectNameByIds(idList.toArray());
        for(Map<String,String> nameMap:nameList){
            //System.out.println(nameMap);
            idNameMap.put(nameMap.get("id"),nameMap.get("uname"));
        }
        //System.out.println(idNameMap);
        for(Object key:keyArray){
            idMap.put((String)key,idNameMap.get(idMap.get(key)));
            //将idMap中的id拿出，从idNameMap中寻找name，并按原键值对赋值
        }
        return idMap;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
