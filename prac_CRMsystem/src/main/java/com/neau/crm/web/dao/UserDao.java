package com.neau.crm.web.dao;

import com.neau.crm.web.domain.SysUser;

import java.util.List;
import java.util.Map;

public interface UserDao {
    SysUser login(Map<String,String> map);
    List<SysUser> selectAllUser();
    List<Map<String,String>> selectNameByIds(Object[] ids);
}
