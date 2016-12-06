<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
 
 	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
    <link href="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css" rel="stylesheet"></link>
    <script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js" type="text/javascript"></script>
    <div class="generic-container">
        <div class="panel panel-default">
              <!-- Default panel contents -->
            <div class="panel-heading"><span class="lead">List of Clients</span></div>
            <table class="table table-hover" id="clientTable">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Phone</th>
                        <th>Email</th>
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
                        <td>${client.phoneNumber}</td>
                        <td>${client.email}</td>
                        <td>${client.companyName}</td>
                        <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
                        <td>
                        	<button type="button" class="btn btn-success formEditButtonIcon"
	                            	onclick="window.location.href ='<c:url value="/edit-client-${client.name}-${client.companyName}" />'">Edit</button>
                            <button type="button" class="btn btn-danger formDeleteButtonIcon" 
                        		onclick="window.location.href ='<c:url value="/delete-client-${client.name}-${client.companyName}" />'">Delete</button>
                        </td>
                        </sec:authorize>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <sec:authorize access="hasRole('ADMIN')">
            <div class="well">
				<button type="button" class="btn btn-primary formAddButtonIcon" onclick="window.location.href ='<c:url value="/newclient" />'">Add New Client</button>
            </div>
        </sec:authorize>
    </div>
    <script type="text/javascript">
    	$("#clientTable").DataTable();
    </script>