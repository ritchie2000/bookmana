<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>登录</title>
		<style>
			.login{
				width: 300px;
				margin: auto;
				text-align: center;
			}
		</style>
	</head>
	<body>
		<h2 style="text-align: center;">登录</h2>
		<h4 style="text-align: center;color: red;">${msg }</h4>
		<div class="login">
			<form action="LoginServlet" method="POST">
				<label for="email">邮箱</label>
				<input id="email" type="email" name="email" placeholder="邮箱..."/><br>
				<label for="password">密码</label>
				<input id="password" type="password" name="password" placeholder="密码..."/><br>
				<input type="submit" value="登录" style="margin-top: 10px;"/>
			</form>
		</div>
		
	</body>
</html>