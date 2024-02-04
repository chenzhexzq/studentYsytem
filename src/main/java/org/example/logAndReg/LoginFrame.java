package org.example.logAndReg;

import org.example.dao.JdbcDaoService;
import org.example.manageFrame.ManageSystemFrame;
import org.example.pojo.Users;
import org.example.studentFrame.StudentSystemFrame;
import org.example.utils.WindowUtil;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private JLabel lblUserName = new JLabel("用户名:"); //用户名标签
    private JLabel lblUserPwd = new JLabel("密 码:"); //密码标签
    private JTextField txtUserName = new JTextField(); //用户名文本输入框
    private JPasswordField txtUserPwd = new JPasswordField(); //密码输入框
    private JButton btnLogin = new JButton("登录"); //登陆按钮
    private JButton btnRegister = new JButton("注册"); //注册按钮
    private JButton btnClose = new JButton("关闭"); //关闭按钮
    private JFrame loginFrame; //当前窗口界面

    public LoginFrame() {
        super("学生成绩管理系统--系统登录");
        this.setLayout(null); //设置为空布局
        this.setSize(400, 280); //设置界面大小
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //关闭窗体同时停
        this.setResizable(false); //设置窗体大小不可改变
        WindowUtil.setFrameCenter(this);//设置窗口居中
        this.setVisible(true); //设置当前窗体可见
        this.loginFrame = this;
        Container container = this.getContentPane();
        container.setBackground(Color.WHITE); //设置背景颜色--白色
        //设置"用户名"标签位置:坐标、宽度、高度
        lblUserName.setBounds(100, 60, 50, 20);
        container.add(lblUserName); //添加"用户名"标签
        //设置"用户名"文本域位置:坐标、宽度、高度
        txtUserName.setBounds(160, 60, 120, 20);
        txtUserName.grabFocus();//获得光标
        container.add(txtUserName); //添加"用户名"文本域
        //设置"密码"标签位置:坐标、宽度、高度
        lblUserPwd.setBounds(100, 100, 50, 20);
        container.add(lblUserPwd); //添加"密码"标签
        //设置"密码"文本域位置:坐标、宽度、高度
        txtUserPwd.setBounds(160, 100, 120, 20);
        container.add(txtUserPwd); //添加"密码"文本域
        btnLogin.setBounds(80, 160, 60, 20); //设置"登录"按钮位置
        btnRegister.setBounds(160, 160, 60, 20); //设置"注册"按钮位置
        btnClose.setBounds(240, 160, 60, 20); //设置"注册"按钮位置
        container.add(btnLogin); //添加"登录"按钮
        container.add(btnRegister); //添加"注册"按钮
        container.add(btnClose); //添加"关闭"按钮
        //注册"登录"按钮事件监听
        btnLogin.addActionListener(actionEvent -> {
            String username = txtUserName.getText().trim();
            String userpwd =new String(txtUserPwd.getPassword());
           if (username.equals("")) {
                JOptionPane.showMessageDialog(loginFrame, "用户名不能为空!",
                        "", JOptionPane.WARNING_MESSAGE);
                return;
            }
            //登录处理
            Users user = new Users(); //创建用户对象
            user.setUsername(username);
            user.setUserpwd(userpwd);
            JdbcDaoService jdbcDaoService = new JdbcDaoService();//创建数据库业务处理对象
            int retCode = jdbcDaoService.loginService(user); //登录处理
            switch (retCode) {
                case -2:
                    JOptionPane.showMessageDialog(loginFrame, " 用户密码错误！");
                    txtUserName.setText("");
                    txtUserPwd.setText("");
                    break;
                case -1:
                    JOptionPane.showMessageDialog(loginFrame, " 用户名不存在！");
                    txtUserName.setText("");
                    txtUserPwd.setText("");
                    break;
                case 0:
                    //JOptionPane.showMessageDialog(loginFrame, "登录成功！");
                    Users users = jdbcDaoService.getUser(user);
                    String role = users.getRole();
                    loginFrame.dispose(); //关闭当前窗口。
                    if("0".equals(role)){
                        //打开主界面
                        ManageSystemFrame frame = new ManageSystemFrame(user);
                    }else if("1".equals(role)){
                        StudentSystemFrame studentSystemFrame = new StudentSystemFrame(user);
                    }
                    break;
            }
        });
        btnClose.addActionListener(actionEvent -> {
            loginFrame.dispose();//关闭当前窗口
        });
        btnRegister.addActionListener(actionEvent -> {
            RegisterFrame registerFrame = new RegisterFrame();
            loginFrame.dispose(); //关闭当前窗口。
        });
    }
}

