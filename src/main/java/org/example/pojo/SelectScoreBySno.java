package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectScoreBySno {
    private String sno;
    private String sname;
    private String cname;
    private String score;
}
