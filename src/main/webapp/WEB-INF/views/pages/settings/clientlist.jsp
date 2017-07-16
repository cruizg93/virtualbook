<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<link href="<c:url value='/static/css/bootstrap-data-tables.css' />" rel="stylesheet"></link>
<div class="container well">
	<div class="panel panel-default">
          <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of Clients</span></div>
        <div class="table-responsive">
        	<table class="table table-bordered " id="clientTable" width="100%">
	            <thead>
	                <tr>
	                    <th>Name</th>
	                    <th class="hidden-xs">Phone</th>
	                    <th class="hidden-xs">Email</th>
	                    <th>Company</th>
	                    <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
	                    	<th></th>
	                    </sec:authorize>
	                </tr>
	            </thead>
	            <tbody>
	            <c:forEach items="${clients}" var="client">
	                <tr>
	                    <td>${client.name}</td>
	                    <td class="hidden-xs">${client.phoneNumber}</td>
	                    <td class="hidden-xs">${client.email}</td>
	                    <td>${client.companyName}</td>
	                    <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
	                    <td>
	                    	<button type="button" class="btn btn-success formEditButtonIcon col-lg-6 col-md-6 col-ms-6 col-xs-6"
	                         	onclick="window.location.href ='<c:url value="/edit-client-${client.id}" />'"><span class="glyphicon glyphicon-pencil"></span><span class="hidden-ms hidden-xs"> Edit</span></button>
	                        <button type="button" class="btn btn-danger formDeleteButtonIcon col-lg-6 col-md-6 col-ms-6 col-xs-6" 
	                    		onclick="deleteClient(${client.id},'${client.name}')"><span class="glyphicon glyphicon-trash"></span><span class="hidden-ms hidden-xs"> Delete</span></button>
	                    </td>
	                   	<!-- TODO: add confirm dialog to delete button -->
	                    </sec:authorize>
	                </tr>
	            </c:forEach>
	            </tbody>
	        </table>
	        <sec:authorize access="hasRole('ADMIN')">
				<div class="footerListActionButton">
					<button type="button" class="btn btn-primary btn-lg formAddButtonIcon" onclick="window.location.href ='<c:url value="/newclient" />'">
						<span class="glyphicon glyphicon-plus"></span> New Client
					</button>
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
	$("#clientTable").DataTable();
	function deleteClient(id, client){
		bootbox.confirm({
    	    title: "Delete client?",
    	    message: "Are you sure you want to delete the Client "+client,
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
    	        	window.location.href ='<c:url value="/delete-client-'+id+'" />'
    	        }
    	    }
    	})
	};
</script>