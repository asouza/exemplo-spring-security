<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${produto.nome}</title>
</head>
<body>
<h1>${produto.nome}</h1>
<div class="comentarios">
	<c:forEach var="comentario" items="${produto.comentarios}">
		<div class="comentario" style="border:dotted;margin:5px">
			<p><a href="/loja-super-segura/comentarios/remove/${produto.id}/comentario/${comentario.id}">${comentario.texto}</a></p>
			<span>${comentario.criador.name}</span>
		</div>
	</c:forEach>
</div>
<hr/>
<form action="/loja-super-segura/comentarios" method="post">
	<input type="hidden" name="produto.id" value="${produto.id}"/>
	<textarea rows="10" cols="20" name="texto"></textarea>
	<sec:csrfInput/>
	<input type="submit" value="Novo comentario"/>
</form>
</body>
</html>