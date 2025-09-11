<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<h1>Trang chủ User</h1>
<a href="${pageContext.request.contextPath}/profile">Profile</a>

<table border="1" width="100%">
	<tr>
		<th>STT</th>
		<th>Images</th>

		<th>CategoryID</th>
		<th>CategoryName</th>
		<th>Status</th>

	</tr>
	<c:forEach items="${listcate}" var="cate" varStatus="STT">
		<tr class="odd gradeX">
			<td>${STT.index+1 }</td>
			
			<c:if test="${cate.images.substring(0,5) !='https'}">
				<c:url value="/image?fname=${cate.images }" var="imgUrl"></c:url>
			</c:if>
			<c:if test="${cate.images.substring(0,5) =='https'}">
				<c:url value="${cate.images }" var="imgUrl"></c:url>
			</c:if>
			
			<td><img height="150" width="200" src="${imgUrl}" /></td>
			
			<td>${cate.categoryId }</td>
			<td>${cate.categoryName }</td>
			<td>
				<c:if test="${cate.status == 1 }">
					<span>Hoạt động</span>
				</c:if>
				<c:if test="${cate.status != 1 }">
					<span>Khóa</span>
				</c:if>
			</td>
			
		</tr>
	</c:forEach>
</table>