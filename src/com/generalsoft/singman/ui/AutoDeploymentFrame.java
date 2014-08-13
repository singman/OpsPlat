package com.generalsoft.singman.ui;
import com.generalsoft.singman.utils.ConsoleTextArea;
import com.generalsoft.singman.FileUploadThread;
import java.awt.Container;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: singman
 * Date: 14-8-6
 * Time: 上午9:14
 * To change this template use File | Settings | File Templates.
 */
public  class  AutoDeploymentFrame  implements ActionListener   {
        Container con = new Container();//

        //本地文件选择
        JLabel choseFile = new JLabel("选择文件");
        JTextField filePath = new JTextField();// 本地文件的路径
        JButton choseButton = new JButton("…");// 选择

        //服务器目录输入
        JLabel serverDir = new JLabel("上传目录");
        JTextField serverDirPath = new JTextField();// 服务器上传目录

        JLabel deployModelLabel = new JLabel("发布模式");
        JRadioButton incDeploy = new JRadioButton("增量",false);
        JRadioButton fullDeploy = new JRadioButton("全量",false);

        ButtonGroup deployGroup = new ButtonGroup();

        //控制台输出框

         ConsoleTextArea consoleTextArea = null;
         JScrollPane scrollPane = null;


        JFileChooser jfc = new JFileChooser();// 文件选择器
        JButton OKButton = new JButton("确定");//

    public Container   AutoDeploymentFrame() {
            jfc.setCurrentDirectory(new File("d://"));// 文件选择器的初始目录定为d盘

            //设定组件布局
            choseFile.setBounds(10, 10, 70, 20);
            filePath.setBounds(75, 10, 200, 20);
            choseButton.setBounds(280, 10, 50, 20);

            serverDir.setBounds(10, 35, 70, 20);
            serverDirPath.setBounds(75, 35, 200, 20);

            deployModelLabel.setBounds(10, 60, 70, 20);
            incDeploy.setBounds(75,60,60,20);
            fullDeploy.setBounds(190,60,60,20);
            deployGroup.add(incDeploy);
            deployGroup.add(fullDeploy);

            OKButton.setBounds(360, 75, 70, 40);

         //控制台输出框
        try {
            consoleTextArea = new ConsoleTextArea();
            scrollPane = new JScrollPane(consoleTextArea);
            //设置控制台输出信息自动滚动到底部 开始
            int height = 10;
            Point p = new Point();
            p.setLocation(0,this.consoleTextArea.getLineCount()*height);
            this.scrollPane.getViewport().setViewPosition(p);
            //设置控制台输出信息自动滚动到底部 结束
        }
        catch(IOException e) {
            System.err.println(
                    "不能创建LoopedStreams：" + e);
            System.exit(1);
        }

            scrollPane.setBounds(0,120,470,150);
            //添加按钮事件监听
            choseButton.addActionListener(this); // 添加事件处理
            OKButton.addActionListener(this); // 添加事件处理

            //添加组件到容器
            con.add(choseFile);
            con.add(filePath);
            con.add(choseButton);

            con.add(serverDir);
            con.add(serverDirPath);

            con.add(deployModelLabel);
            con.add(incDeploy);
            con.add(fullDeploy);

            con.add(scrollPane);

            con.add(OKButton);

            return con;

        }
        /**
         * 事件监听的方法
         */
        public void actionPerformed (ActionEvent e) {
            // 绑定到选择文件，先择文件事件
            if (e.getSource().equals(choseButton)) {
                jfc.setFileSelectionMode(0);// 设定只能选择到文件
                int state = jfc.showOpenDialog(null);// 此句是打开文件选择器界面的触发语句
                if (state == 1) {
                    return; // 撤销则返回
                } else {
                    File f = jfc.getSelectedFile();// f为选择到的文件
                    filePath.setText(f.getAbsolutePath());
                }
            }
            if (e.getSource().equals(OKButton)) {
                String directory =  serverDirPath.getText();
                String uploadFile = filePath.getText();
                String deployModel = null;
                if(directory.equals("")){
                    JOptionPane.showMessageDialog(con, "请指定服务器目录", "提示", 2);
                    return;
                }
                if(uploadFile.equals(""))
                {
                    JOptionPane.showMessageDialog(con, "请选择本地文件", "提示", 2);
                    return;
                }
                if(incDeploy.isSelected()||fullDeploy.isSelected())
                {
                    if(incDeploy.isSelected()){
                         deployModel = "incDeploy";
                    }
                    else {
                        deployModel = "fullDeploy";
                    }
                }
                else{
                    JOptionPane.showMessageDialog(con, "请选择发布模式（全量/增量）", "提示", 2);
                    return;
                }
                System.out.println("启动文件上传解压子线程……");

                new Thread(new FileUploadThread(directory,uploadFile,deployModel)).start();

            }
        }

}


