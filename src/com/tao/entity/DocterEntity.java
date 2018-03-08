package com.tao.entity;

/**
 * Created by LHL on 2016/8/4.
 */
public class DocterEntity {
    private int Did;
    private String Dname;
    private String Sex;
    private String Depart;
    private String Dphone;
    private String Dlogname;
    private String Dpword;

    public int getDid() {  return Did;    }

    public void setDid(int did) {
        this.Did = did;
    }

    public String getDname() {
        return Dname;
    }

    public void setDname(String dname) {
        this.Dname = dname;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        this.Sex = sex;
    }

    public String getDepart() {
        return Depart;
    }

    public void setDepart(String depart) {
        this.Depart = depart;
    }

    public String getDphone() {
        return Dphone;
    }

    public void setDphone(String phone) {
        this.Dphone = phone;
    }

    public String getDlogname() {  return Dlogname;    }

    public void setDlogname(String dlogname) {
        this.Dlogname = dlogname;
    }

    public String getDpword() {
        return Dpword;
    }

    public void setDpword(String dpword) {
        this.Dpword = dpword;
    }

    @Override
    public String toString() {
        return "DocterEntity{" +
                ", Dname='" + Dname + '\'' +
                ", Sex='" + Sex + '\'' +
                ", Depart='" + Depart + '\'' +
                ", Dphone='" + Dphone + '\'' +
                ", Dlogname='" + Dlogname + '\'' +
                ", Dpword='" + Dpword + '\'' +
                '}';
    }
}
