package com.generalsoft.singman.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: singman
 * Date: 14-8-6
 * Time: 下午2:42
 * Project:CFS_v0.2
 * Usage:
 */
public class SystemParmFrame implements ActionListener {
    JTabbedPane tabPane = new JTabbedPane();// 选项卡布局
    Container con = new Container();
    JLabel server_ip_label = new JLabel("服务器地址：");
    JTextField server_ip = new JTextField();

    JLabel server_port_label = new JLabel("服务器端口：");
    JTextField server_port = new JTextField();

    JLabel username_label = new JLabel("用户名：");
    JTextField username = new JTextField();

    JLabel password_label = new JLabel("密码：");
    JTextField password = new JTextField();

    JButton SaveButton = new JButton("保存");//

    public JTabbedPane   SystemParmFrame() {
        server_ip_label.setBounds(10, 10, 70, 20);
        server_ip.setBounds(75, 10, 200, 20);

        server_port_label.setBounds(10, 35, 70, 20);
        server_port.setBounds(75, 35, 200, 20);

        username_label.setBounds(10, 60, 70, 20);
        username.setBounds(75, 60, 200, 20);

        password_label.setBounds(10, 85, 70, 20);
        password.setBounds(75, 85, 200, 20);



        SaveButton.setBounds(280, 120, 50, 20);


        con.add(server_ip_label);
        con.add(server_ip);
        con.add(server_port_label);
        con.add(server_port);
        con.add(username_label);
        con.add(username);
        con.add(password_label);
        con.add(password);
        con.add(SaveButton);
        tabPane.add("自动化发布", con);// 添加布局1
        return tabPane;
    }
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(SaveButton)) {

        }
    }
}
