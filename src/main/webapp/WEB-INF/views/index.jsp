<%-- 
    Document   : index
    Created on : 2023. 4. 15., 오후 11:54:14
    Author     : host
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
    </head>
    <body>
    <li th:text="|인가코드 = ${code}|">|인가코드 = ${code}</li>
    <li th:text="|유효토큰 = ${access_token}|">|유효토큰 = ${access_token}</li>
    <li th:text="|사용자정보 = ${userInfo}|">|사용자정보  = ${userInfo}</li>
    <li th:text="|동의정보 = ${agreementInfo}|">|동의정보 = ${agreementInfo}</li>
    <button onclick="location.href='https://kauth.kakao.com/oauth/logout?client_id=438e26bbed1a7e616852d3e70b183c6b&logout_redirect_uri=http://localhost:8080/'">로그아웃</button>
</body>
</html>
