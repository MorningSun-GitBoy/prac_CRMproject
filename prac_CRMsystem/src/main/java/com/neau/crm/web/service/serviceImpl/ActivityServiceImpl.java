package com.neau.crm.web.service.serviceImpl;

import com.neau.crm.utils.ServerSessionUtils;
import com.neau.crm.web.dao.ActivityDao;
import com.neau.crm.web.dao.ActivityDelDao;
import com.neau.crm.web.dao.ActivityRemarkDao;
import com.neau.crm.web.dao.ActivityRemarkDelDao;
import com.neau.crm.web.domain.Activity;
import com.neau.crm.web.domain.vo.PageInfo;
import com.neau.crm.web.service.ActivityService;

import java.util.ArrayList;
import java.util.HashMap;
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
    public boolean deleteByIds(String[] ids) {
        //查询出需要删除的备注
        Map<String,String> condition = new HashMap<String,String>();
        List<String> acRemIdList = new ArrayList<String>();//备注的ID的列表
        for(String id:ids) {
            condition.put("id",id);
            acRemIdList.add(activityRemarkDao.selectId(condition));
        }
        int remNum = acRemIdList.size();
        //删除备注
        //  拿到要删除的备注
        //  插入到已删除的表中
        //  完成删除操作
        //删除市场活动
        //  拿到要删除的市场活动
        //  插入到已删除的表中
        //  完成删除操作
        return false;
    }
}
