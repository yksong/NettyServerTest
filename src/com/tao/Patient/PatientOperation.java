package com.tao.Patient;

import com.tao.dao.DocterMapper;
import com.tao.dao.PatientMapper;
import com.tao.entity.PatientEntity;
import com.tao.json.JsonOper;
import com.tao.log.LogUtils;
import com.tao.parser.ParserMap;
import io.netty.channel.ChannelHandlerContext;
import net.sf.json.JSONObject;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by LHL on 2016/8/9.
 */
public class PatientOperation {
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;

    static {
        try {
            reader = Resources.getResourceAsReader("config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static SqlSessionFactory getSession() {
        return sqlSessionFactory;
    }

    //根据病人姓名查询病人信息
    public static void getPatientList(ChannelHandlerContext ctx, String data, int type) {
        PatientEntity entity1 = (PatientEntity) ParserMap.get(type).build(JSONObject.fromObject(data));
        try
        {
            SqlSession session = sqlSessionFactory.openSession();
            PatientMapper patientMapper = session.getMapper(PatientMapper.class);
            List<PatientEntity> patients = patientMapper.getPatientList(entity1.getPname());
            Map<String,Object> entity2=patientMapper.selectPatientByPname(entity1.getPname());
            // 显示Patients信息
            for (PatientEntity patient : patients) {
                System.out.println("Patient Id: " + patient.getPid());
                System.out.println("Patient Name: " + patient.getPname());
                System.out.println("Patient Sex: " + patient.getSex());
                System.out.println("Patient Age: " + patient.getAge());
                System.out.println("Patient Adress: " + patient.getAdress());
                System.out.println("Patient Phone: " + patient.getPhone());
                System.out.println("Patient Did: " + patient.getDid());
                System.out.println("Patient Heart: " + patient.getHeart());
                System.out.println("Patient Addition: " + patient.getAddition());
                System.out.println("Patient Logname: " + patient.getLogname());
                System.out.println("Patient Pword: " + patient.getPword());
            }
            String ans = JsonOper.put2Json(entity2);
            LogUtils.I(ans);
            ctx.writeAndFlush(ans + "\r\n");
            LogUtils.I("查询完成！");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //增加病人记录
    public static void addPatient(ChannelHandlerContext ctx, String data, int type) {
        /**
         * addDocter 增加病人
         * @params: [ctx, msg]
         * @return: void
         * @throw:
         */
        PatientEntity entity1 = (PatientEntity) ParserMap.get(type).build(JSONObject.fromObject(data));
        try {
            // 获取Session连接
            SqlSession session = sqlSessionFactory.openSession();
            // 获取Mapper
            PatientMapper patientMapper = session.getMapper(PatientMapper.class);
            DocterMapper docterMapper=session.getMapper(DocterMapper.class);
            System.out.println("Test insert start...");
            PatientEntity patient = new PatientEntity();
            // 执行插入
            if(docterMapper.getDoctersByDid(entity1.getDid())!=null){
                if(patientMapper.getPatientsByPname(entity1.getPname())==null) {
                    patient.setPname(entity1.getPname());
                    patient.setSex(entity1.getSex());
                    patient.setAge(entity1.getAge());
                    patient.setAdress(entity1.getAdress());
                    patient.setPhone(entity1.getPhone());
                    patient.setDid(entity1.getDid());
                    patient.setHeart(entity1.getHeart());
                    patient.setAddition(entity1.getAddition());
                    patient.setLogname(entity1.getLogname());
                    patient.setPword(entity1.getPword());
                    patientMapper.addPatient(patient);
                    // 提交事务
                    LogUtils.I("添加成功！");
                }
                else
                    LogUtils.I("记录已存在！");
            }
            else
                LogUtils.I("输入错误！");
            session.commit();
            System.out.println("After insert");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    //更新病人记录
    public static void updatePatient(ChannelHandlerContext ctx, String data, int type) {
        /**
         * 修改后要commit
         * 更新数据表
         *  * @param [ctx, msg]
         */
        PatientEntity entity1 = (PatientEntity) ParserMap.get(type).build(JSONObject.fromObject(data));
        SqlSession session = sqlSessionFactory.openSession();
        try
        {
            // 获取Session连接
            // 获取Mapper
            System.out.println("Test update start...");
            // 执行更新
            PatientMapper patientMapper = session.getMapper(PatientMapper.class);
            PatientEntity patient = patientMapper.getPatientsByPname(entity1.getPname());
            if(!patient.equals(" ")){
                patient.setAge(entity1.getAge());
                patientMapper.updatePatient(patient);
                // 提交事务
                LogUtils.I("更新成功！");
                session.commit();
            }
            else
                LogUtils.I("错误操作！");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //删除病人记录
    public static void deletePatient(ChannelHandlerContext ctx, String data, int type) {
        /**
         * 删除后要commit.
         *删除医生信息
         * @param [ctx, msg]
         */
        PatientEntity entity1 = (PatientEntity) ParserMap.get(type).build(JSONObject.fromObject(data));
        SqlSession session = sqlSessionFactory.openSession();
        PatientMapper patientMapper = session.getMapper(PatientMapper.class);
        try {
            if (patientMapper.getPatientsByPname(entity1.getPname())!=null){
                patientMapper.deletePatient(entity1.getPname());
                LogUtils.I("删除记录成功！");
            }
            else
                LogUtils.I("记录不存在!");
            session.commit();
        } finally {
            session.close();
        }
    }

    //查询所有病人信息
    public static void getAllPatients(ChannelHandlerContext ctx, int type){
        try
        {
            SqlSession session = sqlSessionFactory.openSession();
            PatientMapper patientMapper = session.getMapper(PatientMapper.class);
            // 显示Patient信息
            List<String> patientList = patientMapper.getPatientlist();
            for(int i=0;i<patientList.size();i++){
                PatientEntity entity=patientMapper.getPatientsByPname(patientList.get(i));
                Map<String,Object> entity2=patientMapper.selectPatientByPname(entity.getPname());
                String ans = JsonOper.put2Json(entity2);
                LogUtils.I(ans);
                ctx.writeAndFlush(ans + "\r\n");
            }
            System.out.println("Test Get start...");
            printDocters(patientMapper.selectAllPatients());
            System.out.println("Test Get finished...");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //打印病人信息
    private static void printDocters(final List<PatientEntity> patients)
    {
        int count = 0;
        for (PatientEntity patient : patients)
        {
            System.out.println(MessageFormat.format("============= Patient[{0}]=================", ++count));
            System.out.println("Patient Id: " + patient.getPid());
            System.out.println("Patient Name: " + patient.getPname());
            System.out.println("Patient Sex: " + patient.getSex());
            System.out.println("Patient Age: " + patient.getAge());
            System.out.println("Patient Adress: " + patient.getAdress());
            System.out.println("Patient Phone: " + patient.getPhone());
            System.out.println("Patient Did: " + patient.getDid());
            System.out.println("Patient Heart: " + patient.getHeart());
            System.out.println("Patient Addition: " + patient.getAddition());
            System.out.println("Patient Logname: " + patient.getLogname());
            System.out.println("Patient Pword: " + patient.getPword());
        }
    }
}
