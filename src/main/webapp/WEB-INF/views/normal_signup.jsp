<%-- 
    Document   : normal_signup
    Created on : 2023. 4. 26., 오전 2:36:36
    Author     : host
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link type="text/css" rel="stylesheet" href="css/signup_css.css" />
        <title>일반 사용자 회원가입</title>
    </head>
    <body>
        <h1>일반 사용자 회원 가입</h1>
        <form method ="POST" action ="${pageContext.request.contextPath}/checkId.do">
            <div>
                <label for="userid">아이디:</label>
                <input type="text" id="userid" name="userid" required  style="width:auto";>
                <input type="submit" value="아이디 확인"><br><br>
            </div>
        </form>
        <form method="POST" action="${pageContext.request.contextPath}/nomal_insert.do">
            <label for="password">패스워드:</label>
            <input type="password" id="password" name="password" required style="width:auto"><br><br>
            <label for="name">이름:</label>
            <input type="text" id="name" name="name" required style="width:auto"><br><br>
            <label for="phone">전화번호:</label>
            <input type="tel" id="phone1" name="phone1" maxlength="3" required>
            -
            <input type="tel" id="phone2" name="phone2" maxlength="4" required>
            -
            <input type="tel" id="phone3" name="phone3" maxlength="4" required> <br><br>
            <label for="birthdate">생년월일:</label>
            <input type="date" id="birthdate" name="birthdate" required style="width:auto"><br><br>
            <label for="address">주소:</label>
            <input type="text" id="city" name="city" placeholder="시" required>
            <input type="text" id="district" name="district" placeholder="구" required>
            <input type="text" id="street" name="street" placeholder="동" required>
            <input type="text" id="detail" name="detail" placeholder="상세주소" required><br><br>
            <label for="major">전공:</label>
            <input type="text" id="major" name="major" required style="width:auto"><br><br>
            <label for="gender">성별:</label>
            <input type="radio" id="gender-male" name="gender" value="남자" required>
            <label for="gender-male">남성</label>
            <input type="radio" id="gender-female" name="gender" value="여자" required>
            <label for="gender-female">여성</label><br><br>
            <input type="submit" value="가입하기">
            <button onclick="location.href = 'select_signup'">뒤로가기</button>
        </form>
    </body>
</html>
