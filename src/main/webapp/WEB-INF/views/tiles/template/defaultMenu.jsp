<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<link href="<c:url value='/static/css/menu.css' />" rel="stylesheet"></link>
<div class="container hidden-xs well wellMenu well-dark defaultMenu">
	<div class="row">
		<div class="col-md-3 col-sm-3 col-xs-6">
		<div class="card defaultMenu text-center clickable" onclick="window.location.href = '${pageContext.request.contextPath}/eventlist'">
			  <div class="card_title">
			    <a href="#"><span class="glyphicon glyphicon-calendar"></span><h2>Calendar</h2></a>
			  </div>
			</div>
		</div>
		<div class="col-md-3 col-sm-3 col-xs-6 hidden-xs">
			<div class="card defaultMenu text-center clickable" onclick="window.location.href = '${pageContext.request.contextPath}/dashboard'">
			    <a href="#" class=""><span class="glyphicon glyphicon-stats"></span><h2>Statistics</h2></a>
			</div>
		</div>
		<div class="col-md-3 col-sm-3 col-xs-6 hidden-xs ">
			<div class="card defaultMenu text-center clickable" onclick="window.location.href = '${pageContext.request.contextPath}/invoicelist'">
			  <div class="card_title">
			    <a href="#" class=""><span class="glyphicon glyphicon-piggy-bank" ></span><h2>Invoices</h2></a>
			  </div>
			</div>
		</div>
		<sec:authorize access="hasRole('ADMIN')">
			<div class="col-md-3 col-sm-3 col-xs-6 hidden-xs ">
				
				<div class="card defaultMenu text-center clickable" onclick="window.location.href = '${pageContext.request.contextPath}/settings'">
				  <div class="card_title">
				    <a href="#"><span class="glyphicon glyphicon-cog" ></span><h2>Settings</h2></a>
				  </div>
				</div>
			</div>
		</sec:authorize>		
	</div>
</div>
