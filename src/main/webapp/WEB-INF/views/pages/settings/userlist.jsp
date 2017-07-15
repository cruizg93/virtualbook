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
			<span class="lead">List of Users </span>
		</div>
		<div class="table-responsive">
			<table class="table table-bordered" width="100%" id="usersTable">
				<thead>
					<tr>
						<th>Name</th>
						<th class="hidden-xs">Address</th>
						<th>Phone</th>
						<th class="hidden-xs">Email</th>
						<th class="hidden-xs">Username</th>
						<sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
							<th width="100"></th>
						</sec:authorize>

					</tr>
				</thead>
				<tbody>
					<c:forEach items="${users}" var="user">
						<tr>
							<td>${user.name}</td>
							<td class="hidden-xs">${user.address}</td>
							<td>${user.phoneNumber}</td>
							<td class="hidden-xs">${user.email}</td>
							<td class="hidden-xs">${user.username}</td>
							<td>
								<sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
								<button type="button"
										class="btn btn-success btn-sm formEditButtonIcon col-lg-6 col-md-6 col-ms-6 col-xs-6"
										onclick="window.location.href ='<c:url value="/edit-user-${user.username}" />'">
									<span class="glyphicon glyphicon-pencil"></span><span class="hidden-ms hidden-xs"> Edit</span>
								</button>
								</sec:authorize>
								<sec:authorize access="hasRole('ADMIN')">
									<button type="button"
										class="btn btn-danger btn-sm formDeleteButtonIcon col-lg-6 col-md-6 col-ms-6 col-xs-6"
										onclick="window.location.href ='<c:url value="/delete-user-${user.username}" />'">
										<span class="glyphicon glyphicon-trash"></span><span class="hidden-ms hidden-xs"> Delete</span>
									</button>
								</sec:authorize>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<sec:authorize access="hasRole('ADMIN')">
				<div class="footerListActionButton">
					<button type="button" class="btn btn-primary btn-lg formAddButtonIcon"
						onclick="window.location.href ='<c:url value="/newuser" />'">
						<span class="glyphicon glyphicon-plus"></span>
						New User</button>
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
    	$("#usersTable").dataTable();
    </script>