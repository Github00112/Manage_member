<%--
  Created by IntelliJ IDEA.
  User: imhwan
  Date: 3/11/24
  Time: 6:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원 수정</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
        }
        .container {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            width: 50%;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            background-color: #f9f9f9;
        }
        .form-group {
            margin-bottom: 15px;
            display: flex;
            align-items: center;
        }
        label {
            width: 100px;
            font-weight: bold;
        }
        input[type="text"], input[type="password"] {
            flex: 1;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 3px;
            width: calc(100% - 110px); /* label 너비를 고려하여 입력 칸의 길이를 조정 */
        }
        input[type="submit"], input[type="button"] {
            width: 45%;
            padding: 10px;
            border: none;
            border-radius: 3px;
            background-color: #4CAF50;
            color: white;
            cursor: pointer;
        }
        input[type="submit"]:hover, input[type="button"]:hover {
            background-color: #45a049;
        }
        .button-container {
            display: flex;
            justify-content: space-between;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>회원 수정 양식</h1>
    <form action="/mem/modify" method="post">
        <div class="form-group">
            <label for="id">아이디:</label>
            <input type="text" id="id" name="id" value="<%= request.getParameter("id") %>" readonly>
        </div>
        <div class="form-group">
            <label for="password">새로운 비밀번호:</label>
            <input type="password" id="password" name="password" value="${dto.password}" required>
        </div>
        <div class="form-group">
            <label for="name">이름:</label>
            <input type="text" id="name" name="name" value="${dto.name}" required>
        </div>
        <div class="form-group">
            <label for="email">이메일:</label>
            <input type="text" id="email" name="email" value="${dto.email}" required>
        </div>
        <div class="button-container">
            <button type="submit" style="width: 45%; padding: 10px; border: none; border-radius: 3px; background-color: #4CAF50; color: white; cursor: pointer;">수정</button>
            <input type="button" value="취소" onclick="location.href='<%= request.getContextPath() %>/mem/do';">
        </div>
    </form>
</div>
</body>
</html>