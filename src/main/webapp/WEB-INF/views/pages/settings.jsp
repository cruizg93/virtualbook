<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container text-center">
	<div class="well well-dark">
		<div class="row">
			<div class="col-md-3 col-sm-3 col-xs-6 ">
		 		<div class="card text-center clickable" onclick="window.location.href = '${pageContext.request.contextPath}/list'">
		 			<div class="image">
		 				<img src="<c:url value='/static/img/icons/User Coat Blue-01.png'/>"/>
		 			</div>
					<div class="card_title footer">
				    	<a href="#" class=""><h3>USERS</h3></a>
				  	</div>
				</div>
		 	</div>
		 	
		 	<div class="col-md-3 col-sm-3 col-xs-6">
		 		<div class="card text-center clickable" onclick="window.location.href = '${pageContext.request.contextPath}/Client'">
		 			<div class="image">
		 				<img src="<c:url value='/static/img/icons/User Clients-01.png'/>"/>
		 			</div>
					<div class="card_title footer">
						<a href="#" ><h3>CLIENTS</h3></a>
					</div>
				</div>
		 	</div>
		 	
		 	<div class="col-md-3 col-sm-3 col-xs-6">
		 		<div class="card text-center clickable" onclick="window.location.href = '${pageContext.request.contextPath}/Location'">
		 			<div class="image">
		 				<img src="<c:url value='/static/img/icons/Safari.png'/>"/>
		 			</div>
				  	<div class="card_title footer">
				    	<a href="#" ><h3>LOCATIONS</h3></a>
				  	</div>
				</div>
		 	</div>
	
			<div class="col-md-3 col-sm-3 col-xs-6">
		 		<div class="card text-center clickable" onclick="window.location.href = '${pageContext.request.contextPath}/Item'">
		 			<div class="image">
		 				<img src="<c:url value='/static/img/icons/Games-01.png'/>"/>
		 			</div>
				  	<div class="card_title footer">
				    	<a href="#" ><h3>ITEMS</h3></a>
				  	</div>
				</div>
		 	</div>
		</div>
	</div>
</div>