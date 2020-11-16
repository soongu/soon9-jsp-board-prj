<%@ page import="java.sql.*" %>
<%@ page import="kr.co.kokono.jspboard.util.ConnectionProvider" %>
<%@ page contentType="text/html; charset=utf-8" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<%
    try (Connection conn = ConnectionProvider.getConnection()) {
        out.println("커넥션 연결 성공: " + conn);
    } catch (SQLException e) {
        out.println("커넥션 연결 실패: " + e.getMessage());
    }
%>
</body>
</html>