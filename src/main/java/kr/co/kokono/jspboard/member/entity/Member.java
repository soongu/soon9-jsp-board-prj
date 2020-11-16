package kr.co.kokono.jspboard.member.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Member {

    private String account;
    private String name;
    private String password;
    private LocalDateTime regDate;

    public Member(ResultSet rs) throws SQLException {
        account = rs.getString("account");
        name = rs.getString("name");
        password = rs.getString("password");
        regDate = rs.getTimestamp("regdate").toLocalDateTime();
    }

    public boolean matchPassword(String pwd) {
        return password.equals(pwd);
    }
}
