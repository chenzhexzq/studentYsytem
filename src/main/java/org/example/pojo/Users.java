package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    private String username; //用户名
    private String userpwd; //密码
    private String role; //用户权限角色
}

