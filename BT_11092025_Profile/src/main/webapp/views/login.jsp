<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Login</title>
<style>
body {
	font-family: Arial, sans-serif;
	background: #f0f2f5;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

.login-box {
	background: #fff;
	padding: 25px 30px;
	border-radius: 10px;
	box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.2);
	width: 350px;
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

input[type=text], input[type=password] {
	width: 100%;
	padding: 12px;
	margin: 8px 0 15px 0;
	border: 1px solid #ccc;
	border-radius: 5px;
}

button {
	width: 100%;
	padding: 12px;
	background: #4CAF50;
	border: none;
	border-radius: 5px;
	color: white;
	font-size: 16px;
	cursor: pointer;
}

button:hover {
	background: #45a049;
}

.options {
	margin-top: 10px;
	font-size: 14px;
	display: flex;
	justify-content: space-between;
}

a {
	text-decoration: none;
	color: #2196F3;
}
</style>
</head>
<body>
	<div class="login-box">
		<h2>Đăng nhập</h2>

		<c:if test="${alert != null}">
			<div class="alert">${alert}</div>
		</c:if>


		<form action="${pageContext.request.contextPath}/login" method="post">
			<label for="uname"><b>Username</b></label> <input type="text"
				name="uname"
				value="${cookie['rememberMe'] != null ? cookie['rememberMe'].value : ''}"
				placeholder="Nhập tên đăng nhập" required> <label for="psw"><b>Password</b></label>
			<input type="password" name="psw" placeholder="Nhập mật khẩu"
				required>

			<button type="submit">Login</button>

			<div class="options">
				<label> <input type="checkbox" name="remember"
					<c:if test="${cookie['rememberMe'] != null}">checked</c:if>>
					Remember me
				</label> <span class="psw"><a
					href="${pageContext.request.contextPath}/forgot-password">Quên mật khẩu?</a></span>
			</div>
		</form>
	</div>
</body>
</html>
