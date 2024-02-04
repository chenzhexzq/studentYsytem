package org.example.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private String Sno;
    private String Sname;
    private String Ssex;
    private String Sage;
    private String Sdept;
    private String Clas;

}

