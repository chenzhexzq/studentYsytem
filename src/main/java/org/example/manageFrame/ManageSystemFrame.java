package org.example.manageFrame;

import org.example.logAndReg.LoginFrame;
import org.example.pojo.Users;
import org.example.utils.WindowUtil;

import javax.swing.*;

public class ManageSystemFrame extends JFrame {
    private JMenuBar menuBar;
    private JMenu editData; //"编辑数据"菜单
    private JMenu queryData; //"查询数据"菜单
    private JMenu statisticalData; //"统计数据"菜单
    private JMenu systemManage; //"系统管理"菜单
    private JMenuItem studentInformationEditing; //"学生信息编辑"菜单项
    private JMenuItem courseInformationEditing; //"课程信息编辑"菜单项
    private JMenuItem courseSelectionInformationEditing; //"课程信息编辑"菜单项
    private JMenuItem selectCourseInformationByClass;//"按课号查课程信息"菜单项
    private JMenuItem selectStudentByClassNumber;//"按班级号查学生情况"菜单项
    private JMenuItem selectStudentBySno;//"按学号查询学生基本情况"菜单项
    private JMenuItem selectScoreNoGood;//"查课程不及格名单"菜单项
    private JMenuItem selectScoreGoodBySid;//"按学号查已修的学分数"菜单项
    private JMenuItem selectAllscoreBySid;//"按学号查各科成绩"菜单项
    private JMenuItem selectScoreNoGoodByClass; //" 按班级统计不及格人数及比例"菜单项
    private JMenuItem selectScorePeopleAndScaleByClass; //"按班级统计各分数段人数及比例"菜单项
    private JMenuItem selectAvgScore; //"各科的平均分数"菜单项
    private JMenuItem changePassword;//"修改密码"菜单项
    private JMenuItem exitSystem; //"退出系统"菜单项
    private JFrame mainFrame; //当前窗口
    public ManageSystemFrame(Users user){
        super("亲爱的用户"+user.getUsername()+"-欢迎使用学生管理系统-管理员");
        menuBar = new JMenuBar(); //创建菜单条
        this.setJMenuBar(menuBar); //添加菜单条
        editData = new JMenu("编辑数据"); //创建"编辑数据"菜单
                studentInformationEditing = new JMenuItem("学生信息编辑"); // 创建"学生信息编辑"菜单项
                courseInformationEditing = new JMenuItem("课程信息编辑"); // 创建"课程信息编辑"菜单项
                courseSelectionInformationEditing = new JMenuItem("成绩信息编辑");//创建"成绩信息编辑"菜单项
        editData.add(studentInformationEditing); //添加"学生信息录入"菜单项
        editData.add(courseInformationEditing); //添加"学生信息修改"菜单项
        editData.add(courseSelectionInformationEditing); //添加"学生信息删除"菜单项
        menuBar.add(editData); //添加"学生信息管理"菜单
        queryData = new JMenu("查询数据"); //创建"查询数据"菜单
        selectCourseInformationByClass = new JMenuItem("按课号查课程信息"); //创建"按课号查课程信息"菜单项
        selectStudentByClassNumber = new JMenuItem("按班级号查学生情况"); //创建"按班级号查学生情况"菜单项
        selectStudentBySno = new JMenuItem("按学号查询学生情况");////创建"按学号查学生情况"菜单项
        selectScoreNoGood = new JMenuItem("查课程不及格名单");//创建"查课程不及格名单"菜单项
        selectScoreGoodBySid = new JMenuItem("按学号查已修的学分");//创建"按学号查已修的学分数"菜单项
        selectAllscoreBySid = new JMenuItem("按学号查各科成绩");//创建"按学号查各科成绩"菜单项

                        queryData.add(selectCourseInformationByClass);//添加"按课号查课程信息"菜单项
        queryData.add(selectStudentByClassNumber);//添加"按班级号查学生情况"菜单项
        queryData.add(selectStudentBySno);//添加"按学号查学生情况"菜单项
        queryData.add(selectScoreNoGood); //添加"查课程不及格名单"菜单项
        queryData.add(selectScoreGoodBySid);//添加"按学号查已修的学分数"菜单项
        queryData.add(selectAllscoreBySid); //添加"按学号查各科成绩"菜单项
        menuBar.add(queryData); //添加"查询数据"菜单
        statisticalData = new JMenu("统计数据");
        selectScoreNoGoodByClass = new JMenuItem("按班级统计不及格人数及比例"); //创建"按班级统计不及格人数及比例"菜单项
        selectScorePeopleAndScaleByClass = new
                        JMenuItem("按班级统计各分数段人数及比例"); //创建"按班级统计各分数段人数及比例"菜单项
        selectAvgScore = new JMenuItem("各科的平均分数");//创建"各科的平均分数"菜单项

        statisticalData.add(selectScoreNoGoodByClass);

        statisticalData.add(selectScorePeopleAndScaleByClass);
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
        studentInformationEditing.addActionListener(actionEvent -> {
            EditStudentFrame editStudentFrame = new EditStudentFrame();
        });
        courseInformationEditing.addActionListener(actionEvent -> {
            EditCourseFrame editClassFrame=new EditCourseFrame();
        });
        courseSelectionInformationEditing.addActionListener(actionEvent -> {
            EditScFrame editScFrame = new EditScFrame();
        });
        exitSystem.addActionListener(actionEvent -> {
            mainFrame.dispose();//关闭当前窗口
            LoginFrame frame = new LoginFrame();
        });
        changePassword.addActionListener(actionEvent -> {
            ChangePasswordFrame changePasswordFrame = new ChangePasswordFrame(user);
        });
        selectCourseInformationByClass.addActionListener(actionEvent -> {
            SelectByCno selectByCno = new SelectByCno();
        });
        selectScoreNoGood.addActionListener(actionEvent -> {
            ScoreByNoGood score = new ScoreByNoGood();
        });
        selectStudentByClassNumber.addActionListener(actionEvent -> {
            SelectByClassNumber selectByClassNumber = new SelectByClassNumber();
        });
        selectScoreGoodBySid.addActionListener(actionEvent -> {
           SelectByCredits selectByCredits = new SelectByCredits();
        });
        selectAllscoreBySid.addActionListener(actionEvent -> {
           SelectScoreBySnos selectScoreBySnos = new SelectScoreBySnos();
        });
        selectScoreNoGoodByClass.addActionListener(actionEvent -> {
            SelectByScales selectByScales = new SelectByScales();
        });
        selectAvgScore.addActionListener(actionEvent -> {
            SelectByAvgScore selectByAvgScore = new SelectByAvgScore();
        });
        selectScorePeopleAndScaleByClass.addActionListener(actionEvent -> {
           SelectByRoundScales selectByRoundScales = new SelectByRoundScales();
        });
        selectStudentBySno.addActionListener(actionEvent -> {
            SelectStudentBySno studentBySno = new SelectStudentBySno();
        });
    }
}
