<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<link href="<c:url value='/static/css/bootstrap-data-tables.css' />"
	rel="stylesheet"></link>
<div class="container well">
	<div class="panel panel-default">
		<!-- Default panel contents -->
		<div class="panel-heading">
			<span class="lead">List of Locations</span>
		</div>
		<div class="table-responsive">
			<table class="table table-bordered" width="100%" id="locationTable">
				<thead>
					<tr>
						<th class="hidden-xs">Location</th>
						<th>Building</th>
						<th>Phone</th>
						<sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
							<th></th>
						</sec:authorize>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${locations}" var="location">
						<tr class="state_${location.state }">
							<td class="hidden-xs">${location.location}</td>
							<td>${location.buildingName}</td>
							<td>${location.phoneNumber}</td>
							<sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
								<td>
									<button type="button"
										class="btn btn-success formEditButtonIcon col-lg-6 col-md-6 col-ms-6 col-xs-6"
										onclick="window.location.href ='<c:url value="/edit-location-${location.id}" />'">
										<span class="glyphicon glyphicon-pencil"></span><span class="hidden-ms hidden-xs"> Edit</span>
									</button>
									<button type="button"
										class="btn btn-danger formDeleteButtonIcon col-lg-6 col-md-6 col-ms-6 col-xs-6"
										onclick="deleteLocation(${location.id},'${location.location}')">
										<span class="glyphicon glyphicon-trash"></span><span class="hidden-ms hidden-xs"> Delete</span>	
									</button>
								</td>
							</sec:authorize>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<sec:authorize access="hasRole('ADMIN')">
				<div class="footerListActionButton">
					<button type="button" class="btn btn-primary btn-lg formAddButtonIcon"
						onclick="window.location.href ='<c:url value="/newlocation" />'">
						<span class="glyphicon glyphicon-plus"></span>
						New Location</button>
				</div>
			</sec:authorize>
			<c:if test="${not empty param['success']}">
			<div class="col-lg-12 col-md-12 col-ms-12 col-xs-12 text-center">
				<div class="alert alert-success">
				  <%= request.getParameter("success") %>
				  
				</div>
			</div>
			</c:if>
		</div>
	</div>
</div>
<script src="<c:url value='/static/js/jquery.dataTables.min.js' />" type="text/javascript"></script>
<script src="<c:url value='/static/js/dataTables.bootstrap.min.js' />" type="text/javascript"></script>
<script type="text/javascript">
    	$("#locationTable").DataTable({
    		responsive: true,
			"columns":[
				{"width": "25%"},
				{"width": "25%"},
				{"width": "25%"},
				{"width": "25%"}
			]
    	});
    	function deleteLocation(id, location){
    		bootbox.confirm({
        	    title: "Delete location?",
        	    message: "Are you sure you want to delete the location "+location,
        	    buttons: {
        	        cancel: {
        	            label: '<i class="fa fa-times"></i> Cancel'
        	        },
        	        confirm: {
        	            label: '<i class="fa fa-check"></i> Confirm'
        	        }
        	    },
        	    callback: function (result) {
        	        if(result){
        	        	window.location.href ='<c:url value="/delete-location-'+id+'"/>'; 
        	        }
        	    }
        	})
    	};
    </script>