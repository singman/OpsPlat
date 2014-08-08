package com.generalsoft.singman;

/**
 * User: singman
 * Date: 14-8-7
 * Time: 下午2:46
 * Project:CFS_v0.2
 * Usage:
 */
public class FileUploadThread implements Runnable {
   private  String directory;
   private String uploadFile;
   private  String deployModel;
   public  FileUploadThread (String directory,String uploadFile,String deployModel) {
       this.directory = directory;
       this.uploadFile = uploadFile;
       this.deployModel = deployModel;
    }
    public void run() {
        try {
            Thread.sleep(5000);
            new AutoDeployment().uploadAndUnzipFile(directory,uploadFile,deployModel);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
