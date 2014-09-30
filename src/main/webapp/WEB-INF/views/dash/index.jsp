<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
	<body>
		<ul>
			<li>ultimas compras</li>
			<sec:authorize access="hasRole('ROLE_DEUS')">
				<li>mais vendidos</li>
				<li>menos vendidos</li>
			</sec:authorize>
		</ul>
	</body>
</html>