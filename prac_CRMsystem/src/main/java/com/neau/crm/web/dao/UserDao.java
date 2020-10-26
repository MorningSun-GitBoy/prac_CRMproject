package com.neau.crm.web.dao;

import com.neau.crm.web.domain.SysUser;

import java.util.Map;

public interface UserDao {
    SysUser login(Map<String,String> map);
}
