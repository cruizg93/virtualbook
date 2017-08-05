<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<link href="<c:url value='/static/css/bootstrap-data-tables.css' />" rel="stylesheet"></link>
<div class="container well">
	<div class="panel panel-default">
          <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of Invoices</span></div>
        <div class="table-responsive">
        	<table class="table table-bordered " id="invoiceTable" width="100%">
	            <thead>
	                <tr>
	                    <th>Client</th>
	                    <th>State</th>
	                    <th>Invoice Number</th>
	                    <th>Due Date</th>
	                    <th>Total</th>
	                    <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
	                    	<th></th>
	                    </sec:authorize>
	                </tr>
	            </thead>
	            <tbody>
	            <c:forEach items="${invoices}" var="invoice">
	                <tr>
	                    <td>${invoice.events.iterator().next().client.name}</td>
	                    <c:if test="${invoice.isCollected()}">
							<c:set var="paid" value="paid" />
						</c:if>
						<c:if test="${invoice.isCollected() eq false}">
							<c:set var="paid" value="notPaid" />
						</c:if>
	                    <td><p class="${paid}">${paid}</p></td>
	                    <td>${invoice.invoiceNumber}</td>
	                    <td>${invoice.dueDate}</td>
	                    <td>${invoice.total}</td>
	                    <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
	                    <td>
	                    	<button type="button" class="btn btn-success formEditButtonIcon col-lg-6 col-md-6 col-ms-6 col-xs-6"
	                         	onclick="window.location.href ='<c:url value="/edit-invoice-${invoice.id}" />'"><span class="glyphicon glyphicon-pencil"></span><span class="hidden-ms hidden-xs"> Edit</span></button>
	                        <c:choose>
	                        	<c:when test="${invoice.isCollected()}">
	                        		<button type="button" class="btn btn-danger formDeleteButtonIcon col-lg-6 col-md-6 col-ms-6 col-xs-6" disabled>
	                        			<span class="glyphicon glyphicon-trash"></span><span class="hidden-ms hidden-xs"> Delete</span></button>
	                        	</c:when>
	                        	<c:otherwise>
	                        		<button type="button" class="btn btn-danger formDeleteButtonIcon col-lg-6 col-md-6 col-ms-6 col-xs-6" 
	                    		onclick="deleteInvoice(${invoice.id},'${invoice.events.iterator().next().client.name}')"><span class="glyphicon glyphicon-trash"></span><span class="hidden-ms hidden-xs"> Delete</span></button>
	                        	</c:otherwise>
	                        </c:choose>
	                    </td>
	                    </sec:authorize>
	                </tr>
	            </c:forEach>
	            </tbody>
	        </table>
	        <sec:authorize access="hasRole('ADMIN')">
				<div class="footerListActionButton">
					<button type="button" class="btn btn-primary btn-lg formAddButtonIcon" onclick="window.location.href ='<c:url value="/newInvoice" />'">
						<span class="glyphicon glyphicon-plus"></span> New Invoice
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
<script type="text/javascript"
	src="<c:url value='/static/js/invoice.js' />"></script>
<script type="text/javascript">
	$("#invoiceTable").DataTable();
	function deleteInvoice(id, client){
		bootbox.confirm({
    	    title: "Delete client?",
    	    message: "Are you sure you want to delete the Invoice for "+client,
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
    	        	window.location.href ='<c:url value="/delete-invoice-'+id+'" />'
    	        }
    	    }
    	})
	};
</script>