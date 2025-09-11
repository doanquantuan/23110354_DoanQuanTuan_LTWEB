<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<h2>Thông tin cá nhân</h2>
<img src="${user.images}" alt="Avatar" width="120" height="120"><br>
Full name: ${user.fullName} <br>
Phone: ${user.phone} <br>
<a href="${pageContext.request.contextPath}/profile/update">Update</a>
