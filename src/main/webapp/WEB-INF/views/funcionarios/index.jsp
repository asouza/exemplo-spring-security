<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>	
		<h2>Olá <sec:authentication property="principal.login"/></h2>
		<ul>
			<li>lançar horas</li>
			<sec:authorize access="hasRole('ROLE_DEUS') or hasRole('ROLE_DIRETOR')">
				<li>cursos mais vendidos</li>
				<li>horas dos instrutores</li>
			</sec:authorize>
			<sec:authorize access="hasRole('ROLE_DIRETOR')">
				<li>quadro de demissões</li>
			</sec:authorize>
		</ul>
</body>
</html>