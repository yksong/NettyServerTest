package com.tao.entity;

/**
 * Created by LHL on 2016/8/9.
 */
public class PatientEntity {
    private int Pid;
    private String Pname;
    private String Sex;
    private int Age;
    private String Adress;
    private String Phone;
    private int Did;
    private String Heart;
    private String Addition;
    private String Logname;
    private String Pword;

    public int getPid() {  return Pid;  }

    public void setPid(int pid) {  this.Pid = pid;  }

    public String getPname() {   return Pname;    }

    public void setPname(String pname) {   this.Pname = pname;    }

    public String getSex() {   return Sex;  }

    public void setSex(String sex) {   this.Sex = sex;  }

    public int getAge() {   return Age;  }

    public void setAge(int age) {   this.Age = age;  }

    public String getAdress() {   return Adress;   }

    public void setAdress(String adress) {   this.Adress = adress; }

    public String getPhone() {    return Phone;  }

    public void setPhone(String phone) {    this.Phone = phone;  }

    public int getDid() {     return Did;  }

    public void setDid(int did) {     this.Did = did;  }

    public String getHeart() {        return Heart;    }

    public void setHeart(String heart) {         this.Heart = heart;    }

    public String getAddition() {        return Addition;    }

    public void setAddition(String addition) {         this.Addition = addition;    }

    public String getLogname() {    return Logname;  }

    public void setLogname(String logname) {    this.Logname = logname;   }

    public String getPword() {     return Pword;  }

    public void setPword(String pword) {   this.Pword = pword;   }
}
