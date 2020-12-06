package com.neau.crm.web.service.serviceImpl;

import com.neau.crm.web.dao.ActivityDao;
import com.neau.crm.web.dao.ActivityRemarkDao;
import com.neau.crm.web.dao.DeleteDao;
import com.neau.crm.web.domain.Activity;
import com.neau.crm.web.domain.ActivityRemark;
import com.neau.crm.web.domain.vo.PageInfo;
import com.neau.crm.web.service.ActivityService;

import java.util.List;
import java.util.Map;

public class ActivityManageService extends ActivityServiceImpl {
    private ActivityDao activityDao;
    private ActivityRemarkDao activityRemarkDao;
    private DeleteDao deleteDao;
    @Override
    public boolean save(Activity ac) {
        int count = activityDao.save(ac);
        //System.out.println(count);
        //System.out.println(count==1);
        return count == 1;
    }

    @Override
    public PageInfo<Activity> pageList(Map<String, Object> parameters) {
        //取得total
        int total = activityDao.getTotal(parameters);
        //取得activityList
        List<Activity> activityList = activityDao.pageList(parameters);
        //封装vo
        PageInfo<Activity> pageInfo = new PageInfo<Activity>();
        pageInfo.setTotal(total);
        pageInfo.setEntityList(activityList);
        return pageInfo;
    }

    @Override
    public boolean deleteByIds(String[] ids, String optUsrId) {
        //方法重写
        return false;
    }

    @Override
    public boolean updateActivity(Activity activity) {
        int count = activityDao.update(activity);
        return count == 1;
    }
    public void setActivityDao(ActivityDao activityDao) {
        this.activityDao = activityDao;
    }
    public void setActivityRemarkDao(ActivityRemarkDao activityRemarkDao) {
        this.activityRemarkDao = activityRemarkDao;
    }
    public void setDeleteDao(DeleteDao deleteDao) {
        this.deleteDao = deleteDao;
    }
}
