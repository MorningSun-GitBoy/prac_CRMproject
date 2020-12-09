package com.neau.crm.web.dao;

import com.neau.crm.web.domain.ActivityRemark;

import java.util.List;
import java.util.Map;

public interface ActivityRemarkDao {
    public String[] selectIdByAid(String[] ids);
    public List<ActivityRemark> selectByIds(String[] ids);
    public List<ActivityRemark> selectByAid(String id);
    public int save(ActivityRemark activityRemark);
    public int update(ActivityRemark activityRemark);
}
