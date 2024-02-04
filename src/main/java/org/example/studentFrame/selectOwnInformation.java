package org.example.studentFrame;

import org.example.dao.JdbcDaoService;
import org.example.pojo.Course;
import org.example.pojo.Student;
import org.example.utils.WindowUtil;

import javax.swing.*;
import java.awt.*;

public class selectOwnInformation {
    JButton btSelect = new JButton("查询");
    public selectOwnInformation(String sno){
        JFrame jf = new JFrame("学生信息查询");
        JPanel jp = new JPanel();
        jp.add(btSelect);
        jf.add(jp, BorderLayout.NORTH);
        btSelect.addActionListener(actionEvent -> {
            JdbcDaoService jdbcDaoService = new JdbcDaoService();
            Student student = jdbcDaoService.selectSno(sno);
            String[] columns = {"学号","姓名","性别","年龄","系别","班级"};
            String Sno = student.getSno();
            String sname = student.getSname();
            String sex = student.getSsex();
            String sage = student.getSage();
            String sdept = student.getSdept();
            String sclass = student.getClas();
            String[][]date = {
                    {Sno,sname,sex,sage,sdept,sclass},
            };
            JTable table = new JTable(date, columns);
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
