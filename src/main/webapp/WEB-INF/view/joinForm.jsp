<%@page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sign up</title>
</head>
<body>
<form action="join.nako" method="post">
    <p>
        아이디: <br> <input type="text" name="account" value="${param.account}">
        <c:if test="${errors.account}">ID를 입력하세요.</c:if>
        <c:if test="${errors.duplicateAccount}">이미 사용중인 아이디입니다.</c:if>
    </p>
    <p>
        이름: <br> <input type="text" name="name" value="${param.name}">
        <c:if test="${errors.name}">이름을 입력하세요.</c:if>
    </p>
    <p>
        암호: <br> <input type="password" name="password" value="${param.password}">
        <c:if test="${errors.password}">암호를 입력하세요.</c:if>
    </p>
    <p>
        확인: <br> <input type="password" name="confirmPassword" value="${param.confirmPassword}">
        <c:if test="${errors.confirmPassword}">확인을 입력하세요.</c:if>
        <c:if test="${errors.notMatch}">암호와 확인이 일치하지 않습니다.</c:if>
    </p>
    <input type="submit" value="가입">
</form>
</body>
</html>