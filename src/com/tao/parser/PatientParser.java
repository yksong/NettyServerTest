package com.tao.parser;

import com.tao.entity.PatientEntity;
import com.tao.log.LogUtils;
import net.sf.json.JSONObject;

/**
 * Created by LHL on 2016/8/9.
 */
public class PatientParser implements Parser<PatientEntity>{
    @Override
    public PatientEntity build(JSONObject object) {
        PatientEntity entity = new PatientEntity();

        entity.setPname(object.getString("Pname"));
        entity.setSex(object.getString("Sex"));
        entity.setAge(object.getInt("Age"));
        entity.setAdress(object.getString("Adress"));
        entity.setPhone(object.getString("Phone"));
        entity.setDid(object.getInt("Did"));
        entity.setHeart(object.getString("Heart"));
        entity.setAddition(object.getString("Addition"));
        entity.setLogname(object.getString("Logname"));
        entity.setPword(object.getString("Pword"));
        LogUtils.I(entity.toString());
        return entity;
    }
}
