package com.generalsoft.singman.logview;


import ch.ethz.ssh2.Connection;

import java.util.ResourceBundle;

/**
 * User: singman
 * Date: 2014/8/13
 * Time: 16:16
 * Project:CFS_v0.2
 * Usage:
 */
public class LogViewThread implements Runnable {
    private static ResourceBundle rb = ResourceBundle.getBundle("system");
    private static String server_ip =  rb.getString("SERVER_IP");
    private static int server_port =Integer.parseInt( rb.getString("SERVER_PORT"));
    private static String user_name =  rb.getString("USER_NAME");
    private static String password = rb.getString("PASSWORD");

    private  String directory;
    private  String logFile;
    private  int lineNum;

    public  LogViewThread(String directory,String logFile,int lineNum){
       this.directory = directory;
       this.logFile = logFile;
       this.lineNum = lineNum;
    }
    public void run() {
        try {
            Thread.sleep(5000);
            InterceptLog lc = new InterceptLog();
            Connection conn = lc.establishConnection(server_ip);
            new InterceptLog().InterceptLog( conn, user_name, password, logFile, directory, lineNum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
