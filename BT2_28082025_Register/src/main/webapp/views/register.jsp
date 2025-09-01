<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f0f2f5;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .register-box {
            background: #fff;
            padding: 25px 30px;
            border-radius: 10px;
            box-shadow: 0px 2px 10px rgba(0,0,0,0.2);
            width: 400px;
        }
        h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        .alert {
            color: red;
            margin-bottom: 15px;
            text-align: center;
        }
        input[type=text], input[type=email], input[type=password] {
            width: 100%;
            padding: 12px;
            margin: 8px 0 15px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        button {
            width: 100%;
            padding: 12px;
            background: #2196F3;
            border: none;
            border-radius: 5px;
            color: white;
            font-size: 16px;
            cursor: pointer;
        }
        button:hover {
            background: #1976D2;
        }
        .options {
            margin-top: 15px;
            text-align: center;
        }
        a {
            text-decoration: none;
            color: #4CAF50;
        }
    </style>
</head>
<body>
    <div class="register-box">
        <h2>Đăng ký</h2>

        <c:if test="${alert != null}">
            <div class="alert">${alert}</div>
        </c:if>

        <form action="/bt1/register" method="post">
            <label for="uname"><b>Username</b></label>
            <input type="text" name="uname" placeholder="Nhập tên đăng nhập" required>

            <label for="fullname"><b>Fullname</b></label>
            <input type="text" name="fullname" placeholder="Nhập họ và tên" required>

            <label for="email"><b>Email</b></label>
            <input type="email" name="email" placeholder="Nhập email" required>

            <label for="psw"><b>Password</b></label>
            <input type="password" name="psw" placeholder="Nhập mật khẩu" required>

            <button type="submit">Register</button>

            <div class="options">
                Đã có tài khoản? <a href="login.jsp">Login</a>
            </div>
        </form>
    </div>
</body>
</html>

