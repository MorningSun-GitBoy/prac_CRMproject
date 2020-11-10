package com.neau.crm.web.service.serviceImpl;

import com.neau.crm.utils.ServerSessionUtils;
import com.neau.crm.web.dao.ActivityDao;
import com.neau.crm.web.domain.Activity;
import com.neau.crm.web.domain.vo.PageInfo;
import com.neau.crm.web.service.ActivityService;

import java.util.List;
import java.util.Map;

public class ActivityServiceImpl implements ActivityService {
    private ActivityDao activityDao = ServerSessionUtils.getSqlSession().getMapper(ActivityDao.class);

    @Override
    public boolean save(Activity activity) {
        int count = activityDao.save(activity);
        return count == 1;
    }

    @Override
    public PageInfo<Activity> pageList(Map<String, String> parameters) {
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
}
