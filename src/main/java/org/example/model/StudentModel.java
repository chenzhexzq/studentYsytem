package org.example.model;

import org.example.dao.JdbcDaoService;
import org.example.pojo.Student;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.Vector;
public class StudentModel extends AbstractTableModel {
    private Vector<Student> students;
    private Vector<String> columnNames = null; //columnNames 存放列名
    private Vector<Vector<String>> rowData = null;//rowData 用来存放行数据
    private JdbcDaoService jdbcDaoService;
    public StudentModel(String sql, JDialog jDialog){
        jdbcDaoService = new JdbcDaoService();
        students = jdbcDaoService.getStudent(sql);
        columnNames = new Vector<String>();
        rowData = new Vector<Vector<String>>();
        columnNames.add("学号");
        columnNames.add("姓名");
        columnNames.add("性别");
        columnNames.add("年龄");
        columnNames.add("系名");
        columnNames.add("专业班级");
        for(Student student : students){
            Vector<String> row = new Vector<String>();
            row.add(student.getSno());
            row.add(student.getSname());
            row.add(student.getSsex());
            row.add(student.getSage());
            row.add(student.getSdept());
            row.add(student.getClas());
            rowData.add(row);
        }
        if(this.getRowCount()!=0){
            JOptionPane.showMessageDialog(jDialog, "一共有"+getRowCount()+" 条记录！");
            return ;
        }else{
            JOptionPane.showMessageDialog(jDialog, "没有任何记录！");
            return ;
        }
    }
    @Override
    public int getRowCount() { //得到共有多少行
        return this.rowData.size();
    }
    @Override
    public int getColumnCount() { //得到共有多少列
        return this.columnNames.size();
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) { //得到某行某列的数据
        return ((Vector)this.rowData.get(rowIndex)).get(columnIndex);
    }
    //重写方法 getColumnName
    @Override
    public String getColumnName(int i) {
        //return super.getColumnName(i);
        return (String)this.columnNames.get(i);
    }
}
