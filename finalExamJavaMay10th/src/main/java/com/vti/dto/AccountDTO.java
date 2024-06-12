package com.vti.dto;

import com.vti.entity.Role;
import lombok.Data;

@Data
public class AccountDTO {
    private int id;
    private String userName;
    private String password;
    private String fullName;
    private String role;

}
