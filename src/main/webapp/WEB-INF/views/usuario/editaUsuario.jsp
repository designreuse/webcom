<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="springform"
	uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<tiles:insertDefinition name="template">
	<tiles:putAttribute name="corpo">
		<h1>Editar Usuário</h1>

		<springform:form method="post" action="${pageContext.request.contextPath}/protected/usuario/edit.do"
		 modelAttribute="usuario" class="form-horizontal">


			<div class="form-group">
				<label class="col-md-1 control-label" for="nome">Nome:</label>
				<div class="col-md-4">
					<springform:input id="nome" path="nome" cssClass="form-control" 
					placeholder="Nome" autofocus="true" required="true" maxlength="48"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-1 control-label" for="login">Login:</label>
				<div class="col-md-4">
					<springform:input id="login" path="login" cssClass="form-control" 
					placeholder="Login" required="true" maxlength="48"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-1 control-label" for="senha">Senha:</label>
				<div class="col-md-4">
					<springform:password id="senha" path="senha"
						cssClass="form-control" placeholder="Preencha somente se quiser alterar"  maxlength="48"/> 
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-1 control-label" for="roles">Permissão</label>
				<div class="col-md-4">
					<select  name="permissao" class="form-control">
						<option value="ROLE_PADRAO">  Padrão </option>
						<option value="ROLE_ADMIN"> Administrador </option>
					</select>
				</div>
			</div>
				<springform:hidden path="id_usuario" />


			<input type="submit" value="Salvar" class="btn btn-primary" />
			
			<a href="${pageContext.request.contextPath}/protected/usuario/listar.do">
									<input type="button" value="Cancelar" class="btn btn-default" />
								</a>
		</springform:form>
	</tiles:putAttribute>
</tiles:insertDefinition>
</html>