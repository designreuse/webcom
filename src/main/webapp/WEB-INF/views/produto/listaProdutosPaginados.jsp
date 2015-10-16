<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/recursos/css/style-produto.css">

<tiles:insertDefinition name="template">
	<tiles:putAttribute name="corpo">


		<h1>Cadastro de Produtos</h1>

		<div class="row-fluid">
					<table class="table table-condensed table-bordered">
						<thead>
							<tr>
								<th>Nome</th>
								<th>Descrição</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${produtos}" var="p"
								varStatus="loopStatus">
								<tr class="${loopStatus.index % 2 == 0 ? 'odd' : 'even'}">
									<td><c:out value="${p.nome}" /></td>
									<td><c:out value="${p.descricao}" /></td>

									<!-- <td><spring:url value="/members/" var="update_form_url">
											<spring:param name="form" />
											<spring:param name="id" value="${member.id}"></spring:param>
										</spring:url> <spring:url value="/members/${member.id}"
											var="delete_form_url" /> <a href="${update_form_url}">${fn:escapeXml(edit_message)}</a>
										| <a href="#"
										onclick="javascript:deleteMember('${delete_form_url}')">${delete_message}</a>
									</td>-->
								</tr>
							</c:forEach>
							<tr>
						</tbody>
					</table>
				</div>
				
			
		
	</tiles:putAttribute>
</tiles:insertDefinition>
</html>
