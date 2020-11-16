package kr.co.kokono.jspboard.member.service;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter @Setter
public class JoinRequestDto {
    private String account;
    private String name;
    private String password;
    private String confirmPassword;

    public boolean isPasswordEqualToConfirm() {
        return password != null && password.equals(confirmPassword);
    }

    public void validate(Map<String, Boolean> errors) {
        checkEmpty(errors, account, "account");
        checkEmpty(errors, name, "name");
        checkEmpty(errors, password, "password");
        checkEmpty(errors, confirmPassword, "confirmPassword");
        if (!errors.containsKey("confirmPassword")) {
            if (!isPasswordEqualToConfirm()) {
                errors.put("notMatch", Boolean.TRUE);
            }
        }
    }

    private void checkEmpty(Map<String, Boolean> errors, String value, String fieldName) {
        if (value == null || value.isEmpty()) {
            errors.put(fieldName, Boolean.TRUE);
        }
    }
}
