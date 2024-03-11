<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>회원 관리 과제</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-image: url('https://source.unsplash.com/1600x900/?abstract');
            background-size: cover;
            background-position: center;
            color: #fff;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.7);
        }
        .container {
            text-align: center;
            margin-top: 100px;
        }
        h1 {
            color: #fff;
            font-size: 3rem;
            margin-bottom: 20px;
        }
        h2 {
            color: #fff;
            font-size: 2rem;
            margin-bottom: 30px;
        }
        a {
            display: inline-block;
            padding: 15px 30px;
            background-color: rgba(0, 123, 255, 0.8);
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            font-size: 1.2rem;
            transition: background-color 0.3s ease;
        }
        a:hover {
            background-color: rgba(0, 123, 255, 1);
        }
    </style>
</head>
<body>
<div class="container">
    <h1><%= "회원 관리 과제" %></h1>
    <a href="/mem/do">회원 가입하러가기</a>
</div>
</body>
</html>