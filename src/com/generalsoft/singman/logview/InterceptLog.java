package com.generalsoft.singman.logview;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * User: singman
 * Date: 2014/8/13
 * Time: 16:26
 * Project:CFS_v0.2
 * Usage:
 */
public class InterceptLog {
    public Connection establishConnection(String server_ip){
        Connection conn = null;
        try {
            conn = new Connection(server_ip);
            conn.connect(); // 连接

        }
        catch (IOException e) {
            e.printStackTrace();

        }
        finally {

        }
        return conn;
    }
    public  int InterceptLog(Connection conn,String user_name,String password,String filename,String directory,int lineNum) {
        Session session = null;
        int ret = -1;

        try {
            if (conn.authenticateWithPassword(user_name, password)) {  // 认证
                session = conn.openSession();               // 打开一个会话
            }
            System.out.println("username : " + user_name);
            System.out.println("password:  " + password);
            System.out.println("开始截取日志……");

            session.execCommand("cd " + directory + ";" + "tail -"+lineNum +" " + filename);       //切换目录，截取文件



            InputStream stdErr = new StreamGobbler(session.getStderr());
            InputStream stdOut = new StreamGobbler(session.getStdout());
            String line = null;
            BufferedReader brError = new BufferedReader(new InputStreamReader(stdErr, "utf-8"));
            BufferedReader brStdout = new BufferedReader(new InputStreamReader(stdOut, "utf-8"));

            while ((line = brError.readLine()) != null) {
                System.out.println(line);
            }
            while ((line = brStdout.readLine()) != null) {
                System.out.println(line);
            }
            ret = session.getExitStatus();
            session.close();
            conn.close();
            if (ret == 0) {
                System.out.println("截取日志成功！");
            } else {
                System.out.println("截取日志失败！");
            }
            return ret;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
