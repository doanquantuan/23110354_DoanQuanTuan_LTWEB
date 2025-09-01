<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f0f2f5;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .welcome-box {
            background: #fff;
            padding: 30px 40px;
            border-radius: 10px;
            box-shadow: 0px 2px 10px rgba(0,0,0,0.2);
            text-align: center;
        }
        h2 {
            color: #333;
        }
        .logout {
            margin-top: 20px;
        }
        .logout a {
            text-decoration: none;
            color: #fff;
            background: #f44336;
            padding: 10px 20px;
            border-radius: 5px;
        }
        .logout a:hover {
            background: #d32f2f;
        }
    </style>
</head>
<body>
    <div class="welcome-box">
        <h2>Chào mừng, <c:out value="${username}"/> 🎉</h2>
        <p>Bạn đã đăng nhập thành công!</p>

        <div class="logout">
            <a href="home.jsp">Quay lại Home</a>
        </div>
    </div>
</body>
</html>
