package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class selectAvgscoreBySno {
    private String sno;
    private String sname;
    private String avgScore;
}
