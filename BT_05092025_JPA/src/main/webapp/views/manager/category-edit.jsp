<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<form action="${pageContext.request.contextPath}/manager/category/update" method="post">
<input type="text" id="categoryId" name="categoryId" value="${cate.categoryId}" hidden="hidden"><br>
  <label for="categoryName">Category name:</label><br>
  <input type="text" id="categoryName" name="categoryName" value="${cate.categoryName}" ><br>
  <label for="categoryName">Images:</label><br>
  		<c:if test="${cate.images.substring(0,5) !='https'}">
				<c:url value="/image?fname=${cate.images }" var="imgUrl"></c:url>
			</c:if>
				<c:if test="${cate.images.substring(0,5) =='https'}">
					<c:url value="${cate.images }" var="imgUrl"></c:url>
			</c:if>
			<img height="150" width="200" src="${imgUrl}" />
			
  <input type="file" id="images" name="images"  value="${cate.images}"><br>
  <label for="status">Status:</label><br>
  <input type="text" id="status" name="status" value="${cate.status}"><br>
  <br> <input type="submit" value="Submit">
</form> 