package org.example.studentFrame;

import org.example.dao.JdbcDaoService;
import org.example.pojo.Student;
import org.example.pojo.Users;
import org.example.utils.WindowUtil;

import javax.swing.*;
import java.awt.*;

public class updateStudent extends JFrame{
    private JLabel lblSname = new JLabel("姓名:"); //姓名标签
    private JLabel lblSex = new JLabel("性别:"); //性别标签
    private JLabel lblAge = new JLabel("年龄:");//年龄标签
    private JLabel lblDept= new JLabel("系别:");//系别标签
    private JLabel lblClass = new JLabel("班级:");//班级标签
    private JTextField txtSname = new JTextField(); //姓名文本输入框
    private JTextField txtSex = new JTextField(); //性别输入框
    private JTextField txtAge = new JTextField(); //年龄输入框
    private JTextField txtDept = new JTextField(); //系别输入框
    private JTextField txtClass = new JTextField(); //班级输入框
    private JButton btnChange = new JButton("修改"); //修改按钮
    private JButton btnClose = new JButton("关闭"); //关闭按钮
    private JFrame changepasswordFrame; //当前窗口界面
    private JdbcDaoService jdbcDaoService=new JdbcDaoService();
    private Component currDialog=null;

    public updateStudent(String sno) {
        super("学生成绩管理系统--学生信息修改");
        this.setLayout(null); //设置为空布局
        this.setSize(600, 480); //设置界面大小
        this.setResizable(false); //设置窗体大小不可改变
        WindowUtil.setFrameCenter(this);//设置窗口居中
        this.setVisible(true); //设置当前窗体可见
        this.changepasswordFrame = this;
        Container container = this.getContentPane();
        container.setBackground(Color.WHITE);
        lblSname .setBounds(200, 60, 50, 20);
        container.add(lblSname );
        txtSname.setBounds(250, 60, 120, 20);
        txtSname.grabFocus();//获得光标
        container.add(txtSname);
        //
        lblSex.setBounds(200, 100, 60, 20);
        container.add(lblSex); //
        //
        txtSex.setBounds(250, 100, 120, 20);
        txtSex.grabFocus();
        container.add(txtSex);
        //
        lblAge.setBounds(200,140,50,20);
        container.add(lblAge);
        //
        txtAge.setBounds(250,140,120,20);
        txtAge.grabFocus();
        container.add(txtAge);
        //
        lblDept.setBounds(200,180,50,20);
        container.add(lblDept);
        txtDept.setBounds(250,180,120,20);
        txtDept.grabFocus();
        container.add(txtDept);
        //
        lblClass.setBounds(200,220,50,20);
        container.add(lblClass);
        txtClass.setBounds(250,220,120,20);
        txtClass.grabFocus();
        container.add(txtClass);
        btnChange.setBounds(180, 280, 60, 20);
        btnClose.setBounds(340, 280, 60, 20);
        container.add(btnChange);
        container.add(btnClose); //添加"关闭"按钮
        //注册"修改"按钮事件监听
        btnChange.addActionListener(actionEvent -> {
            String txtsname = txtSname.getText().trim();
            String txtsex = txtSex.getText().trim();
            String txtage = txtAge.getText().trim();
            String txtdept = txtDept.getText().trim();
            String txtclass= txtClass.getText().trim();
            Student student = new Student();
            student.setSno(sno);
            student.setSname(txtsname);
            student.setSsex(txtsex);
            student.setSage(txtage);
            student.setSdept(txtdept);
            student.setClas(txtclass);
                //修改操作
            if(!jdbcDaoService.updateStudent(student)) {
                JOptionPane.showMessageDialog(currDialog,"学生信息修改失败！ ", "", JOptionPane.WARNING_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(currDialog, "学生信息修改成功！");
            clearthing();
        });
        btnClose.addActionListener(actionEvent -> {
            changepasswordFrame.dispose();//关闭当前窗口
        });
    }
    public void clearthing(){
        txtSname.setText("");
        txtSex.setText("");
        txtAge.setText("");
        txtDept.setText("");
        txtClass.setText("");
    }
}
