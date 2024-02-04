package org.example.utils;

import java.awt.*;

public class WindowUtil {
    //设置窗体居中
    public static void setFrameCenter(Container container){
        Toolkit toolkit = Toolkit.getDefaultToolkit();//获取工具对象
        //获取屏幕的宽和高
        Dimension dim = toolkit.getScreenSize();
        double screenWidth=dim.getWidth();
        double screenHeight=dim.getHeight();
        //获取窗体的宽和高
        int frameWidth=container.getWidth();
        int frameHeight=container.getHeight();
        //获取新的宽和高
        int width = (int) (screenWidth - frameWidth) / 2;
        int height = (int) (screenHeight - frameHeight) / 2;
        //重新设置窗体位置
        container.setLocation(width, height);
    }
}
