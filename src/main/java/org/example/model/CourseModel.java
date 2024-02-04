package org.example.model;

import org.example.dao.JdbcDaoService;
import org.example.pojo.Course;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.Vector;

public class CourseModel extends AbstractTableModel {
    private Vector<Course> courses;
    private Vector<String> columnNames = null; //columnNames 存放列名
    private Vector<Vector<String>> rowData = null;//rowData 用来存放行数据
    private JdbcDaoService jdbcDaoService;
    public CourseModel(String sql, JDialog jDialog){
        jdbcDaoService = new JdbcDaoService();
        courses = jdbcDaoService.getCourse(sql);
        columnNames = new Vector<String>();
        rowData = new Vector<Vector<String>>();
        columnNames.add("课程代码");
        columnNames.add("课程名称");
        columnNames.add("教师姓名");
        columnNames.add("学分");
        for(Course course : courses){
            Vector<String> row = new Vector<String>();
            row.add(course.getCno());
            row.add(course.getCname());
            row.add(course.getTeacher());
            row.add(course.getCredit());
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
