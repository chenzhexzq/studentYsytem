package org.example.manageFrame;

import org.example.dao.JdbcDaoService;
import org.example.pojo.SelectByNoGood;
import org.example.utils.WindowUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class ScoreByNoGood {
    //这一块是 JFrame 北部的部分
    //信息的输入
    JLabel cname = new JLabel("请输入要查询的课程名称:");
    JTextField cnametext = new JTextField(7);
    JButton btSelect = new JButton("查询");
    private Vector<String> columnNames = null; //columnNames 存放列名
    private Vector<Vector<String>> rowData = null;//rowData 用来存放行数据
    public ScoreByNoGood(){
        JFrame jf = new JFrame("挂科信息查询");
        JPanel jp = new JPanel();
        jp.add(cname);
        jp.add(cnametext);
        jp.add(btSelect);
        jf.add(jp, BorderLayout.NORTH);
        btSelect.addActionListener(actionEvent -> {
            String cnametextText = cnametext.getText().trim();
            JdbcDaoService jdbcDaoService = new JdbcDaoService();
            Vector<SelectByNoGood> sbngs = jdbcDaoService.selectByNoGoods(cnametextText);
            columnNames = new Vector<String>();
            rowData = new Vector<Vector<String>>();
            columnNames.add("课程名称");
            columnNames.add("课程代码");
            columnNames.add("学生学号");
            columnNames.add("学生姓名");
            columnNames.add("考试成绩");
            for(SelectByNoGood sbng : sbngs){
               Vector<String> row = new Vector<String>();
               row.add(sbng.getSno());
               row.add(sbng.getSname());
               row.add(sbng.getCno());
               row.add(sbng.getCname());
               row.add(sbng.getScore());
               rowData.add(row);
            }
            JTable table = new JTable(rowData,columnNames);
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
