package com.generalsoft.singman.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Vector;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/**
 * Created with IntelliJ IDEA.
 * User: singman
 * Date: 14-8-5
 * Time: 下午5:13
 * 使用SFTP上传、下载、删除文件的工具类
 */
public class FileUpload {

    /**
     *
     * @param server_ip
     * @param server_port
     * @param user_name
     * @param password
     * @return
     */
    public ChannelSftp connect(String server_ip, int server_port, String user_name,
                               String password) {
        ChannelSftp sftp = null;
        try {
            JSch jsch = new JSch();
            jsch.getSession(user_name, server_ip, server_port);
            Session sshSession = jsch.getSession(user_name, server_ip, server_port);
            System.out.println("Session created.");
            sshSession.setPassword(password);
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(sshConfig);
            sshSession.connect();
            System.out.println("Session connected.");
            System.out.println("Opening Channel.");
            Channel channel = sshSession.openChannel("sftp");
            channel.connect();
            sftp = (ChannelSftp) channel;
            System.out.println("Connected to " + server_ip + ".");
        } catch (Exception e) {

        }
        return sftp;
    }
    /**
     * 上传文件
     * @param directory 上传的目录
     * @param uploadFile 要上传的文件
     * @param sftp
     */
    public void upload(String directory, String uploadFile, ChannelSftp sftp) {
        try {
            sftp.cd(directory);
            File file=new File(uploadFile);
            FileInputStream fis = new FileInputStream(file);
            System.out.println("文件size:"+String.valueOf(fis.available()/1000)+"k");
            sftp.put(new FileInputStream(file), file.getName(),new FileProgressMonitor(fis.available()), ChannelSftp.OVERWRITE);
        }
        catch (SftpException sftpException){
            sftpException.printStackTrace();
            return;
        }
        catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    /**
     * 下载文件
     * @param directory 下载目录
     * @param downloadFile 下载的文件
     * @param saveFile 存在本地的路径
     * @param sftp
     */
    public void download(String directory, String downloadFile,String saveFile, ChannelSftp sftp) {
        try {
            sftp.cd(directory);
            File file=new File(saveFile);
            sftp.get(downloadFile, new FileOutputStream(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除文件
     * @param directory 要删除文件所在目录
     * @param deleteFile 要删除的文件
     * @param sftp
     */
    public void delete(String directory, String deleteFile, ChannelSftp sftp) {
        try {
            sftp.cd(directory);
            sftp.rm(deleteFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 列出目录下的文件
     * @param directory 要列出的目录
     * @param sftp
     * @return
     * @throws SftpException
     */
    public Vector listFiles(String directory, ChannelSftp sftp) throws SftpException{
        return sftp.ls(directory);
    }
}
