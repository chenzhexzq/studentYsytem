package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectByNoGood {
    private String sno;
    private String sname;
    private String cno;
    private String cname;
    private String score;
}
