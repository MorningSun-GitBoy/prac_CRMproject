package com.neau.crm.web.service.serviceImpl;

import com.neau.crm.utils.ServerSessionUtils;
import com.neau.crm.web.dao.ActivityDao;

public class ActivityServiceImpl {
    private ActivityDao activityDao = ServerSessionUtils.getSqlSession().getMapper(ActivityDao.class);

}
