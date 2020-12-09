package com.neau.crm.web.service.serviceImpl;

import com.neau.crm.utils.DateTimeUtils;
import com.neau.crm.utils.UUIDUtils;
import com.neau.crm.web.dao.ActivityDao;
import com.neau.crm.web.dao.ActivityRemarkDao;
import com.neau.crm.web.dao.DeleteDao;
import com.neau.crm.web.domain.Activity;
import com.neau.crm.web.domain.ActivityRemark;
import com.neau.crm.web.domain.fordelet.DelContainer;
import com.neau.crm.web.domain.vo.PageInfo;
import com.neau.crm.web.service.ActivityService;

import java.util.*;

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
        int acNum = ids.length;
        String acrIds[] = activityRemarkDao.selectIdByAid(ids);
        int acrNum = acrIds.length;
        //数据备份
        String currentTime = DateTimeUtils.getSysTime();
        List<Activity> activities = activityDao.selectByIds(ids);
        List<DelContainer<Activity>> delActivities = new ArrayList<DelContainer<Activity>>();
        for(Activity a : activities){
            delActivities.add(new DelContainer<Activity>(UUIDUtils.getUUID(), currentTime,a,optUsrId));
        }
        int inserAcNum = deleteDao.insertActivities(delActivities);
        int inserAcReNum = 0;
        if(acrNum != 0) {
            List<ActivityRemark> actRemarks = activityRemarkDao.selectByIds(acrIds);
            List<DelContainer<ActivityRemark>> delActRemarks = new ArrayList<DelContainer<ActivityRemark>>();
            for (ActivityRemark ar : actRemarks) {
                delActRemarks.add(new DelContainer<ActivityRemark>(UUIDUtils.getUUID(), currentTime, ar, optUsrId));
            }
            inserAcReNum = deleteDao.insertActivityRemarks(delActRemarks);
        }
        //数据删除
        if(acNum != inserAcNum && acrNum != inserAcReNum)
            return false;
        deleteDao.deleteActivityByIds(ids);
        deleteDao.deleteActivityRemarkByIds(acrIds);
        return true;
    }

    @Override
    public boolean saveActivityRemark(ActivityRemark activityRemark) {
        int count = activityRemarkDao.save(activityRemark);
        return 1==count;
    }

    @Override
    public boolean updateActivityRemark(ActivityRemark activityRemark) {
        int count = activityRemarkDao.update(activityRemark);
        return 1==count;
    }

    @Override
    public boolean deleteRemarkById(String id,String optUser) {
        //将id封装进数组
        String ids[] = {id};
        //备份数据
        List<DelContainer<ActivityRemark>> delActvityRemark = new ArrayList<DelContainer<ActivityRemark>>();
        List<ActivityRemark> activityRemark = activityRemarkDao.selectByIds(ids);
        for(ActivityRemark acr:activityRemark)
            delActvityRemark.add(new DelContainer<>(UUIDUtils.getUUID(),DateTimeUtils.getSysTime(),acr,optUser));
        if(1==deleteDao.insertActivityRemarks(delActvityRemark))
            return 1==deleteDao.deleteActivityRemarkByIds(ids);
        else return false;
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
