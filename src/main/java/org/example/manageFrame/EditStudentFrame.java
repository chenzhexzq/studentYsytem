package org.example.manageFrame;

import org.example.dao.JdbcDaoService;
import org.example.model.StudentModel;
import org.example.pojo.Student;
import org.example.utils.WindowUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EditStudentFrame {
    //这一块是 JFrame 北部的部分
    //信息的输入
    JLabel stuid = new JLabel("学号:");
    JTextField stuidtext = new JTextField(5);
    JLabel name = new JLabel("姓名:");
    JTextField nametext = new JTextField(3);
    JLabel sex = new JLabel("性别:");
    JTextField sextext = new JTextField(2);
    JLabel age = new JLabel("年龄:");
    JTextField agetext = new JTextField(2);
    JLabel department = new JLabel("系别:");
    JTextField departmenttext = new JTextField(5);
    JLabel Class = new JLabel("专业班级:");
    JTextField Classtext = new JTextField(4);
    //下部的按钮
    JButton btchange = new JButton("修改");
    JButton btadd = new JButton("添加");
    JButton btdelete = new JButton("删除");
    public EditStudentFrame(){
        JFrame jf = new JFrame("表格");
        JPanel jp = new JPanel();
        jp.add(stuid);
        jp.add(stuidtext);
        jp.add(name);
        jp.add(nametext);
        jp.add(sex);
        jp.add(sextext);
        jp.add(age);
        jp.add(agetext);
        jp.add(department);
        jp.add(departmenttext);
        jp.add(Class);
        jp.add(Classtext);
        jf.add(jp, BorderLayout.NORTH);
        //这一块是中间的表格
        String sql = "select * from student"; //查询全部内容
        StudentModel studentModel = new StudentModel(sql, new JDialog()); //构建新的数据模型类，并更新
        JTable table_want = new JTable();
        table_want.setModel(studentModel);
        //支持滚动
        JScrollPane jsp = new JScrollPane(table_want);
        jf.add(jsp,BorderLayout.CENTER);
        //这一块是南边的按钮区域
        JPanel jp2 = new JPanel();
        jp2.add(btchange);
        jp2.add(btadd);
        jp2.add(btdelete);
        jf.add(jp2,BorderLayout.SOUTH);
        JdbcDaoService jdbcDaoService=new JdbcDaoService();
        Component currDialog=null;
        //执行添加操作
        btadd.addActionListener(e -> {
            //行数和列数 从 0 开始
            //获取信息
            String stuidtextText = stuidtext.getText().trim();
            String nametextText = nametext.getText().trim();
            String sextextText = sextext.getText().trim();
            String agetextText = agetext.getText().trim();
            String departmenttextText = departmenttext.getText().trim();
            String ClasstextText = Classtext.getText().trim();
            Student student=new Student();
            student.setSno(stuidtextText);
            student.setSname(nametextText);
            student.setSage(agetextText);
            student.setSsex(sextextText);
            student.setSdept(departmenttextText);
            student.setClas(ClasstextText);
            if(!jdbcDaoService.addStudent(student)) {
                JOptionPane.showMessageDialog(currDialog,"学生信息录入失败！ ", "", JOptionPane.WARNING_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(currDialog, "学生信息录入成功！");
            clearthing();
            String sql1 = "select * from student"; //查询全部内容
            StudentModel studentModel1 = new StudentModel(sql1, new JDialog());//构建新的数据模型类，并更新
            table_want.setModel(studentModel1);
        });

        table_want.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(table_want.getValueAt(table_want.getSelectedRow(),0)!=null){
                    String s1 = (String)
                            table_want.getValueAt(table_want.getSelectedRow(),0);
                    stuidtext.setText(s1);
                    String s2 = (String)
                            table_want.getValueAt(table_want.getSelectedRow(),1);
                    nametext.setText(s2);
                    String s3 = (String)
                            table_want.getValueAt(table_want.getSelectedRow(),2);
                    sextext.setText(s3);
                    String s4 = (String)
                            table_want.getValueAt(table_want.getSelectedRow(),3);
                    agetext.setText(s4);
                    String s5 = (String)
                            table_want.getValueAt(table_want.getSelectedRow(),4);
                    departmenttext.setText(s5);
                    String s6 = (String)
                            table_want.getValueAt(table_want.getSelectedRow(),5);
                    Classtext.setText(s6);
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
                    stuidtext.setText(s1);
                    String s2 = (String) table_want.getValueAt(table_want.getSelectedRow(),
                            1);
                    nametext.setText(s2);
                    String s3 = (String) table_want.getValueAt(table_want.getSelectedRow(),
                            2);
                    sextext.setText(s3);
                    String s4 = (String) table_want.getValueAt(table_want.getSelectedRow(),
                            3);
                    agetext.setText(s4);
                    String s5 = (String) table_want.getValueAt(table_want.getSelectedRow(),
                            4);
                    departmenttext.setText(s5);
                    String s6 = (String) table_want.getValueAt(table_want.getSelectedRow(),
                            5);
                    Classtext.setText(s6);
                }
            }
        });
        // 修改按钮的点击事件
        btchange.addActionListener(actionEvent -> {
            // 获取文本框中的修改后的值
            String stuidtextText = stuidtext.getText().trim();
            String nametextText = nametext.getText().trim();
            String sextextText = sextext.getText().trim();
            String agetextText = agetext.getText().trim();
            String departmenttextText = departmenttext.getText().trim();
            String ClasstestTest = Classtext.getText().trim();
            // 执行数据库更新操作，根据所选行的学号进行更新
            Student student=new Student();
            student.setSno(stuidtextText);
            student.setSname(nametextText);
            student.setSage(agetextText);
            student.setSsex(sextextText);
            student.setSdept(departmenttextText);
            student.setClas(ClasstestTest);
            if(!jdbcDaoService.updateStudent(student)) {
                JOptionPane.showMessageDialog(currDialog,"学生信息修改失败！ ", "", JOptionPane.WARNING_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(currDialog, "学生信息修改成功！");
            clearthing();
            // 更新表格数据显示
            String sql1 = "select * from student";
            StudentModel studentModel1 = new StudentModel(sql1, new JDialog());
            table_want.setModel(studentModel1);
        });
        // 删除按钮的点击事件
        btdelete.addActionListener(actionEvent -> {
            // 获取当前所选行的学号
            String selectedStudentId = (String) table_want.getValueAt(table_want.getSelectedRow(), 0);
            // 执行数据库删除操作，根据学号进行删除
            if(!jdbcDaoService.deleteStudent(selectedStudentId)) {
                JOptionPane.showMessageDialog(currDialog,"学生信息删除失败！ ", "", JOptionPane.WARNING_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(currDialog, "学生信息删除成功！");
            clearthing();
            // 更新表格数据显示
            String sql1 = "select * from student";
            StudentModel studentModel1 = new StudentModel(sql1, new JDialog());
            table_want.setModel(studentModel1);
        });

        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf.setSize(600,500);
        WindowUtil.setFrameCenter(jf);
        jf.setVisible(true);
    }
    //这个方法是用来清除掉输入框里的文本
    public void clearthing(){
        stuidtext.setText("");
        nametext.setText("");
        sextext.setText("");
        agetext.setText("");
        departmenttext.setText("");
        Classtext.setText("");
    }
}
