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
        <title>비지니스 사용자 회원가입</title>
    </head>
    <body>
        <h1>비지니스 사용자 회원 가입</h1>
        <div class ="signup">
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
                <label for="name">기업명:</label>
                <input type="text" id="name" name="name" required style="width:auto"><br><br>
                <label for="name">대표자명:</label>
                <input type="text" id="name" name="name" required style="width:auto"><br><br>

                <div class = "phone">
                    <label for="phone">전화번호:</label>
                    <input type="tel" id="phone1" name="phone1" maxlength="3" required>
                    -
                    <input type="tel" id="phone2" name="phone2" maxlength="4" required>
                    -
                    <input type="tel" id="phone3" name="phone3" maxlength="4" required> 
                </div>
                <br><br>
                <!--
                <label for="major">전공:</label>
                <input type="text" id="major" name="major" required style="width:auto"><br><br>
                -->
                <div class ="address">
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
                    </script><br><br>
                </div>
                <input type="submit" value="가입하기">
                <button onclick="location.href = 'select_signup'">뒤로가기</button>
        </div>
    </form>
</body>
</html>
