package com.vti.form.Account;

import com.vti.validation.Account.AccountUsernameNotExists;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
//@Validated
public class CreateAccount {

    @NotBlank(message = "Username not null")
    @AccountUsernameNotExists
    private String username;
    private String password;
    private String fullName;
    private String role;

}
