<%-- 
    Document   : checkId_result
    Created on : 2023. 4. 25., 오후 11:30:15
    Author     : host
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>아이디 확인 결과</title>
    </head>
    <body>
       <script>
            alert('${msg}');
            location.href = history.back();
        </script>
    </body>
</html>
