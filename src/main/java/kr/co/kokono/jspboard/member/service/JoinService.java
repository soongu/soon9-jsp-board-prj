package kr.co.kokono.jspboard.member.service;

import kr.co.kokono.jspboard.member.entity.Member;
import kr.co.kokono.jspboard.member.exception.DuplicateAccountException;
import kr.co.kokono.jspboard.member.repository.MemberRepository;
import kr.co.kokono.jspboard.util.ConnectionProvider;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

import static kr.co.kokono.jspboard.util.JdbcUtil.close;
import static kr.co.kokono.jspboard.util.JdbcUtil.rollback;

public class JoinService {

    private final MemberRepository memberRepository = new MemberRepository();

    public void join(JoinRequestDto joinReq) {
        Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            conn.setAutoCommit(false);

            Member member = memberRepository.findByAccount(conn, joinReq.getAccount());
            if (member != null) {
                rollback(conn);
                throw new DuplicateAccountException();
            }

            memberRepository.save(conn, new Member(
                    joinReq.getAccount(),
                    joinReq.getName(),
                    joinReq.getPassword(),
                    LocalDateTime.now()
            ));
            conn.commit();
        } catch (SQLException e) {
            rollback(conn);
            throw new RuntimeException(e);
        } finally {
            close(conn);
        }
    }
}
