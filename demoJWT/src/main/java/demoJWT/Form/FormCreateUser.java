package demoJWT.Form;

import lombok.Data;

@Data
public class FormCreateUser {
    private String username;
    private String password;
    private int roleId;
}
