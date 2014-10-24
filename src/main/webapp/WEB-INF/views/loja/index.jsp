<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authentication var="principal" property="principal" />
<html>
	<body>
		<ul>
			<c:forEach  var="produto" items="${produtos}">
				<li><a href="/loja-super-segura/produto/${produto.id}">${produto.nome}</a></li>
			</c:forEach>
		</ul>
	</body>
</html>