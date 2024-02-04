package org.example.manageFrame;

import org.example.dao.JdbcDaoService;
import org.example.model.CourseModel;
import org.example.pojo.Course;
import org.example.utils.WindowUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EditCourseFrame {
    //这一块是 JFrame 北部的部分
    //信息的输入
    JLabel cno = new JLabel("课程代码:");
    JTextField cnotext = new JTextField(5);
    JLabel cname = new JLabel("课程名称:");
    JTextField cnametext = new JTextField(5);
    JLabel teacher = new JLabel("教师姓名:");
    JTextField teachertext = new JTextField(5);
    JLabel credit = new JLabel("学分:");
    JTextField credittext = new JTextField(2);
    //下部的按钮
    JButton btchange = new JButton("修改");
    JButton btadd = new JButton("添加");
    JButton btdelete = new JButton("删除");
    public EditCourseFrame() {
        JFrame jf = new JFrame("表格");
        JPanel jp = new JPanel();
        jp.add(cno);
        jp.add(cnotext);
        jp.add(cname);
        jp.add(cnametext);
        jp.add(teacher);
        jp.add(teachertext);
        jp.add(credit);
        jp.add(credittext);
        jf.add(jp, BorderLayout.NORTH);
        //这一块是中间的表格
        String sql = "select * from course"; //查询全部内容
        CourseModel courseModel = new CourseModel(sql, new JDialog()); //构建新的数据模型类，并更新
        JTable table_want = new JTable();
        table_want.setModel(courseModel);
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
            String cnotextText = cnotext.getText().trim();
            String cnametextText = cnametext.getText().trim();
            String teachertextText = teachertext.getText().trim();
            String credittextText = credittext.getText().trim();
            Course course = new Course();
            course.setCno(cnotextText);
            course.setCname(cnametextText);
            course.setTeacher(teachertextText);
            course.setCredit(credittextText);
            if (!jdbcDaoService.addCourse(course)) {
                JOptionPane.showMessageDialog(currDialog, "课程信息录入失败！ ", "", JOptionPane.WARNING_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(currDialog, "课程信息录入成功！");
            clearthing();
            String sql1 = "select * from course"; //查询全部内容
            CourseModel courseModel1 = new CourseModel(sql1, new JDialog());//构建新的数据模型类，并更新
            table_want.setModel(courseModel1);
        });

        table_want.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (table_want.getValueAt(table_want.getSelectedRow(), 0) != null) {
                    String s1 = (String)
                            table_want.getValueAt(table_want.getSelectedRow(), 0);
                    cnotext.setText(s1);
                    String s2 = (String)
                            table_want.getValueAt(table_want.getSelectedRow(), 1);
                    cnametext.setText(s2);
                    String s3 = (String)
                            table_want.getValueAt(table_want.getSelectedRow(), 2);
                    teachertext.setText(s3);
                    String s4 = (String)
                            table_want.getValueAt(table_want.getSelectedRow(), 3);
                    credittext.setText(s4);
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
                    cnotext.setText(s1);
                    String s2 = (String) table_want.getValueAt(table_want.getSelectedRow(),
                            1);
                    cnametext.setText(s2);
                    String s3 = (String) table_want.getValueAt(table_want.getSelectedRow(),
                            2);
                    teachertext.setText(s3);
                    String s4 = (String) table_want.getValueAt(table_want.getSelectedRow(),
                            3);
                    credittext.setText(s4);
                }
            }
        });
        // 修改按钮的点击事件
        btchange.addActionListener(actionEvent -> {
            // 获取文本框中的修改后的值
            String cnotextText = cnotext.getText().trim();
            String cnametextText = cnametext.getText().trim();
            String teachertextText = teachertext.getText().trim();
            String credittextText = credittext.getText().trim();
            // 执行数据库更新操作，根据所选行的学号进行更新
            Course course = new Course();
            course.setCno(cnotextText);
            course.setCname(cnametextText);
            course.setTeacher(teachertextText);
            course.setCredit(credittextText);
            if (!jdbcDaoService.updateCourse(course)) {
                JOptionPane.showMessageDialog(currDialog, "课程信息修改失败！ ", "", JOptionPane.WARNING_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(currDialog, "课程信息修改成功！");
            clearthing();
            // 更新表格数据显示
            String sql1 = "select * from course";
            CourseModel courseModel1 = new CourseModel(sql1, new JDialog());//构建新的数据模型类，并更新
            table_want.setModel(courseModel1);
        });

        // 删除按钮的点击事件
        btdelete.addActionListener(actionEvent -> {
            // 获取当前所选行的课程代码
            String selectedCourseId = (String) table_want.getValueAt(table_want.getSelectedRow(), 0);
            // 执行数据库删除操作，根据学号进行删除
            if(!jdbcDaoService.deleteCourse(selectedCourseId)) {
                JOptionPane.showMessageDialog(currDialog,"课程信息删除失败！ ", "", JOptionPane.WARNING_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(currDialog, "课程信息删除成功！");
            clearthing();
            // 更新表格数据显示
            String sql1 = "select * from course";
            CourseModel courseModel1 = new CourseModel(sql1, new JDialog());//构建新的数据模型类，并更新
            table_want.setModel(courseModel1);
        });

            jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            jf.setSize(600, 500);
            WindowUtil.setFrameCenter(jf);
            jf.setVisible(true);
        }
    public void clearthing(){
        cnotext.setText("");
        cnametext.setText("");
        teachertext.setText("");
        credittext.setText("");
    }
}
