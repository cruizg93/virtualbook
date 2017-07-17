<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<link href="<c:url value='/static/css/stats.css' />" rel="stylesheet"></link>
<div class="container well">
	<div class="row">
		<div class="col-lg-1 col-md-2 col-sm-2 col-xs-12 statsMenu text-center">
			<button onclick="loadYear()" class="btn btn-primary btn-lg">Year</button>
			<select name="years" id="years">
			</select>
			<p><button onclick="showClients()" class="btn btn-primary btn-lg">Clients</button></p>
			<p><button onclick="showItems()" class="btn btn-primary btn-lg">Items</button></p>
		</div>
		<div class="col-lg-11 col-md-10 col-sm-10 col-xs-12">
			<p class="chartTitle" id="chartTitle"></p>
			<div class="chart-container hidden-xs">
				<canvas id="myChart" style="height:200px;" ></canvas>
			</div>
			<div class="chart-container hidden-lg hidden-md hidden-sm">
				<canvas id="myChart-xs" ></canvas>
			</div>
		</div>
	</div>
</div>
<script>
	var context = '${pageContext.request.contextPath}';
</script>	
<script type="text/javascript" src="<c:url value='/static/js/Chart.bundle.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/static/js/dashboard.js' />"></script>