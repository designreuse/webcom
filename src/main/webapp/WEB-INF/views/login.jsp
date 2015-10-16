<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>webcom - login</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/recursos/css/bootstrap.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/recursos/css/style-login.css">
		<script src="${pageContext.request.contextPath}/recursos/js/jquery-2.1.3.min.js"></script>
		<script src="${pageContext.request.contextPath}/recursos/js/bootstrap.min.js"></script>
</head>
<body>
	
	
	
	
	<div class="container">
	    <div class="row">
	        <div class="col-sm-6 col-md-4 col-md-offset-4">
	            <h1 id="titulo1" class="text-center login-title">Seja Bem Vindo!</h1>
	             <h3 id="titulo2"  class="text-center login-title">Identifique-se para acessar o sistema.</h3>
	            <div class="account-wall">
	              <!-- <img class="profile-img" src="logo"   alt=""> -->  
	                <form class="form-signin"  action="j_spring_security_check" method="post">
	                <input type="text" class="form-control" name="j_username" id="user_login" placeholder="Login" required autofocus>
	                <input type="password" class="form-control" name="j_password" id="user_password"	placeholder="Senha" required>
	                <button class="btn btn-lg btn-primary btn-block" type="submit">
	                    Entrar</button>
	                <label class="text-center login-title">
	                    <c:if test="${param.error eq 'invalido'}">
							</p>
							<span style="color: #404040">Usuário ou Senha incorretos!</span>
							</p>
						</c:if>
	                </label>
	                </form>
	            </div>
	        </div>
	    </div>
	</div>

</body>
</html>



