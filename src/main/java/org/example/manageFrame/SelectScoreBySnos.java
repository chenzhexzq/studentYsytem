package org.example.manageFrame;

import org.example.dao.JdbcDaoService;
import org.example.pojo.SelectScoreBySno;
import org.example.utils.WindowUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class SelectScoreBySnos {
    JLabel sno = new JLabel("请输入要查询的学生学号:");
    JTextField snotext = new JTextField(10);
    JButton btSelect = new JButton("查询");
    private Vector<String> columnNames = null; //columnNames 存放列名
    private Vector<Vector<String>> rowData = null;//rowData 用来存放行数据
    public SelectScoreBySnos(){
        JFrame jf = new JFrame("按学号查各科成绩");
        JPanel jp = new JPanel();
        jp.add(sno);
        jp.add(snotext);
        jp.add(btSelect);
        jf.add(jp, BorderLayout.NORTH);
        btSelect.addActionListener(actionEvent -> {
            String snotextText = snotext.getText().trim();
            JdbcDaoService jdbcDaoService = new JdbcDaoService();
            Vector<SelectScoreBySno> ssbs = jdbcDaoService.selectScoreBySnos(snotextText);
            columnNames = new Vector<String>();
            rowData = new Vector<Vector<String>>();
            columnNames.add("学生学号");
            columnNames.add("学生姓名");
            columnNames.add("课程名称");
            columnNames.add("考试成绩");
            for (SelectScoreBySno sbc : ssbs) {
                Vector<String> row = new Vector<String>();
                row.add(sbc.getSno());
                row.add(sbc.getSname());
                row.add(sbc.getCname());
                row.add(sbc.getScore());
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
