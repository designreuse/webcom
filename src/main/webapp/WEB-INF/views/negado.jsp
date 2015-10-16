<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>webcom - permissão negada</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/recursos/css/bootstrap.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/recursos/css/style-negado.css">
		<script src="${pageContext.request.contextPath}/recursos/js/jquery-2.1.3.min.js"></script>
		<script src="${pageContext.request.contextPath}/recursos/js/bootstrap.min.js"></script>
</head>
<body>
	
	
	<div class="container">
	    <div class="row">
	        <div class="col-sm-6 col-md-4 col-md-offset-4">
	            <h1  id="titulo1" class="text-center login-title">Você não tem permissão para acessar esta página!</h1>
	            <a  id="titulo2" class="text-center login-title" href="${pageContext.request.contextPath}/protected/home.do">
	             <span class="glyphicon glyphicon-circle-arrow-left"></span> </a>
	            
	        </div>
	    </div>
	</div>

</body>
</html>



