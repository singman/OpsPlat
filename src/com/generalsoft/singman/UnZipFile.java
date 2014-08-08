package com.generalsoft.singman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
/**
 * Created with IntelliJ IDEA.
 * User: singman
 * Date: 14-8-5
 * Time: 下午4:25
 * 使用SSH连接到服务器，调用服务器命令解压文件
 */
public class UnZipFile {
    /**
     * 服务器上解压文件
     * @autohor singman
     * @date 2014年8月5日19:47:10
     * @return
     */

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

    /**
     * 解压文件
     * @param conn
     * @param user_name
     * @param password
     * @param filename
     * @param directory
     * @return
     */
    public  int unZipFile(Connection conn,String user_name,String password,String filename,String directory,String deployModel){
        Session session = null;
        int ret = -1;

        try {
            if (conn.authenticateWithPassword(user_name, password)) {  // 认证
                session = conn.openSession();               // 打开一个会话
            }
            System.out.println("username : "+user_name);
            System.out.println("password:  " +password);
            System.out.println("开始解压文件……");
            if(deployModel.equals("incDeploy"))
            {
                session.execCommand("cd " + directory + ";" + "tar xvf " + filename);       //切换目录，解压文件 ,增量发版使用tar命令解压
            }
            else if (deployModel.equals("fullDeploy")){
                session.execCommand("cd " + directory + ";" + "jar xvf " + filename);       //切换目录，解压文件 ,全量发版使用jar命令解压
            }
            else{
                System.out.println("发布方式参数错误，文件未解压！");
            }

            InputStream stdErr = new StreamGobbler(session.getStderr());
            InputStream stdOut = new StreamGobbler(session.getStdout());
            String line = null;
            BufferedReader brError = new BufferedReader(new InputStreamReader(stdErr));
            BufferedReader brStdout = new BufferedReader(new InputStreamReader(stdOut));

            while ((line = brError.readLine()) != null){
                System.out.println(line);
            }
            while ((line = brStdout.readLine()) != null){
                System.out.println(line);
            }
            ret = session.getExitStatus();
            session.close();
            conn.close();
            if(ret==0)
            {
                System.out.println("解压成功！");
            }
            else {
                System.out.println("解压失败！");
            }
            return ret;

    }
        catch (IOException e)
        {
            e.printStackTrace();
        }
      return  ret;
   }
}
