package org.example.studentFrame;

import org.example.logAndReg.LoginFrame;
import org.example.manageFrame.*;
import org.example.pojo.Users;
import org.example.utils.WindowUtil;

import javax.swing.*;

public class StudentSystemFrame extends JFrame{
    private JMenuBar menuBar;
    private JMenu editData; //"编辑数据"菜单
    private JMenu queryData; //"查询数据"菜单
    private JMenu statisticalData; //"统计数据"菜单
    private JMenu systemManage; //"系统管理"菜单
    private JMenuItem studentInformationEditing; //"学生信息编辑"菜单项
    private JMenuItem selectOwnInformation;//"查询学生本人信息"菜单项
    private JMenuItem selectCourseInformationByClass;//"按课号查课程信息"菜单项
    private JMenuItem selectScoreGoodBySid;//"按学号查已修的学分数"菜单项
    private JMenuItem selectAllscoreBySid;//"按学号查各科成绩"菜单项
    private JMenuItem selectAvgScore; //"各科的平均分数"菜单项
    private JMenuItem changePassword;//"修改密码"菜单项
    private JMenuItem exitSystem; //"退出系统"菜单项
    private JFrame mainFrame; //当前窗口
    public StudentSystemFrame(Users user){
        super("亲爱的用户"+user.getUsername()+"-欢迎使用学生管理系统-学生端");
        menuBar = new JMenuBar(); //创建菜单条
        this.setJMenuBar(menuBar); //添加菜单条
        editData = new JMenu("编辑数据"); //创建"编辑数据"菜单
        studentInformationEditing = new JMenuItem("学生信息编辑"); // 创建"学生信息编辑"菜单项
        editData.add(studentInformationEditing); //添加"学生信息录入"菜单项
        menuBar.add(editData); //添加"学生信息管理"菜单
        queryData = new JMenu("查询数据"); //创建"查询数据"菜单
        selectOwnInformation = new JMenuItem("查询本人信息");
        selectCourseInformationByClass = new JMenuItem("按课号查课程信息"); //创建"按课号查课程信息"菜单项
        selectScoreGoodBySid = new JMenuItem("查本人已修的学分");//创建"按学号查已修的学分数"菜单项
        selectAllscoreBySid = new JMenuItem("查本人各科成绩");//创建"按学号查各科成绩"菜单项

        queryData.add(selectCourseInformationByClass);//添加"按课号查课程信息"菜单项
        queryData.add(selectScoreGoodBySid);//添加"按学号查已修的学分数"菜单项
        queryData.add(selectAllscoreBySid); //添加"按学号查各科成绩"菜单项
        queryData.add(selectOwnInformation);
        menuBar.add(queryData); //添加"查询数据"菜单
        statisticalData = new JMenu("统计数据");
        selectAvgScore = new JMenuItem("考试平均分数");//创建"各科的平均分数"菜单项

        statisticalData.add(selectAvgScore);
        menuBar.add(statisticalData);
        systemManage = new JMenu("系统管理"); //创建"系统管理"菜单
        changePassword = new JMenuItem("修改密码");//创建"修改密码"菜单项
        exitSystem = new JMenuItem("退出系统"); //创建"退出系统"菜单项
        systemManage.add(changePassword); //添加"修改密码"菜单项
        systemManage.add(exitSystem); //添加"学生成绩修改"菜单项
        menuBar.add(systemManage); //添加"系统管理"菜单
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        WindowUtil.setFrameCenter(this);//设置窗体居中
        this.setVisible(true);//设置窗体可见。
        this.mainFrame = this;
        selectCourseInformationByClass.addActionListener(actionEvent -> {
            SelectByCno selectByCno = new SelectByCno();
        });
        selectScoreGoodBySid.addActionListener(actionEvent -> {
            selectBySno selectBySno = new selectBySno(user.getUsername());
        });
        selectAllscoreBySid.addActionListener(actionEvent -> {
           selectAllScoreBySno selectAllScoreBySno = new selectAllScoreBySno(user.getUsername());
        });
        changePassword.addActionListener(actionEvent -> {
            ChangePasswordFrame changePasswordFrame = new ChangePasswordFrame(user);
        });
        exitSystem.addActionListener(actionEvent -> {
            mainFrame.dispose();//关闭当前窗口
            LoginFrame frame = new LoginFrame();
        });
        selectAvgScore.addActionListener(actionEvent -> {
            selectAvgScoreBySno selectAvgScoreAndCreditBySno = new selectAvgScoreBySno(user.getUsername());
        });
        studentInformationEditing.addActionListener(actionEvent -> {
            updateStudent updateStudent = new updateStudent(user.getUsername());
        });
        selectOwnInformation.addActionListener(actionEvent -> {
            selectOwnInformation selectOwnInformation1 = new selectOwnInformation(user.getUsername());
        });
    }
}