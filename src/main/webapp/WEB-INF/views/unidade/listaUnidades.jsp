<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="utf-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<tiles:insertDefinition name="template">
		<tiles:putAttribute name="corpo">
			
			
			<h1>Cadastro de Unidades  </h1>
			
			
			
			<a href="${pageContext.request.contextPath}/protected/unidade/preparaCadastroUnidade.do">
				<input type="button" value="Adicionar unidade" class="btn btn-default  pull-right" />
			</a>
			
			
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>Descrição</th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="u" items="${unidades}">
						<tr>
							<td>${u.descricao}</td>
							<td>
								<a href="${pageContext.request.contextPath}/protected/unidade/preparaEdit.do?idUnidade=${u.idUnidade}">
									          <span class="glyphicon glyphicon-edit"></span> 
									
								</a>
								
								&nbsp;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/protected/unidade/excluir.do?idUnidade=${u.idUnidade}">
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
