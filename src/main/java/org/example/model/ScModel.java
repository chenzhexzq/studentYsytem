package org.example.model;

import org.example.dao.JdbcDaoService;
import org.example.pojo.Sc;
import org.example.pojo.Student;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.Vector;

public class ScModel extends AbstractTableModel {
    private Vector<Sc> scs;
    private Vector<String> columnNames = null; //columnNames 存放列名
    private Vector<Vector<String>> rowData = null;//rowData 用来存放行数据
    private JdbcDaoService jdbcDaoService;
    public ScModel(String sql, JDialog jDialog){
        jdbcDaoService = new JdbcDaoService();
        scs = jdbcDaoService.getSc(sql);
        columnNames = new Vector<String>();
        rowData = new Vector<Vector<String>>();
        columnNames.add("学号");
        columnNames.add("课程代码");
        columnNames.add("成绩");
        for(Sc sc : scs){
            Vector<String> row = new Vector<String>();
            row.add(sc.getSno());
            row.add(sc.getCno());
            row.add(sc.getScore());
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
