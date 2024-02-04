package org.example.manageFrame;

import org.example.dao.JdbcDaoService;
import org.example.model.ScModel;
import org.example.pojo.Sc;
import org.example.utils.WindowUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EditScFrame {
    //这一块是 JFrame 北部的部分
    //信息的输入
    JLabel sno = new JLabel("学号:");
    JTextField snotext = new JTextField(7);
    JLabel cno = new JLabel("课程代码:");
    JTextField cnotext = new JTextField(5);
    JLabel score = new JLabel("成绩:");
    JTextField scoretext = new JTextField(2);
    //下部的按钮
    JButton btchange = new JButton("修改");
    JButton btadd = new JButton("添加");
    JButton btdelete = new JButton("删除");

    public EditScFrame() {
        JFrame jf = new JFrame("表格");
        JPanel jp = new JPanel();
        jp.add(sno);
        jp.add(snotext);
        jp.add(cno);
        jp.add(cnotext);
        jp.add(score);
        jp.add(scoretext);
        jf.add(jp, BorderLayout.NORTH);
        //这一块是中间的表格
        String sql = "select * from sc"; //查询全部内容
        ScModel scModel = new ScModel(sql, new JDialog()); //构建新的数据模型类，并更新
        JTable table_want = new JTable();
        table_want.setModel(scModel);
        //支持滚动
        JScrollPane jsp = new JScrollPane(table_want);
        jf.add(jsp, BorderLayout.CENTER);
        //这一块是南边的按钮区域
        JPanel jp2 = new JPanel();
        jp2.add(btchange);
        jp2.add(btadd);
        jp2.add(btdelete);
        jf.add(jp2, BorderLayout.SOUTH);
        JdbcDaoService jdbcDaoService = new JdbcDaoService();
        Component currDialog = null;

        //执行添加操作
        btadd.addActionListener(e -> {
            //行数和列数 从 0 开始
            //获取信息
            String snotextText = snotext.getText().trim();
            String cnotextText = cnotext.getText().trim();
            String scoretextText = scoretext.getText().trim();
            Sc sc = new Sc();
            sc.setSno(snotextText);
            sc.setCno(cnotextText);
            sc.setScore(scoretextText);
            if (!jdbcDaoService.addSc(sc)) {
                JOptionPane.showMessageDialog(currDialog, "成绩信息录入失败！ ", "", JOptionPane.WARNING_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(currDialog, "成绩信息录入成功！");
            clearthing();
            String sql1 = "select * from sc"; //查询全部内容
            ScModel scModel1 = new ScModel(sql1, new JDialog());//构建新的数据模型类，并更新
            table_want.setModel(scModel1);
        });

        table_want.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (table_want.getValueAt(table_want.getSelectedRow(), 0) != null) {
                    String s1 = (String)
                            table_want.getValueAt(table_want.getSelectedRow(), 0);
                    snotext.setText(s1);
                    String s2 = (String)
                            table_want.getValueAt(table_want.getSelectedRow(), 1);
                    cnotext.setText(s2);
                    String s3 = (String)
                            table_want.getValueAt(table_want.getSelectedRow(), 2);
                    scoretext.setText(s3);
                }
            }
        });
        table_want.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (table_want.getValueAt(table_want.getSelectedRow(), 0) != null) {
                    // 提取所点击行的数据，并显示在文本框中
                    String s1 = (String) table_want.getValueAt(table_want.getSelectedRow(),
                            0);
                    snotext.setText(s1);
                    String s2 = (String) table_want.getValueAt(table_want.getSelectedRow(),
                            1);
                    cnotext.setText(s2);
                    String s3 = (String) table_want.getValueAt(table_want.getSelectedRow(),
                            2);
                    scoretext.setText(s3);
                }
            }
        });
        // 修改按钮的点击事件
        btchange.addActionListener(actionEvent -> {
            // 获取文本框中的修改后的值
            String snotextText = snotext.getText().trim();
            String cnotextText = cnotext.getText().trim();
            String scoretextText = scoretext.getText().trim();
            // 执行数据库更新操作，根据所选行的学号进行更新
            Sc sc = new Sc();
            sc.setSno(snotextText);
            sc.setCno(cnotextText);
            sc.setScore(scoretextText);
            if (!jdbcDaoService.updateSc(sc)) {
                JOptionPane.showMessageDialog(currDialog, "成绩信息修改失败！ ", "", JOptionPane.WARNING_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(currDialog, "成绩信息修改成功！");
            clearthing();
            // 更新表格数据显示
            String sql1 = "select * from sc"; //查询全部内容
            ScModel scModel1 = new ScModel(sql1, new JDialog());//构建新的数据模型类，并更新
            table_want.setModel(scModel1);
        });

        // 删除按钮的点击事件
        btdelete.addActionListener(actionEvent -> {
            // 获取当前所选行的课程代码
            String selectedSnoId = (String) table_want.getValueAt(table_want.getSelectedRow(), 0);
            String selectedCnoId = (String) table_want.getValueAt(table_want.getSelectedRow(), 1);
            System.out.println(selectedSnoId);
            System.out.println(selectedCnoId);
            // 执行数据库删除操作，根据学号进行删除
            if(!jdbcDaoService.deleteSc(selectedSnoId,selectedCnoId)) {
                JOptionPane.showMessageDialog(currDialog,"成绩信息删除失败！ ", "", JOptionPane.WARNING_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(currDialog, "成绩信息删除成功！");
            clearthing();
            String sql1 = "select * from sc"; //查询全部内容
            ScModel scModel1 = new ScModel(sql1, new JDialog());//构建新的数据模型类，并更新
            table_want.setModel(scModel1);
        });

        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf.setSize(600, 500);
        WindowUtil.setFrameCenter(jf);
        jf.setVisible(true);
    }

    public void clearthing(){
        snotext.setText("");
        cnotext.setText("");
        scoretext.setText("");
    }
}
