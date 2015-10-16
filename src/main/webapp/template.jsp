<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>WebCom</title>
			<link rel="stylesheet"href="${pageContext.request.contextPath}/recursos/css/bootstrap.min.css">
			<link rel="stylesheet"href="${pageContext.request.contextPath}/recursos/css/style-template.css">
			<script src="${pageContext.request.contextPath}/recursos/js/jquery-2.1.3.min.js"></script>
			<script src="${pageContext.request.contextPath}/recursos/js/bootstrap.min.js"></script>
			<script src="${pageContext.request.contextPath}/recursos/js/jprice.js"></script>
			<script src="${pageContext.request.contextPath}/recursos/js/jquery.maskedinput.min.js"></script>
			<script src="${pageContext.request.contextPath}/recursos/js/relatorio.js"></script>
			

</head>
<body>
	<nav class="navbar navbar-default" role="navigation">
	<div class="container-fluid" id="nav">
		<div class="navbar-header ">
			<button type="button" class="navbar-toggle collapsed"	data-toggle="collapse" data-target="#navbar-1">
				<span class="sr-only"></span> <span class="icon-bar"></span>
				<span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${pageContext.request.contextPath}/protected/home.do">Webcom</a>
		</div>

		<div class="collapse navbar-collapse " id="navbar-1">
			<ul class="nav navbar-nav">
				<li><a href="${pageContext.request.contextPath}/protected/venda/abrir-venda.do">Venda</a></li>
				<li class="dropdown">
					 <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
				 		Cadastros<span 	class="caret"></span>
					 </a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="${pageContext.request.contextPath}/protected/cliente/listar.do">Clientes</a></li>
							<li><a 	href="${pageContext.request.contextPath}/protected/produto/listar.do">Produtos</a></li>
							<li><a	href="${pageContext.request.contextPath}/protected/usuario/listar.do">Usuarios</a></li>
							<li><a	href="${pageContext.request.contextPath}/protected/fornecedor/listar.do">Fornecedores</a></li>
							<li><a	href="${pageContext.request.contextPath}/protected/unidade/listar.do">Unidade</a></li>
							
						</ul>
				</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle"data-toggle="dropdown" role="button" aria-expanded="false">
						Relatorios<span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="${pageContext.request.contextPath}/protected/relatorio/relatorio.do">Vendas</a></li>
							<li><a href="${pageContext.request.contextPath}/protected/relatorio/relatorio-produto-codigo.do">Produtos - códigos</a></li>
						
						</ul>
				</li>
				
				
			</ul>
			
	 		<ul class="nav navbar-nav navbar-right">
				<sec:authorize access="isAuthenticated()">	
							<div id="bemvindo">Bem vindo(a) 
							<sec:authentication property="principal.username"/> -
							<a href="${pageContext.request.contextPath}/logout.do">Sair</a>
							</div>
				</sec:authorize>
			
			</ul>
			
				
		</div>
	 	
	</div>
	</nav>

	<c:if test="${mensagem != null}">
		<div class="${mensagem.tipoMensagem.classeCss}" role="alert">${mensagem.texto}</div>
	</c:if>

	<div class="panel panel-default">
		<div class="panel-body">
			<tiles:insertAttribute name="corpo" />
		</div>
	</div>
	
	<div class="container">
      <footer>
        <p>&copy; WebCom</p>
      </footer>
    </div>
</body>
</html>