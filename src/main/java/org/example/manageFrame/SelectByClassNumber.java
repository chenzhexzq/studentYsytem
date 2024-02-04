package org.example.manageFrame;

import org.example.dao.JdbcDaoService;
import org.example.pojo.Student;
import org.example.utils.WindowUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class SelectByClassNumber extends JFrame {
    JLabel classno = new JLabel("请输入要查询的专业班级:");
    JTextField classnotext = new JTextField(7);
    JButton btSelect = new JButton("查询");
    private Vector<String> columnNames = null; //columnNames 存放列名
    private Vector<Vector<String>> rowData = null;//rowData 用来存放行数据
    public SelectByClassNumber() {
        JFrame jf = new JFrame("按专业班级查询学生");
        JPanel jp = new JPanel();
        jp.add(classno);
        jp.add(classnotext);
        jp.add(btSelect);
        jf.add(jp, BorderLayout.NORTH);
        btSelect.addActionListener(actionEvent -> {
            String classnotextText = classnotext.getText().trim();
            JdbcDaoService jdbcDaoService = new JdbcDaoService();
            Vector<Student> students = jdbcDaoService.selectStuByClas(classnotextText);
            columnNames = new Vector<String>();
            rowData = new Vector<Vector<String>>();
            columnNames.add("学号");
            columnNames.add("姓名");
            columnNames.add("性别");
            columnNames.add("年龄");
            columnNames.add("系别");
            columnNames.add("班级");
            for (Student student : students) {
                Vector<String> row = new Vector<String>();
                row.add(student.getSno());
                row.add(student.getSname());
                row.add(student.getSsex());
                row.add(student.getSage());
                row.add(student.getSdept());
                row.add(student.getClas());
                rowData.add(row);
            }
            JTable table = new JTable(rowData, columnNames);
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

