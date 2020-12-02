package com.neau.crm.web.dao;

import com.neau.crm.web.domain.Activity;
import com.neau.crm.web.domain.vo.PageInfo;

import java.util.List;
import java.util.Map;

public interface ActivityDao {
    public int save(Activity activity);
    public int getTotal(Map<String,Object> conditions);
    public List<Activity> pageList(Map<String,Object> conditions);
    public List<Activity> selectByIds(String[] ids);
}
