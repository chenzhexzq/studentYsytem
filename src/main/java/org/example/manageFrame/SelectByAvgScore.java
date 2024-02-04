package org.example.manageFrame;

import org.example.dao.JdbcDaoService;
import org.example.pojo.SelectAvgScore;
import org.example.utils.WindowUtil;

import javax.swing.*;
import java.awt.*;

public class SelectByAvgScore {
    JLabel cname = new JLabel("请输入要查询的课程名称:");
    JTextField cnametext = new JTextField(7);
    JButton btSelect = new JButton("查询");
    public SelectByAvgScore(){
        JFrame jf = new JFrame("课程信息查询");
        JPanel jp = new JPanel();
        jp.add(cname);
        jp.add(cnametext);
        jp.add(btSelect);
        jf.add(jp, BorderLayout.NORTH);
        btSelect.addActionListener(actionEvent -> {
            String cnametextText = cnametext.getText().trim();
            JdbcDaoService jdbcDaoService = new JdbcDaoService();
            SelectAvgScore selectAvgScore = jdbcDaoService.selectAvgScore(cnametextText);
            String[] columnNames = {"课程名称","课程代码","平均分数"};
            String cname=selectAvgScore.getCname();
            String cno=selectAvgScore.getCno();
            String score=selectAvgScore.getScore();
            String[][]date = {
                    {cname,cno,score},
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
