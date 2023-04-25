<%-- 
    Document   : signup
    Created on : 2023. 4. 25., 오후 7:52:34
    Author     : host
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>회원 가입</title>
        <script>
            function showSchoolInfo() {
                var schooltype = document.getElementById("schooltype").value;
                var schoolinfo = document.getElementById("schoolinfo");
                if (schooltype == "university") {
                    schoolinfo.innerHTML = `
                        <label for="school">학교:</label>
                        <input type="text" id="school" name="school" required><br><br>
                        <label for="major">학과:</label>
                        <input type="text" id="major" name="major" required><br><br>
                        <label for="grade">학년:</label>
                        <input type="text" id="grade" name="grade" required><br><br>
                        <label for="status">상태:</label>
                        <input type="text" id="status" name="status" required><br><br>
                    `;
                } else if (schooltype == "elementary" || schooltype == "middle" || schooltype == "high") {
                    schoolinfo.innerHTML = `
                        <label for="school">학교:</label>
                        <input type="text" id="school" name="school" required><br><br>
                        <label for="grade">학년:</label>
                        <input type="text" id="grade" name="grade" required><br><br>
                        <input type="hidden" id="status" name="status" value ="">
                        <input type="hidden" id="major" name="major" value="" >
                    `;
                } else {
                    schoolinfo.innerHTML = "";
                }
            }
        </script>
    </head>
    <body>
        <h1>회원 가입</h1>
        <form method ="POST" action ="${pageContext.request.contextPath}/checkId.do">
            <div>
                <label for="userid">아이디:</label>
                <input type="text" id="userid" name="userid" >
                <input type="submit" value="아이디 확인"><br><br>
            </div>
        </form>
        <form method="POST" action="${pageContext.request.contextPath}/insert.do">
            <label for="password">패스워드:</label>
            <input type="password" id="password" name="password" required><br><br>
            <label for="name">이름:</label>
            <input type="text" id="name" name="name" required><br><br>
            <label for="phone">전화번호:</label>
            <input type="tel" id="phone1" name="phone1" maxlength="3" required>
            -
            <input type="tel" id="phone2" name="phone2" maxlength="4" required>
            -
            <input type="tel" id="phone3" name="phone3" maxlength="4" required> <br><br>
            <label for="birthdate">생년월일:</label>
            <input type="date" id="birthdate" name="birthdate" required><br><br>
            <label for="schooltype">학교:</label>
            <select id="schooltype" name="schooltype" onchange="showSchoolInfo()">
                <option value="">--학교 유형--</option>
                <option value="elementary">초등학교</option>
                <option value="middle">중학교</option>
                <option value="high">고등학교</option>
                <option value="university">대학교</option>
            </select><br><br>
            <div id="schoolinfo"></div>
            <label for="interest">관심사:</label>
            <input type="text" id="interest" name="interest" required><br><br>
            <label for="address">주소:</label>
            <input type="text" id="city" name="city" placeholder="시" required>
            <input type="text" id="district" name="district" placeholder="구" required>
            <input type="text" id="street" name="street" placeholder="동" required>
            <input type="text" id="detail" name="detail" placeholder="상세주소" required><br><br>
            <label for="gender">성별:</label>
            <input type="radio" id="gender-male" name="gender" value="남자" required>
            <label for="gender-male">남성</label>
            <input type="radio" id="gender-female" name="gender" value="여자" required>
            <label for="gender-female">여성</label><br><br>
            <input type="submit" value="가입하기">
            <button onclick="location.href='select_signup'">뒤로가기</button>
            
        </form>
    </body>
</html>
