package com.neau.crm.web.dao;

import com.neau.crm.web.domain.ActivityRemark;
import com.neau.crm.web.domain.fordelet.DelContainer;

import java.util.List;
import java.util.Map;

public interface ActivityRemarkDelDao {
    public int insertItems(List<DelContainer<ActivityRemark>> acRemDelList);
}
