package com.tao.netty_server_test;

import com.tao.Docter.DocterOperation;
import com.tao.Patient.PatientOperation;
import com.tao.Txtserver.ServerTest;
import com.tao.log.LogUtils;
import com.tao.login.Login;
import io.netty.channel.ChannelHandlerContext;

/**
 * Author: Douglass
 * Date: 2016/4/5
 * E-mail: xuetao_ni@163.com
 */
public class FunctionParser {
    public static void parseFunction(ChannelHandlerContext ctx,String msg){
        /**
         * parseFunction 根据读取的内容，判断客户端想执行的功能
         * @author: Douglass
         * @time: 2016/4/5 16:14
         * @params: [ctx, msg]
         * @return: void
         * @throw:
         */
        System.out.println(ctx.channel().remoteAddress() + " Say : " + msg);
        char type = msg.charAt(0);//读取消息的第一个字节
        String data = msg.substring(1);

        switch (type){
            case 'l':
                //处理登陆
                Login.login(ctx, data, 'l');
                break;
            case 'b':
                //增加医生
                DocterOperation.addDocter(ctx, data, 'b');
                break;
            case 'f':
                //删除医生
                DocterOperation.deleteDocter(ctx, data, 'f');
                break;
            case 4:
                //更新医生
                DocterOperation.updateDocter(ctx, data, 4);
                break;
            case 'c':
                //根据姓名查询医生
                DocterOperation.getDocterList(ctx, data, 'c');
                break;
            case 'e':
                //查询所有医生
                DocterOperation docterOperation=new DocterOperation();
                docterOperation.getAllDocters(ctx,'e');
                break;
            case 'g':
                //增加病人
                PatientOperation.addPatient(ctx, data, 'g');
                break;
            case 8:
                //根据姓名查询病人
                PatientOperation.getPatientList(ctx,data,8);
                break;
            case 9:
                //更新病人
                PatientOperation.updatePatient(ctx, data,9);
                break;
            case 'd':
                //删除病人记录
                PatientOperation.deletePatient(ctx,data,'d');
                break;
            case 'a':
                //查询所有病人
                PatientOperation patientOperation=new PatientOperation();
                patientOperation.getAllPatients(ctx,'a');
                break;
            default:
                LogUtils.I("Cannot find the parser!");
                ctx.writeAndFlush("Cannot find the parser!");
                break;
        }
    }
}
