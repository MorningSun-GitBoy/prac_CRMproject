package com.neau.crm.web.dao;

import com.neau.crm.web.domain.Activity;
import com.neau.crm.web.domain.ActivityRemark;
import com.neau.crm.web.domain.fordelet.DelContainer;

import java.util.List;

public interface DeleteDao {
    public int insertActivities(List<DelContainer<Activity>> actDelList);
    public int deleteActivityByIds(String[] isd);
    public int insertActivityRemarks(List<DelContainer<ActivityRemark>> acRemDelList);
    public int deleteActivityRemarkByIds(String[] ids);
}
