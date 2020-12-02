package com.neau.crm.web.service.serviceImpl;

import com.neau.crm.web.dao.ActivityDao;
import com.neau.crm.web.dao.ActivityRemarkDao;
import com.neau.crm.web.domain.Activity;
import com.neau.crm.web.domain.ActivityRemark;
import com.neau.crm.web.domain.vo.PageInfo;
import com.neau.crm.web.service.ActivityService;

import java.util.List;
import java.util.Map;

public class ActivitySearchService extends ActivityServiceImpl {
    private ActivityDao activityDao;
    private ActivityRemarkDao activityRemarkDao;

    @Override
    public Activity getDetailById(String id) {
        String[] ids = {id};
        List<Activity> ac = activityDao.selectByIds(ids);
        return ac.get(0);
    }

    @Override
    public List<ActivityRemark> getRemarkListByAid(String id) {
        return activityRemarkDao.selectByAid(id);
    }
    public void setActivityDao(ActivityDao activityDao) {
        this.activityDao = activityDao;
    }
    public void setActivityRemarkDao(ActivityRemarkDao activityRemarkDao) {
        this.activityRemarkDao = activityRemarkDao;
    }
}
