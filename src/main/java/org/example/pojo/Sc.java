package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sc {
    private String sno;//学号
    private String cno;//课程代码
    private String score;//学生成绩
}
