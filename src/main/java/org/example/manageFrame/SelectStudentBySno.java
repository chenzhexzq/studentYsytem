package org.example.manageFrame;

import org.example.dao.JdbcDaoService;
import org.example.pojo.Student;
import org.example.utils.WindowUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class SelectStudentBySno extends JFrame {
    JLabel QSno = new JLabel("请输入要查询的起点学号:");
    JTextField QSnotext = new JTextField(7);
    JLabel ZSno = new JLabel("请输入要查询的终点学号:");
    JTextField ZSnotext = new JTextField(7);
    JButton btSelect = new JButton("查询");
    public SelectStudentBySno() {
        JFrame jf = new JFrame("按学号查询学生");
        JPanel jp = new JPanel();
        jp.add(QSno);
        jp.add(QSnotext);
        jp.add(ZSno);
        jp.add(ZSnotext);
        jp.add(btSelect);
        jf.add(jp, BorderLayout.NORTH);
        btSelect.addActionListener(actionEvent -> {
            Vector<String> columnNames =  new Vector<String>();
            Vector<Vector<String>> rowData = new Vector<Vector<String>>();
            String QSnotextText = QSnotext.getText().trim();
            String ZSnotestTest = ZSnotext.getText().trim();
            JdbcDaoService jdbcDaoService = new JdbcDaoService();
            if(!ZSnotestTest.equals("")) {
                Vector<Student> students = jdbcDaoService.selectStudentBySno(QSnotextText, ZSnotestTest);
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
                    JTable table = new JTable(rowData, columnNames);
                    JScrollPane jsp = new JScrollPane(table);
                    jf.add(jsp, BorderLayout.CENTER);
                    jf.setVisible(true);
                }
            }else {
                Student student = jdbcDaoService.selectSno(QSnotextText);
                String[] columns = {"学号","姓名","性别","年龄","系别","班级"};
                String sno = student.getSno();
                String sname = student.getSname();
                String sex = student.getSsex();
                String sage = student.getSage();
                String sdept = student.getSdept();
                String sclass = student.getClas();
                String[][]date = {
                        {sno,sname,sex,sage,sdept,sclass},
                };
                JTable table = new JTable(date, columns);
                JScrollPane jsp = new JScrollPane(table);
                jf.add(jsp, BorderLayout.CENTER);
                jf.setVisible(true);
            }
        });
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf.setSize(600, 500);
        WindowUtil.setFrameCenter(jf);
        jf.setVisible(true);
    }
}
