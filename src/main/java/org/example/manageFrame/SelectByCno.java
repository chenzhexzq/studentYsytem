package org.example.manageFrame;

import org.example.dao.JdbcDaoService;
import org.example.pojo.Course;
import org.example.utils.WindowUtil;

import javax.swing.*;
import java.awt.*;


public class SelectByCno {
    //这一块是 JFrame 北部的部分
    //信息的输入
    JLabel cno = new JLabel("请输入要查询的课程代码:");
    JTextField cnotext = new JTextField(5);
    JButton btSelect = new JButton("查询");
    public SelectByCno(){
        JFrame jf = new JFrame("课程信息查询");
        JPanel jp = new JPanel();
        jp.add(cno);
        jp.add(cnotext);
        jp.add(btSelect);
        jf.add(jp, BorderLayout.NORTH);
        btSelect.addActionListener(actionEvent -> {
            String cnotextText = cnotext.getText().trim();
            JdbcDaoService jdbcDaoService = new JdbcDaoService();
            Course course = jdbcDaoService.selectByCno(cnotextText);
            String[] columnNames = {"课程代码","课程名称","教师名称","学分"};
            String cno = course.getCno();
            String cname = course.getCname();
            String teacher = course.getTeacher();
            String credit = course.getCredit();
            String[][]date = {
                    {cno,cname,teacher,credit},
            };
            JTable table = new JTable(date,columnNames);
            JScrollPane jsp = new JScrollPane(table);
            jf.add(jsp, BorderLayout.CENTER);
            jf.setVisible(true);
        });

        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf.setSize(600, 500);
        WindowUtil.setFrameCenter(jf);
        jf.setVisible(true);
    }
}
