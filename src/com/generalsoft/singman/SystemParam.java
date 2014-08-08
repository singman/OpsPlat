package com.generalsoft.singman;

/**
 * User: singman
 * Date: 14-8-6
 * Time: 下午2:16
 * Project:CFS_v0.2
 * Usage:
 */
public class SystemParam {
    private  String  SERVER_IP;
    private  String  SERVER_POR;
    private  String  USER_NAME;
    private  String  PASSWORD;
    public String getSERVER_IP() {
        return SERVER_IP;
    }

    public void setSERVER_IP(String SERVER_IP) {
        this.SERVER_IP = SERVER_IP;
    }

    public String getSERVER_POR() {
        return SERVER_POR;
    }

    public void setSERVER_POR(String SERVER_POR) {
        this.SERVER_POR = SERVER_POR;
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public void setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }


}
