package com.generalsoft.singman.ui;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.Image;

/**
 * Created with IntelliJ IDEA.
 * User: singman
 * Date: 14-8-6
 * Time: 上午9:14
 * To change this template use File | Settings | File Templates.
 */
public  class  MainFrame  implements ActionListener   {

    JFrame frame = new JFrame("OpsPlat运维平台");// 框架布局
    JTabbedPane tabPane = new JTabbedPane();// 选项卡布局

    JMenu help=new JMenu("帮助") ;     //创建JMenu菜单对象
    JMenuItem about=new JMenuItem("关于") ;//菜单项
    JMenuBar  menuBar=new  JMenuBar() ;  //创建菜单工具栏

    public    MainFrame() {
        double lx = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double ly = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        frame.setLocation(new Point((int) (lx / 2) - 150, (int) (ly / 2) - 150));// 设定窗口出现位置
        frame.setSize(480, 370);// 设定窗口大小
        frame.setResizable(false); //禁止最大化

        Image icon = Toolkit.getDefaultToolkit().getImage("");  //去掉左上角java图标
        frame.setIconImage(icon);
        frame.setContentPane(tabPane);// 设置布局

        //添加按钮事件监听
        about.addActionListener(this);

        help.add(about) ;    //将菜单项目添加到菜单
        menuBar.add(help) ;      //将菜单增加到菜单工具栏
        frame.setJMenuBar(menuBar) ;  //为 窗体设置  菜单工具栏

        frame.setVisible(true);// 窗口可见
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 使能关闭窗口，结束程序
        AutoDeploymentFrame autoDeployment = new AutoDeploymentFrame();
        tabPane.add("自动化发布", autoDeployment.AutoDeploymentFrame());// 添加布局1

        AppLogViewFrame logView = new AppLogViewFrame();
        tabPane.add("日志截取",logView.AppLogViewFrame());
        //SystemParmFrame sysPlane =  new SystemParmFrame();
        //tabPane.add("系统配置",sysPlane.SystemParmFrame());

    }
    /**
     * 事件监听的方法
     */
    public void actionPerformed (ActionEvent e) {
        if (e.getSource().equals(about)) {
            JOptionPane.showMessageDialog(frame, "OpsPlat运维平台 V0.1 alpha", "关于", 2);
        }

    }

    public static void main(String[] args){
        new  MainFrame();
    }
}


