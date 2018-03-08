package com.tao.entity;

/**
 * Author: Douglass
 * Date: 2016/3/29
 * E-mail: xuetao_ni@163.com
 */
public class UserEntity {
    private int Lid;
    private String Lname;
    private String passwd;
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return Lid;
    }

    public void setId(int id) {
        this.Lid = id;
    }

    public String getName() {
        return Lname;
    }

    public void setName(String name) {
        this.Lname = name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "Lid=" + Lid +
                ", Lname='" + Lname + '\'' +
                ", passwd='" + passwd + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
