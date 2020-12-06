package com.neau.crm.web.service;

import com.neau.crm.web.domain.Activity;
import com.neau.crm.web.domain.ActivityRemark;
import com.neau.crm.web.domain.vo.PageInfo;

import java.util.List;
import java.util.Map;

public interface ActivityService {
    public boolean save(Activity ac);
    public PageInfo<Activity> pageList(Map<String,Object> parameters);
    public boolean deleteByIds(String[] ids,String optUsrId);
    public Activity getDetailById(String id);
    public List<ActivityRemark> getRemarkListByAid(String id);
    public boolean updateActivity(Activity activity);
}
