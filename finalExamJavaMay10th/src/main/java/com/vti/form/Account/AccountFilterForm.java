package com.vti.form.Account;

import com.vti.validation.Account.AccountUsernameNotExists;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountFilterForm {
    @AccountUsernameNotExists
    private String userName;
    private String fullName;
    private Integer minId;
    private Integer maxId;
}
