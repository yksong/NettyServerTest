package com.tao.parser;

import com.tao.entity.DocterEntity;
import com.tao.log.LogUtils;
import net.sf.json.JSONObject;

/**
 * Created by LHL on 2016/8/4.
 */
public class DocterParser implements Parser<DocterEntity> {
    @Override
    public DocterEntity build(JSONObject object) {
        DocterEntity entity = new DocterEntity();
        entity.setDname(object.getString("Dname"));
        entity.setSex(object.getString("Sex"));
        entity.setDepart(object.getString("Depart"));
        entity.setDphone(object.getString("Dphone"));
        entity.setDlogname(object.getString("Dlogname"));
        entity.setDpword(object.getString("Dpword"));
        LogUtils.I(entity.toString());
        return entity;
    }
}
