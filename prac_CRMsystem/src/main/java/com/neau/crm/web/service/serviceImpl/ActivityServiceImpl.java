package com.neau.crm.web.service.serviceImpl;

import com.neau.crm.utils.ServerSessionUtils;
import com.neau.crm.web.dao.ActivityDao;
import com.neau.crm.web.dao.ActivityRemarkDao;
import com.neau.crm.web.domain.Activity;
import com.neau.crm.web.domain.ActivityRemark;
import com.neau.crm.web.domain.vo.PageInfo;
import com.neau.crm.web.service.ActivityService;

import java.util.List;
import java.util.Map;

public class ActivityServiceImpl implements ActivityService {

    @Override
    public boolean save(Activity ac) {
        return false;
    }

    @Override
    public PageInfo<Activity> pageList(Map<String, Object> parameters) {
        return null;
    }

    @Override
    public boolean deleteByIds(String[] ids, String optUsrId) {
        return false;
    }

    @Override
    public Activity getDetailById(String id) {
        return null;
    }

    @Override
    public List<ActivityRemark> getRemarkListByAid(String id) {
        return null;
    }

    @Override
    public boolean updateActivity(Activity activity) {
        return false;
    }

    @Override
    public boolean saveActivityRemark(ActivityRemark activityRemark) {
        return false;
    }

    @Override
    public boolean updateActivityRemark(ActivityRemark activityRemark) {
        return false;
    }

    @Override
    public boolean deleteRemarkById(String id,String optUser) {
        return false;
    }
}
