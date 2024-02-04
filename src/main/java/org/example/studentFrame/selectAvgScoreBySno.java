package org.example.studentFrame;

import org.example.dao.JdbcDaoService;
import org.example.pojo.SelectAvgScore;
import org.example.pojo.selectAvgscoreBySno;
import org.example.utils.WindowUtil;

import javax.swing.*;
import java.awt.*;

public class selectAvgScoreBySno {
    JButton btSelect = new JButton("查询");
    public selectAvgScoreBySno(String sno){
        JFrame jf = new JFrame("课程信息查询");
        JPanel jp = new JPanel();
        jp.add(btSelect);
        jf.add(jp, BorderLayout.NORTH);
        btSelect.addActionListener(actionEvent -> {
            JdbcDaoService jdbcDaoService = new JdbcDaoService();
            selectAvgscoreBySno selectAvgscoreBySno= jdbcDaoService.selectAvgscoreBySno(sno);
            String[] columnNames = {"学生学号","学生姓名","考试平均分数"};
            String Sno=selectAvgscoreBySno.getSno();
            String Sname=selectAvgscoreBySno.getSname();
            String avgScore=selectAvgscoreBySno.getAvgScore();
            String[][]date = {
                    {Sno,Sname,avgScore},
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
