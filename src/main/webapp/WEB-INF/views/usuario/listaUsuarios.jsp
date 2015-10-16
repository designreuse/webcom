<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="utf-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<tiles:insertDefinition name="template">
		<tiles:putAttribute name="corpo">
			
			
			<h1>Cadastro de Usuários  </h1>
			
			<form method="get"	action="${pageContext.request.contextPath}/protected/usuario/busca-nome.do"	class="form-inline col-md-6" role="form">
				<div class="form-group  ">
					<label class="control-label" for="">Pesquisar usuários:</label>
					<input type="text" class="form-control" name="nome" autofocus="true" placeholder="Nome do usuário">
				</div>
				<button type="submit" class="btn btn-default  ">
					<span class="glyphicon glyphicon-search"></span>
				</button>
			</form>
			
			<a href="${pageContext.request.contextPath}/protected/usuario/preparaCadastroUsuario.do">
				<input type="button" value="Adicionar usuário" class="btn btn-default  pull-right" />
			</a>
			
			
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>Nome</th>
						<th>Login</th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="u" items="${usuarios}">
						<tr>
							<td>${u.nome}</td>
							<td>${u.login}</td>
							<td>
								<a href="${pageContext.request.contextPath}/protected/usuario/preparaEdit.do?idUsuario=${u.id_usuario}">
									          <span class="glyphicon glyphicon-edit"></span> 
									
								</a>
								
								&nbsp;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/protected/usuario/excluir.do?idUsuario=${u.id_usuario}">
									          <span class="glyphicon glyphicon-trash"></span> 
								</a>
								
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</tiles:putAttribute>
	</tiles:insertDefinition>
</html>
