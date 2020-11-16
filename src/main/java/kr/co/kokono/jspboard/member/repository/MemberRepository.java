package kr.co.kokono.jspboard.member.repository;

import kr.co.kokono.jspboard.member.entity.Member;

import java.sql.*;

import static kr.co.kokono.jspboard.util.JdbcUtil.close;

public class MemberRepository {

    public Member findByAccount(Connection connection, String account) throws SQLException {
        String query = "SELECT * FROM member WHERE account=?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, account);
            rs = pstmt.executeQuery();
            Member member = null;
            if (rs.next()) {
                member = new Member(rs);
            }
            return member;
        } finally {
            close(pstmt);
            close(rs);
        }
    }

    public void save(Connection connection, Member member) throws SQLException {
        String query = "INSERT INTO member values (?,?,?,?)";
        try(PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, member.getAccount());
            pstmt.setString(2, member.getName());
            pstmt.setString(3, member.getPassword());
            pstmt.setTimestamp(4, Timestamp.valueOf(member.getRegDate()));

            pstmt.executeUpdate();
        }
    }
}
