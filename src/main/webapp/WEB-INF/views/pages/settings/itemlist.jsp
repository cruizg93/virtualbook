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
		<ul class="nav nav-pills">
		  <li class="active"><a href="#tab_items" data-toggle="pill">ITEMS</a></li>
		  <li><a href="#tab_attachment" data-toggle="pill">ATTACHMENTS</a></li>
		</ul>
		<div class="tab-content">
			<div class="tab-pane active" id="tab_items">
				<!-- Default panel contents -->
				<div class="panel-heading">
					<span class="lead">List of Items</span>
				</div>
				<div class="table-responsive">
					<table class="table table-bordered " width="100%" id="itemsTable">
						<thead>
							<tr>
								<th>Description</th>
								<th>Quantity</th>
								<sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
									<th width="100"></th>
								</sec:authorize>
		
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${items}" var="item">
								<tr>
									<td>${item.description}</td>
									<td>${item.quantity}</td>
									<td>
										<sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
											<button type="button" class="btn btn-success formEditButtonIcon col-lg-6 col-md-6 col-ms-6 col-xs-6"
													onclick="window.location.href ='<c:url value="/edit-item-${item.description}" />'">
													<span class="glyphicon glyphicon-pencil"></span><span
														class="hidden-ms hidden-xs"> Edit</span>
											</button>
										</sec:authorize>
										<sec:authorize access="hasRole('ADMIN')">
											<button type="button" class="btn btn-danger formDeleteButtonIcon col-lg-6 col-md-6 col-ms-6 col-xs-6"
													onclick="deleteItem(${item.id},'${item.description}')">
													<span class="glyphicon glyphicon-trash"></span><span
														class="hidden-ms hidden-xs"> Delete</span>
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
								onclick="window.location.href ='<c:url value="/newitem" />'">
								<span class="glyphicon glyphicon-plus"></span>
								New Item</button>
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
			<div class="tab-pane" id="tab_attachment">
				<!-- Default panel contents -->
				<div class="panel-heading">
					<span class="lead">List of Attachments</span>
				</div>
				<div class="table-responsive">
					<table class="table table-bordered " width="100%" id="itemsTable">
						<thead>
							<tr>
								<th width="80%">Description</th>
								<sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
									<th width="100"></th>
								</sec:authorize>
		
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${attachments}" var="attachment">
								<tr>
									<td>${attachment.description}</td>
									<td>
										<sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
											<button type="button" class="btn btn-success formEditButtonIcon col-lg-6 col-md-6 col-ms-6 col-xs-6"
													onclick="window.location.href ='<c:url value="/edit-attachment-${attachment.id}" />'">
													<span class="glyphicon glyphicon-pencil"></span><span
														class="hidden-ms hidden-xs"> Edit</span>
											</button>
										</sec:authorize>
										<sec:authorize access="hasRole('ADMIN')">
											<button type="button" class="btn btn-danger formDeleteButtonIcon col-lg-6 col-md-6 col-ms-6 col-xs-6"
													onclick="deleteAttachment(${attachment.id}, '${attachment.description }')">
													<span class="glyphicon glyphicon-trash"></span><span
														class="hidden-ms hidden-xs"> Delete</span>
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
								onclick="window.location.href ='<c:url value="newattachment" />'">
								<span class="glyphicon glyphicon-plus"></span>
								New Attachment</button>
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
				
	</div>
</div>

<script src="<c:url value='/static/js/jquery.dataTables.min.js' />" type="text/javascript"></script>
<script src="<c:url value='/static/js/dataTables.bootstrap.min.js' />" type="text/javascript"></script>
<script type="text/javascript">
    	$("#itemsTable").DataTable();

    	function deleteItem(id, description){
    		bootbox.confirm({
        	    title: "Delete Item?",
        	    message: "Are you sure you want to delete the item "+description,
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
        	        	window.location.href ='<c:url value="/delete-item-'+description+'" />';
        	        }
        	    }
        	})
    	};
    	
    	function deleteAttachment(id, description){
    		bootbox.confirm({
        	    title: "Delete Item?",
        	    message: "Are you sure you want to delete the Attachment "+description,
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
        	        	window.location.href ='<c:url value="/delete-attachment-'+id+'" />';
        	        }
        	    }
        	})
    	};
    </script>