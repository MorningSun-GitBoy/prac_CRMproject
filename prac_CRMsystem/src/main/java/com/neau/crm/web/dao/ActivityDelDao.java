package com.neau.crm.web.dao;

import com.neau.crm.web.domain.Activity;
import com.neau.crm.web.domain.fordelet.DelContainer;

import java.util.List;

public interface ActivityDelDao {
    public int insertItems(List<DelContainer<Activity>> actDelList);
}
