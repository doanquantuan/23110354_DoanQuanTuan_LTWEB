<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<form action="${pageContext.request.contextPath}/manager/category/insert" method="post">
  <label for="categoryName">Category name:</label><br>
  <input type="text" id="categoryName" name="categoryName" ><br>
  <label for="categoryName">Images:</label><br>
  <input type="file" id="images" name="images" ><br>
  <label for="status">Status:</label><br>
  <input type="text" id="status" name="status" ><br>
  <br> <input type="submit" value="Submit">
</form> 