package com.neau.crm.web.service.serviceImpl;

import com.neau.crm.utils.DateTimeUtils;
import com.neau.crm.utils.ServerSessionUtils;
import com.neau.crm.utils.UUIDUtils;
import com.neau.crm.web.dao.ActivityDao;
import com.neau.crm.web.dao.ActivityDelDao;
import com.neau.crm.web.dao.ActivityRemarkDao;
import com.neau.crm.web.dao.ActivityRemarkDelDao;
import com.neau.crm.web.domain.Activity;
import com.neau.crm.web.domain.ActivityRemark;
import com.neau.crm.web.domain.fordelet.DelContainer;
import com.neau.crm.web.domain.vo.PageInfo;
import com.neau.crm.web.service.ActivityService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ActivityServiceImpl implements ActivityService {
    private ActivityDao activityDao = ServerSessionUtils.getSqlSession().getMapper(ActivityDao.class);
    private ActivityDelDao activityDelDao = ServerSessionUtils.getSqlSession().getMapper(ActivityDelDao.class);
    private ActivityRemarkDao activityRemarkDao = ServerSessionUtils.getSqlSession().getMapper(ActivityRemarkDao.class);
    private ActivityRemarkDelDao activityRemarkDelDao = ServerSessionUtils.getSqlSession().getMapper(ActivityRemarkDelDao.class);

    @Override
    public boolean save(Activity activity) {
        int count = activityDao.save(activity);
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
    public boolean deleteByIds(String[] ids,String optUsrId) {
        //查询出需要删除的备注
        String[] acRemIds = activityRemarkDao.selectId(ids);//备注id
        int remNum = acRemIds.length;//备注数目
        //删除备注
        //  拿到要删除的备注
        List<ActivityRemark> acRemarks = activityRemarkDao.selectByIds(acRemIds);
        //  插入到已删除的表中
        List<DelContainer<ActivityRemark>> delAcRemarks = new ArrayList<DelContainer<ActivityRemark>>();
        String currentTime = DateTimeUtils.getSysTime();
        for(ActivityRemark acRemark : acRemarks){
            delAcRemarks.add(new DelContainer<ActivityRemark>(UUIDUtils.getUUID(),currentTime,optUsrId));
        }
        int insRemNum = activityRemarkDelDao.insertItems(delAcRemarks);//插入的删除记录数
        //  完成删除操作
        int delRemNum = activityRemarkDao.deleteByIds(acRemIds);//删除的备注数
        //删除市场活动
        //  拿到要删除的市场活动
        List<Activity> activities = activityDao.selectByIds(ids);
        //  插入到已删除的表中
        List<DelContainer<Activity>> delActivities = new ArrayList<DelContainer<Activity>>();
        for(Activity activity : activities){
            delActivities.add(new DelContainer<Activity>(UUIDUtils.getUUID(),currentTime,optUsrId));
        }
        int insActNum = activityDelDao.insertItems(delActivities);//插入的删除记录数
        //  完成删除操作
        int delActNum = activityDao.deleteByIds(ids);//删除的活动数
        /**
         * 删除成功的判定：
         * 备注数=插入备注数=删除备注数
         * 且
         * 活动数=插入活动数=删除活动数
         */
        return (remNum==insRemNum || insRemNum==delRemNum)||(insActNum==ids.length || insActNum==delActNum);
    }

    @Override
    public Activity getDetailById(String id) {
        return activityDao.selectDetailById(id);
    }

    public void setActivityDao(ActivityDao activityDao) {
        this.activityDao = activityDao;
    }

    public void setActivityDelDao(ActivityDelDao activityDelDao) {
        this.activityDelDao = activityDelDao;
    }

    public void setActivityRemarkDao(ActivityRemarkDao activityRemarkDao) {
        this.activityRemarkDao = activityRemarkDao;
    }

    public void setActivityRemarkDelDao(ActivityRemarkDelDao activityRemarkDelDao) {
        this.activityRemarkDelDao = activityRemarkDelDao;
    }
}
