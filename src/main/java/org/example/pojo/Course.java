package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    private String cno;//课程代码
    private String cname;//课程名称
    private String teacher;//教师名称
    private String credit;//学分
}
