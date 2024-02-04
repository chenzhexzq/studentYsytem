package org.example.manageFrame;

import org.example.dao.JdbcDaoService;
import org.example.pojo.SelectByScale;
import org.example.utils.WindowUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class SelectByScales {
    JLabel classno = new JLabel("请输入要查询的专业班级:");
    JTextField classnotext = new JTextField(7);
    JLabel cname = new JLabel("请输入要查询的课程名称:");
    JTextField cnametext = new JTextField(7);
    JButton btSelect = new JButton("查询");
    private Vector<String> columnNames = null; //columnNames 存放列名
    private Vector<Vector<String>> rowData = null;//rowData 用来存放行数据
    public SelectByScales() {
        JFrame jf = new JFrame("按专业班级查询挂科学生的人数和比例");
        JPanel jp = new JPanel();
        jp.add(classno);
        jp.add(classnotext);
        jp.add(cname);
        jp.add(cnametext);
        jp.add(btSelect);
        jf.add(jp, BorderLayout.NORTH);
        btSelect.addActionListener(actionEvent -> {
            String classnotextText = classnotext.getText().trim();
            String cnametextText = cnametext.getText().trim();
            JdbcDaoService jdbcDaoService = new JdbcDaoService();
            Vector<SelectByScale> selectByScales= jdbcDaoService.selectByScales(classnotextText,cnametextText);
            columnNames = new Vector<String>();
            rowData = new Vector<Vector<String>>();
            columnNames.add("人数");
            columnNames.add("比例");
            for (SelectByScale selectByScale : selectByScales) {
                Vector<String> row = new Vector<String>();
                row.add(selectByScale.getPeople());
                row.add(selectByScale.getScale());
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
