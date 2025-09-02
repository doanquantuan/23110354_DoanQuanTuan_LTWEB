<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Xác thực OTP</title>
<style>
body {
    font-family: Arial, sans-serif;
    background: #f2f2f2;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}

.form-container {
    background: #fff;
    padding: 25px;
    border-radius: 10px;
    box-shadow: 0px 0px 15px rgba(0,0,0,0.1);
    width: 350px;
}

.form-container h2 {
    text-align: center;
    margin-bottom: 20px;
    color: #333;
}

.form-container input[type="text"],
.form-container input[type="password"] {
    width: 100%;
    padding: 12px;
    margin: 10px 0;
    border: 1px solid #ccc;
    border-radius: 6px;
}

.form-container button {
    width: 100%;
    padding: 12px;
    background: #28a745;
    color: #fff;
    border: none;
    border-radius: 6px;
    cursor: pointer;
    font-size: 15px;
}

.form-container button:hover {
    background: #218838;
}

.alert {
    color: red;
    text-align: center;
    margin-bottom: 15px;
}

.success {
    color: green;
    text-align: center;
    margin-bottom: 15px;
}
</style>
</head>
<body>
<div class="form-container">
    <h2>Xác thực OTP</h2>

    <!-- Hiển thị thông báo -->
    <c:if test="${not empty alert}">
        <div class="${alert.contains('thành công') ? 'success' : 'alert'}">
            ${alert}
        </div>
    </c:if>

    <!-- Form nhập OTP -->
    <form action="verify-otp" method="post">
        <input type="text" name="otp" placeholder="Nhập mã OTP" required maxlength="6"/>
        <button type="submit">Xác thực OTP</button>
    </form>

    <!-- Nếu muốn, thêm nút gửi lại OTP -->
    <form action="forgot-password" method="post" style="margin-top:10px;">
        <input type="hidden" name="email" value="${sessionScope.otpEmail}" />
        <button type="submit" style="background:#007bff;">Gửi lại OTP</button>
    </form>
</div>
</body>
</html>
