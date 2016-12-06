<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container text-center">
	<div class="row">
	 	<div class="col-md-3 col-sm-4">
	 		<div class="generic-container settings" onclick="window.location.href = '${pageContext.request.contextPath}/list'">
	 			<img src="<c:url value='/static/img/icons/User Coat Blue-01.png'/>"/>
	 			<footer>
	 				<h3>USERS</h3>
	 			</footer>
 			</div>
		</div>
	 	<div class="col-md-3 col-sm-4">
	 		<div class="generic-container settings" onclick="window.location.href = '${pageContext.request.contextPath}/Client'">
	 			<img src="<c:url value='/static/img/icons/User Clients-01.png'/>"/>
	 			<footer>
	 				<h3>CLIENTS</h3>
	 			</footer>
 			</div>
		</div>
	 	<div class="col-md-3 col-sm-4">
	 		<div class="generic-container settings" onclick="window.location.href = '${pageContext.request.contextPath}/Location'">
	 			<img src="<c:url value='/static/img/icons/Safari.png'/>"/>
	 			<footer>
	 				<h3>LOCATIONS</h3>
	 			</footer>
			</div>
		</div>
	 	<div class="col-md-3 col-sm-4">
	 		<div class="generic-container settings" onclick="window.location.href = '${pageContext.request.contextPath}/Item'">
	 			<img src="<c:url value='/static/img/icons/Games-01.png'/>"/>
	 			<footer>
	 				<h3>ITEMS</h3>
	 			</footer>
			</div>
		</div>
	</div>
</div>