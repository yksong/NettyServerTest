package com.tao.dao;

import com.tao.entity.PatientEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Created by LHL on 2016/8/9.
 */
public interface PatientMapper {

    public List<PatientEntity> getPatientList(String Pname);

    public List<PatientEntity> selectAllPatients();

    public PatientEntity getPatientsByPname(String Pname);

    public void addPatient(PatientEntity patient);

    public void updatePatient(PatientEntity patient);

    public void deletePatient(String Pname);

    List<String> getPatientlist();

    @Select("select * from patient where Pname = #{Pname}")
    public Map<String, Object> selectPatientByPname(String Pname);
}
