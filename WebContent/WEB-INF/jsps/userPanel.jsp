<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>User Panel - QuizApp</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/styles.css">
<style type="text/css">
</style>
</head>
<body>
	<div id='navbar'>
		<ul>
			<li><a href='${pageContext.request.contextPath}'><span>Home</span></a></li>
			<li><a href='${pageContext.request.contextPath}/login'><span>Login</span></a></li>
			<li><a href='${pageContext.request.contextPath}/register'><span>Register</span></a></li>
			<li class='active'><a
				href='${pageContext.request.contextPath}/userPanel'><span>My
						Results</span></a></li>

		</ul>
	</div>

	<!--  If user is not logged in -->
	<c:if test='${empty sessionScope.user}'>
		<div id="wrapper">
			<form name="login-form" class="login-form">
				<div class="header" style="height: 60px;">
					<img style="float: left;" height="30" width="30"
						src="${pageContext.request.contextPath}/images/warn.png" />
					<h1 style="float: right;">Please Register or Login..</h1>
				</div>
			</form>
		</div>
	</c:if>

	<!--  If user is logged in hide the register panel-->
	<c:if test='${not empty sessionScope.user}'>

		<a href='${pageContext.request.contextPath}/userPanel'>
			<div class="button nameuser">Logged as, ${sessionScope.user}</div>
		</a>

		<a href='${pageContext.request.contextPath}/logout'>
			<div class="button logout">Logout</div>
		</a>

		<div class="login-form" style="margin-top: 5%;">
			<h2 align="center"
				style="color: white; background-color: blue; border-radius: 5px;">Test
				Results : ${sessionScope.user}</h2>
			<div class="results">
				<tr>
					<c:if test="${java < 50}">
						<div style="color: red;">
					</c:if>
					<c:if test="${java >= 50}">
						<div style="color: green;">
					</c:if>
					<c:out value="Java: %${java}" />
				</tr>
				<tr>
					<c:if test="${python < 50}">
						<div style="color: red;">
					</c:if>
					<c:if test="${python >= 50}">
						<div style="color: green;">
					</c:if>
					<c:out value="Python: %${python}" />
				</tr>
				<tr>
					<c:if test="${c < 50}">
						<div style="color: red;">
					</c:if>
					<c:if test="${c >= 50}">
						<div style="color: green;">
					</c:if>
					<c:out value="C: %${c}" />
				</tr>
			</div>
	</c:if>

</body>
</html>
