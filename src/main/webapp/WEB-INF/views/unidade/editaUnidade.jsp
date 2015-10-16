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
		<h1>Editar Unidade</h1>

		<springform:form method="post" action="${pageContext.request.contextPath}/protected/unidade/edit.do"
		 modelAttribute="unidade" class="form-horizontal">


			<div class="form-group">
				<label class="col-md-1 control-label" for="descricao">Descrição:</label>
				<div class="col-md-4">
					<springform:input id="descricao" path="descricao" cssClass="form-control" 
					placeholder="Descrição" autofocus="true" required="true" maxlength="48"/>
				</div>
			</div>
				<springform:hidden path="idUnidade" />


			<input type="submit" value="Salvar" class="btn btn-primary" />
			
			<a href="${pageContext.request.contextPath}/protected/unidade/listar.do">
									<input type="button" value="Cancelar" class="btn btn-default" />
								</a>
		</springform:form>
	</tiles:putAttribute>
</tiles:insertDefinition>
</html>