<%--
  Created by IntelliJ IDEA.
  User: imhwan
  Date: 3/8/24
  Time: 4:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>회원 정보</title>
    <style>
        body {
            text-align: center;
        }
        table {
            margin: 0 auto;
            text-align: left;
            border-collapse: collapse;
            width: 80%;
        }
        th, td {
            padding: 8px;
            text-align: center;
        }
        th {
            background-color: #f2f2f2;
        }
        #signup-link {
            position: fixed;
            bottom: 20px;
            left: 50%;
            transform: translateX(-50%);
        }
    </style>
    <script type="text/javascript">
        function deleteMember(id) {
            if (confirm("정말로 삭제하시겠습니까?")) {
                window.location.href="/mem/deleteMember?id="+id;
            }else {
                return false;
            }
        }
    </script>

</head>
<body>
<h1>회원 정보</h1>
<table border="1">
    <thead>
    <tr>
        <th>아이디</th>
        <th>비밀번호</th>
        <th>이름</th>
        <th>이메일</th>
        <th>가입일</th>
        <th>수정</th>
        <th>삭제</th>
    </tr>
    </thead>
    <tbody>
    <c:choose>
        <c:when test="${not empty members}">
            <c:forEach items="${members}" var="member">
                <tr>
                    <td>${member.id}</td>
                    <td>${member.password}</td>
                    <td>${member.name}</td>
                    <td>${member.email}</td>
                    <td>${member.join_date}</td>
                    <td>
                        <a href="/mem/modify?id=${member.id}">수정</a>
                    </td>
                    <td>
                        <a href="/mem/deleteMember?id=${member.id}" onclick='return deleteMember(id)'>삭제</a>
                    </td>
                </tr>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <tr>
                <td colspan="5">회원 정보가 없습니다.</td>
            </tr>
        </c:otherwise>
    </c:choose>
    </tbody>
</table>
<div id="signup-link">
    <a href="/mem/add">회원 가입하기</a>
</div>
</body>
</html>