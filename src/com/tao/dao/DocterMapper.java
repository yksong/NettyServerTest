package com.tao.dao;

import com.tao.entity.DocterEntity;
import com.tao.entity.UserEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Created by LHL on 2016/8/4.
 */
public interface DocterMapper {

    public List<DocterEntity> getDocterList(String Dname);

    public List<DocterEntity> selectAllDocters();

    public DocterEntity getDoctersByDname(String Dname);

    public DocterEntity getDoctersByDid(int Did);

    public void addDocter(DocterEntity docter);

    public void updateDocter(DocterEntity docter);

    public void deleteDocter(String Dname);

    List<String> getDocterlist();

    @Select("select * from docter where Dname = #{Dname}")
    public Map<String, Object> selectDocterByDname(String Dname);
}
