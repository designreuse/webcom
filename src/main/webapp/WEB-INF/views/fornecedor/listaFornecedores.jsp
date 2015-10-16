<%@ page language="java" contentType="text/html; charset=utf-8" 	pageEncoding="utf-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<tiles:insertDefinition name="template">
	<tiles:putAttribute name="corpo">

		


			<h1>
				Cadastro de Fornecedores
			</h1>
			
			<form method="get"	action="${pageContext.request.contextPath}/protected/fornecedor/busca-nome.do"	class="form-inline col-md-6" role="form">
				<div class="form-group  ">
					<label class="control-label" for="">Pesquisar fornecedores:</label>
					<input type="text" class="form-control" name="nome" autofocus="true" placeholder="Nome do fornecedor">
				</div>
				<button type="submit" class="btn btn-default  ">
					<span class="glyphicon glyphicon-search"></span>
				</button>
			</form>
			
				<a	href="${pageContext.request.contextPath}/protected/preparaCadastroFornecedor.do">
					<input type="button" value="Adicionar fornecedor"	class="btn btn-default  pull-right" />
				</a>
			
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>Nome</th>
						<th>CPF</th>
						<th>CNPJ</th>
						<th>RG</th>
						<th>Endereço</th>
						<th>Bairro</th>
						<th>Cidade</th>
						<th>Estado</th>
						<th>Telefone</th>
						<th>Email</th>
						<th>Observação</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="f" items="${fornecedores}">
						<tr>
							<td>${f.nome}</td>
							<td>${f.cpf}</td>
							<td>${f.cnpj}</td>
							<td>${f.rg}</td>
							<td>${f.endereco}</td>
							<td>${f.bairro}</td>
							<td>${f.cidade}</td>
							<td>${f.estado}</td>
							<td>${f.telefone}</td>
							<td>${f.email}</td>
							<th>${f.observacao}</th>
							<td><a
								href="${pageContext.request.contextPath}/protected/fornecedor/preparaEdit.do?idFornecedor=${f.idFornecedor}">
									<span class="glyphicon glyphicon-edit"></span>

							</a> &nbsp;&nbsp;&nbsp;&nbsp; <a
								href="${pageContext.request.contextPath}/protected/fornecedor/excluir.do?idFornecedor=${f.idFornecedor}">
									<span class="glyphicon glyphicon-trash"></span>
							</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			

	</tiles:putAttribute>
</tiles:insertDefinition>
</html>
