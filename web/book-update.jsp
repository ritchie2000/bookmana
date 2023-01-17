<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>图书更新</title>
		<style>
			.book{
				margin: 20px auto;
				display: flex;
				justify-content: center;
			}
		</style>
	</head>
	<body>
		
		<h2 style="text-align: center;">图书更新</h2>
		<div class="book">
			<form action="BookUpdateServlet" method="post" >
				<input type="hidden" value="${book.bookId }" name="bookId">
				<label for="bookName">图书名称</label>
				<input id="bookName" type="text" name="bookName" value="${book.bookName }"/><br>
				<label for="authorName">图书作者</label>
				<input id="authorName" type="text" name="authorName" value="${book.authorName }"/><br>
				<label for="price">图书价格</label>
				<input id="price" type="text" name="price" value="${book.price }"/><br>
				<label for="categoryName">图书分类</label>
				<select id="categoryName" name="categoryId" style="width: 147px;height: 21.5px;">
					<c:forEach items="${categories }" var="cates">
						<option <c:if test="${book.categoryId eq cates.categoryId }">selected</c:if> value="${cates.categoryId }">${cates.categoryName }</option>
					</c:forEach>
				</select><br>
				
				<label>是否上架</label> 
				<input id="up" type="radio" <c:if test="${book.flag==1 }">checked</c:if> name="flag" value="1"><label for="up">上架</label>
				<input id="down" type="radio" <c:if test="${book.flag==0 }">checked</c:if> name="flag" value="0"><label for="down">下架</label>
				<br>
				<label for="createTime">上传时间</label>
				
				<input id="createTime" type="date" name="createTime" value="<fmt:formatDate value="${book.createTime }" pattern="yyyy-MM-dd"/>" style="width: 142px;"/><br>
				<input type="submit" value="更新图书" style="margin: 10px auto;width: 218px;"/>
			</form>
		</div>
	</body>
</html>