package com.neau.crm.web.service;

import com.neau.crm.web.domain.Activity;
import com.neau.crm.web.domain.vo.PageInfo;

import java.util.Map;

public interface ActivityService {
    public boolean save(Activity ac);
    public PageInfo<Activity> pageList(Map<String,Object> parameters);
    public boolean deleteByIds(String[] ids,String optUsrId);
}
