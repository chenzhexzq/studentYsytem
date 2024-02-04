package org.example.manageFrame;

import org.example.dao.JdbcDaoService;
import org.example.pojo.Users;
import org.example.utils.WindowUtil;

import javax.swing.*;
import java.awt.*;

public class ChangePasswordFrame extends JFrame {
    private JLabel lblUserPwd = new JLabel("新密码:"); //新密码标签
    private JLabel lblUserPwdR = new JLabel("确认密码:"); //确认密码标签
    private JTextField txtUserPwd = new JTextField(); //新密码文本输入框
    private JPasswordField txtUserPwdR = new JPasswordField(); //确认密码输入框
    private JButton btnChange = new JButton("修改"); //修改按钮
    private JButton btnClose = new JButton("关闭"); //关闭按钮
    private JFrame changepasswordFrame; //当前窗口界面
    private JdbcDaoService jdbcDaoService=new JdbcDaoService();
    private Component currDialog=null;

    public ChangePasswordFrame(Users users) {
        super("学生成绩管理系统--密码修改");
        this.setLayout(null); //设置为空布局
        this.setSize(400, 280); //设置界面大小
        this.setResizable(false); //设置窗体大小不可改变
        WindowUtil.setFrameCenter(this);//设置窗口居中
        this.setVisible(true); //设置当前窗体可见
        this.changepasswordFrame = this;
        Container container = this.getContentPane();
        container.setBackground(Color.WHITE);
        lblUserPwd .setBounds(100, 60, 50, 20);
        container.add(lblUserPwd );
        txtUserPwd.setBounds(160, 60, 120, 20);
        txtUserPwd.grabFocus();//获得光标
        container.add(txtUserPwd);
        //设置"密码"标签位置:坐标、宽度、高度
        lblUserPwdR.setBounds(100, 100, 60, 20);
        container.add(lblUserPwdR); //添加"密码"标签
        //设置"密码"文本域位置:坐标、宽度、高度
        txtUserPwdR.setBounds(160, 100, 120, 20);
        container.add(txtUserPwdR); //添加"密码"文本域
        btnChange.setBounds(80, 160, 60, 20);
        btnClose.setBounds(240, 160, 60, 20); //设置"注册"按钮位置
        container.add(btnChange);
        container.add(btnClose); //添加"关闭"按钮
        //注册"修改"按钮事件监听
        btnChange.addActionListener(actionEvent -> {
            String newPassword = txtUserPwd.getText().trim();
            String realPassword = new String(txtUserPwdR.getPassword());
            if(newPassword.equals(users.getUserpwd())){
                JOptionPane.showMessageDialog(currDialog,"新密码不能与旧密码相同！ ", "", JOptionPane.WARNING_MESSAGE);
                clearthing();
                return;
            }
            if(!newPassword.equals(realPassword)){
                JOptionPane.showMessageDialog(currDialog,"两次输入密码不同！ ", "", JOptionPane.WARNING_MESSAGE);
                clearthing();
                return;
            }
            if(newPassword.equals(realPassword)){
                //修改操作
                users.setUserpwd(realPassword);
                if(!jdbcDaoService.updatePassword(users)) {
                    JOptionPane.showMessageDialog(currDialog,"用户密码修改失败！ ", "", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                JOptionPane.showMessageDialog(currDialog, "用户密码修改成功！");
                clearthing();
            }
        });
        btnClose.addActionListener(actionEvent -> {
            changepasswordFrame.dispose();//关闭当前窗口
        });
    }
    public void clearthing(){
        txtUserPwd.setText("");
        txtUserPwdR.setText("");
    }
}
