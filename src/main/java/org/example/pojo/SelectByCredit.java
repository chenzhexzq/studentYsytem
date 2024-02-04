package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelectByCredit {
    private String sno;
    private String sname;
    private String cno;
    private String cname;
    private String credit;
}
