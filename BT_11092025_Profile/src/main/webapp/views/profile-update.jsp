<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<h2>Cập nhật thông tin cá nhân</h2>

<form action="${pageContext.request.contextPath}/profile/update" method="post">
    <label for="fullname">Họ tên:</label><br>
    <input type="text" id="fullname" name="fullname" value="${user.fullName}"><br><br>

    <label for="phone">Số điện thoại:</label><br>
    <input type="text" id="phone" name="phone" value="${user.phone}"><br><br>

    <label for="images">Ảnh đại diện (URL):</label><br>
    <c:if test="${not empty user.images}">
        <img src="${user.images}" alt="Avatar" width="150" height="150"><br>
    </c:if>
    <input type="text" id="images" name="images" value="${user.images}"><br><br>

    <input type="submit" value="Cập nhật">
</form>
