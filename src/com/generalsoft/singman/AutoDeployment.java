package com.generalsoft.singman;

import com.jcraft.jsch.ChannelSftp;

import java.io.File;
import java.util.ResourceBundle;
import ch.ethz.ssh2.Connection;

/**
 * Created with IntelliJ IDEA.
 * User: singman
 * Date: 14-8-5
 * Time: 下午7:59
 * To change this template use File | Settings | File Templates.
 */
public class AutoDeployment  {
    private static ResourceBundle rb = ResourceBundle.getBundle("system");
    private static String server_ip =  rb.getString("SERVER_IP");
    private static int server_port =Integer.parseInt( rb.getString("SERVER_PORT"));
    private static String user_name =  rb.getString("USER_NAME");
    private static String password = rb.getString("PASSWORD");

    public void  uploadAndUnzipFile  (String directory,String uploadFile,String deployModel)
    {
        FileUpload fu = new FileUpload();
        ChannelSftp sftp=fu.connect(server_ip, server_port, user_name, password);
        System.out.println("开始上传文件");
        fu.upload(directory, uploadFile, sftp);
        System.out.println("上传文件结束");

        File tempFile =new File( uploadFile.trim());
        String fileName = tempFile.getName();  //获取文件名
        System.out.println("fileName = " + fileName);
        UnZipFile uzf = new UnZipFile();
        Connection conn = uzf.establishConnection(server_ip);

        uzf.unZipFile(conn,user_name,password,fileName,directory,deployModel);

    }

}
