<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Welcome</title>
<style>
    /* Body nền trắng, căn giữa */
    body {
        margin: 0;
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        background: #f9f9f9;
        height: 100vh;
        display: flex;
        justify-content: center;
        align-items: center;
        color: #333;
    }

    /* Box chứa nội dung */
    .welcome-box {
        background: #fff;
        border-radius: 12px;
        padding: 40px 50px;
        box-shadow: 0 5px 15px rgba(0,0,0,0.1);
        text-align: center;
        width: 400px;
    }

    h2 {
        margin-bottom: 15px;
        font-size: 26px;
        color: #333;
    }

    p {
        font-size: 16px;
        margin-bottom: 30px;
        color: #555;
    }

    /* Nút hành động */
    .btn {
        display: inline-block;
        padding: 12px 25px;
        margin: 0 10px;
        font-size: 16px;
        font-weight: bold;
        text-decoration: none;
        border-radius: 8px;
        transition: 0.3s;
        color: white;
    }

    .btn.home {
        background: #4CAF50;
    }
    .btn.home:hover {
        background: #45a049;
    }

    .btn.logout {
        background: #f44336;
    }
    .btn.logout:hover {
        background: #d32f2f;
    }

    /* Icon tùy chọn */
    .btn i {
        margin-right: 8px;
    }
</style>
</head>
<body>
    <div class="welcome-box">
        <h2>Chào mừng, <c:out value="${username}"/>!</h2>
        <p>Bạn đã đăng nhập thành công.</p>

        <div class="actions">
            <a href="${pageContext.request.contextPath}/admin/home" class="btn home">
                <i class="fas fa-home"></i>Quay lại Home
            </a>
            <a href="${pageContext.request.contextPath}/logout" class="btn logout">
                <i class="fas fa-sign-out-alt"></i>Logout
            </a>
        </div>
    </div>

    <!-- FontAwesome cho icon -->
    <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
</body>
</html>
