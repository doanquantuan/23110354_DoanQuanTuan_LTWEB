<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quên mật khẩu</title>
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
	box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.1);
	width: 350px;
}

.form-container h2 {
	text-align: center;
	margin-bottom: 20px;
	color: #333;
}

.form-container input[type="email"] {
	width: 100%;
	padding: 12px;
	margin: 10px 0;
	border: 1px solid #ccc;
	border-radius: 6px;
}

.form-container button {
	width: 100%;
	padding: 12px;
	background: #007bff;
	color: #fff;
	border: none;
	border-radius: 6px;
	cursor: pointer;
	font-size: 15px;
}

.form-container button:hover {
	background: #0056b3;
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
		<h2>Quên mật khẩu</h2>

		<!-- Hiển thị thông báo -->
		<c:if test="${not empty alert}">
			<div class="${alert.contains('OTP') ? 'success' : 'alert'}">
				${alert}</div>
		</c:if>


		<form action="forgot-password" method="post">
			<input type="email" name="email" placeholder="Nhập email của bạn"
				required />
			<button type="submit">Gửi mã OTP</button>
		</form>
	</div>
</body>
</html>
