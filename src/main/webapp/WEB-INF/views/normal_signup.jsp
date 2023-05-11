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
        <link type="text/css" rel="stylesheet" href="css/signup_css.css" />
        <title>일반 사용자 회원 가입</title>
        <script>
            function showSchoolInfo() {
                var schooltype = document.getElementById("schooltype").value;
                var schoolinfo = document.getElementById("schoolinfo");
                if (schooltype == "university") {
                    schoolinfo.innerHTML = `
                        <label for="school">학교:</label>
                        <input type="text" id="school" name="school" required style="width:auto"><br><br>
                        <label for="major">학과:</label>
                        <input type="text" id="major" name="major" required style="width:auto"><br><br>
                        <label for="grade">학년:</label>
                        <input type="text" id="grade" name="grade" required style="width:auto"><br><br>
                        <label for="status">상태:</label>
                        <input type="text" id="status" name="status" required style="width:auto"><br><br>
                    `;
                } else if (schooltype == "elementary" || schooltype == "middle" || schooltype == "high") {
                    schoolinfo.innerHTML = `
                        <label for="school">학교:</label>
                        <input type="text" id="school" name="school" required style="width:auto"><br><br>
                        <label for="grade">학년:</label>
                        <input type="text" id="grade" name="grade" required style="width:auto"><br><br>
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
        <h1>일반 사용자 회원 가입</h1>
        <div class ="signup">
            <form method ="POST" action ="${pageContext.request.contextPath}/checkId.do">
                <div>
                    <label for="userid">아이디:</label>
                    <input type="text" id="userid" name="userid" required style="width:auto"; >
                    <input type="submit" value="아이디 확인"><br><br>
                </div>
            </form>
            <form method="POST" action="${pageContext.request.contextPath}/normal_insert.do">
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
                <input type="text" id="interest" name="interest" required style="width:auto"><br><br>
                <label for="address">주소:</label>
                <input type="text" id="postcode" name="postcode" placeholder="우편번호">
                <input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
                <input type="text" id="address" name="address" placeholder="주소"><br>
                <input type="text" id="detail" name="detail" placeholder="상세주소">
                <input type="text" id="extra" name="extra" placeholder="참고항목">

                <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

                <script>
                    function sample6_execDaumPostcode() {
                        new daum.Postcode({
                            oncomplete: function (data) {
                                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                                var addr = ''; // 주소 변수
                                var extraAddr = ''; // 참고항목 변수

                                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                                    addr = data.roadAddress;
                                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                                    addr = data.jibunAddress;
                                }

                                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                                if (data.userSelectedType === 'R') {
                                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                                    if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                                        extraAddr += data.bname;
                                    }
                                    // 건물명이 있고, 공동주택일 경우 추가한다.
                                    if (data.buildingName !== '' && data.apartment === 'Y') {
                                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                                    }
                                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                                    if (extraAddr !== '') {
                                        extraAddr = ' (' + extraAddr + ')';
                                    }
                                    // 조합된 참고항목을 해당 필드에 넣는다.
                                    document.getElementById("extra").value = extraAddr;

                                } else {
                                    document.getElementById("extra").value = '';
                                }

                                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                                document.getElementById('postcode').value = data.zonecode;
                                document.getElementById("address").value = addr;
                                // 커서를 상세주소 필드로 이동한다.
                                document.getElementById("detail").focus();
                            }
                        }).open();
                    }
                </script>
                <label for="gender">성별:</label>
                <input type="radio" id="gender-male" name="gender" value="남자" required>
                <label for="gender-male">남성</label>
                <input type="radio" id="gender-female" name="gender" value="여자" required>
                <label for="gender-female">여성</label><br><br>
                <input type="submit" value="가입하기">
                <button onclick="location.href = 'select_signup'">뒤로가기</button>

            </form>
        </div>
    </body>
</html>
