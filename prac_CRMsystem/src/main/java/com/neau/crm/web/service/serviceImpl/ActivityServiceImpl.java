package com.neau.crm.web.service.serviceImpl;

import com.neau.crm.utils.ServerSessionUtils;
import com.neau.crm.web.dao.ActivityDao;
import com.neau.crm.web.domain.Activity;
import com.neau.crm.web.service.ActivityService;

public class ActivityServiceImpl implements ActivityService {
    private ActivityDao activityDao = ServerSessionUtils.getSqlSession().getMapper(ActivityDao.class);

    @Override
    public boolean save(Activity activity) {
        int count = activityDao.save(activity);
        return count == 1;
    }
}
