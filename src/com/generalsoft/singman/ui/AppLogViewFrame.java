package com.generalsoft.singman.ui;

import com.generalsoft.singman.utils.ConsoleTextArea;
import com.generalsoft.singman.logview.LogViewThread;
import com.generalsoft.singman.utils.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * User: singman
 * Date: 2014/8/13
 * Time: 15:29
 * Project:CFS_v0.2
 * Usage:
 */
public class AppLogViewFrame implements ActionListener{

        //JTabbedPane tabPane = new JTabbedPane();// 选项卡布局
        Container con = new Container();

        JLabel log_path_label = new JLabel("日志路径");
        JTextField log_path = new JTextField();

        JLabel log_name_label = new JLabel("日志名称");
        JTextField log_name = new JTextField();

        JLabel line_num_label = new JLabel("截取行数");
        JTextField line_num = new JTextField();


        JButton OKButton = new JButton("确定");//

        ConsoleTextArea consoleTextArea = null;
        JScrollPane scrollPane = null;

        public Container   AppLogViewFrame() {
            log_path_label.setBounds(10, 10, 70, 20);
            log_path.setBounds(75, 10, 200, 20);

            log_name_label.setBounds(10, 35, 70, 20);
            log_name.setBounds(75, 35, 200, 20);

            line_num_label.setBounds(10, 60, 70, 20);
            line_num.setBounds(75, 60, 200, 20);

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

            con.add(log_path_label);
            con.add(log_path);
            con.add(log_name_label);
            con.add(log_name);
            con.add(line_num_label);
            con.add(line_num);

            con.add(OKButton);
            con.add(scrollPane);
            OKButton.addActionListener(this); // 添加事件处理

            return con;
        }
        public void actionPerformed(ActionEvent e) {

            if (e.getSource().equals(OKButton)) {
                    String directory =  log_path.getText();
                    String logFile = log_name.getText();
                    String lineNumStr = line_num.getText();
                    if(directory.equals("")){
                        JOptionPane.showMessageDialog(con, "请指定服务器目录", "提示", 2);
                        return;
                    }
                    if(logFile.equals(""))
                    {
                        JOptionPane.showMessageDialog(con, "请指定日志文件", "提示", 2);
                        return;
                    }
                    if(lineNumStr.equals("") || !utils.isNumeric(lineNumStr))
                    {
                        JOptionPane.showMessageDialog(con, "请输入截取行数(数字)", "提示", 2);
                        return;
                    }
                    int lineNum   = Integer.parseInt(lineNumStr);
                    new Thread(new LogViewThread(directory,logFile,lineNum)).start();
            }
        }


}
