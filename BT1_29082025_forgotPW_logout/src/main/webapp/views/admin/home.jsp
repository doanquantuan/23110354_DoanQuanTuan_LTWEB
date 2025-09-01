<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f0f2f5;
            text-align: center;
            margin-top: 100px;
        }
        h1 {
            color: #333;
        }
        .btn {
            display: inline-block;
            margin: 20px;
            padding: 12px 25px;
            font-size: 16px;
            background: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            transition: 0.3s;
        }
        .btn:hover {
            background: #45a049;
        }
        .btn.register {
            background: #2196F3;
        }
        .btn.register:hover {
            background: #1976D2;
        }
    </style>
</head>
<body>
    <h1>Trang chủ</h1>
    <a href="${pageContext.request.contextPath}/login" class="btn">Login</a>
    <a href="${pageContext.request.contextPath}/register" class="btn register">Register</a>
</body>
</html>
